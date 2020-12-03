package pers.tuershen.nbtedit.function.edit.tile.mobspawner.entity;

import org.bukkit.inventory.ItemStack;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagBase;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagCompound;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagList;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagString;
import pers.tuershen.nbtedit.function.AbstractEditManager;
import pers.tuershen.nbtedit.function.handle.NewNBTTag;

import java.util.List;

public class EditMobSpawnManager extends AbstractEditManager {

    @Override
    public TagBase inputParams(TagBase tagBase, String... params) {
        if (tagBase instanceof TagCompound){
          return setEntityAttributes((TagCompound) tagBase,params[0]);
        }
        return tagBase;
    }


    public TagCompound setEntityAttributes(TagCompound tagCompound, String data){
        TagList potentials = (TagList)tagCompound.getMap().get("SpawnPotentials");
        if (potentials == null){
            potentials = new TagList();
        }
        List<TagBase> pt = potentials.getData();
        for (int i = 0; i < pt.size(); i++) {
            TagCompound entity = (TagCompound) pt.get(i);
            TagCompound entityAttribute = (TagCompound) entity.getMap().get("Entity");
            entityAttribute.getMap().put("Attributes", setAttribute((TagList) entityAttribute.getMap().get("Attributes"),data));
            entity.getMap().put("Entity", entityAttribute);
            pt.set(i,entity);
        }
        tagCompound.getMap().put("SpawnPotentials", potentials);
        return tagCompound;
    }


    public TagList setAttribute(TagList attributeArray, String data){
        if (attributeArray != null && attributeArray.getData().size() <= 0){
            List<TagBase> attributeData = attributeArray.getData();
            for (int i = 0; i < attributeData.size(); i++) {
                TagCompound compound = (TagCompound) attributeData.get(i);
                if (compound.getMap().get("Name").toString().equalsIgnoreCase(this.key)){
                    compound.getMap().put("Base", NewNBTTag.type((byte)6).newTag(data));
                    compound.getMap().put("Name", new TagString(this.key));
                    attributeData.set(i,compound);
                    attributeArray.setData(attributeData);
                    return attributeArray;
                }
            }
        }
        attributeArray = new TagList();
        attributeArray.getData().add(newAttribute(data));
        return attributeArray;
    }


    public TagCompound newAttribute(String data){
        TagCompound attributeCompound = new TagCompound();
        attributeCompound.getMap().put("Base", NewNBTTag.type((byte)6).newTag(data));
        attributeCompound.getMap().put("Name", new TagString(this.key));
        return attributeCompound;
    }


    @Override
    public int getIndex() {
        return 0;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public ItemStack getSlotItem() {
        return this.slotItem;
    }
}
