package pers.tuershen.nbtedit.function.edit.entity;

import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagBase;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagCompound;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagList;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pers.tuershen.nbtedit.function.AbstractEditManager;
import pers.tuershen.nbtedit.function.annotation.EditDescribe;
import pers.tuershen.nbtedit.function.annotation.TagParameter;
import pers.tuershen.nbtedit.function.handle.NewNBTTag;
import pers.tuershen.nbtedit.function.handle.TagType;

public class EditEntityMoveSpeed extends AbstractEditManager {

    @TagParameter(TYPE = TagType.DOUBLE)
    private double moveSpeed;


    public EditEntityMoveSpeed(){
        this.key = "Attributes";
        this.slotItem = new ItemStack(Material.FEATHER);
        ItemMeta itemMeta = this.slotItem.getItemMeta();
        itemMeta.setDisplayName("§b设置实体的移动速度");
        this.slotItem.setItemMeta(itemMeta);
    }


    @EditDescribe( describe = {"§eⒺ §2修改实体的移动速度，取值范围是10的-308次方到10的308次方"})
    @Override
    public TagBase inputParams(TagBase tagBase, String... params) {
        if (!(tagBase instanceof TagCompound)) return tagBase;
        TagCompound base = (TagCompound) tagBase;
        TagList tagList = (TagList) base.getMap().get(key);
        if (tagList == null){
            tagList = new TagList();
        }
        TagCompound tagCompound = new TagCompound();
        tagCompound.getMap().put("Name", NewNBTTag.type((byte) 8).newTag("generic.movementSpeed"));
        tagCompound.getMap().put("Base", NewNBTTag.type((byte) 6).newTag(params[0]));
        tagList.add(tagCompound);
        base.getMap().put(key, tagList);
        return base;
    }

    @Override
    public int getIndex() {
        return 0;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public ItemStack getSlotItem() {
        return slotItem;
    }
}
