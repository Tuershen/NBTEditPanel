package pers.tuershen.nbtedit.function.edit.tile.mobspawner.entity;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagBase;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagCompound;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagInt;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagList;
import pers.tuershen.nbtedit.function.AbstractEditManager;
import pers.tuershen.nbtedit.function.annotation.EditDescribe;
import pers.tuershen.nbtedit.function.annotation.TagParameter;
import pers.tuershen.nbtedit.function.handle.NewNBTTag;
import pers.tuershen.nbtedit.function.handle.TagType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditSpawnPotentials extends AbstractEditManager {

    @TagParameter(TYPE = TagType.STRING)
    private String id;

    public EditSpawnPotentials(){
        this.key = "SpawnPotentials";
        this.slotItem = new ItemStack(Material.MOB_SPAWNER);
        ItemMeta itemMeta = this.slotItem.getItemMeta();
        itemMeta.setDisplayName("§7设置刷怪笼的实体类型");
        List<String> lore = new ArrayList<>();
        lore.add("§eⒺ §b一个刷怪笼可以添加多个不同或者相同的实体");
        lore.add("§eⒺ §b如果你不知道实体类型名称可以手持刷个蛋查看其NBT即可知晓");
        itemMeta.setLore(lore);
        this.slotItem.setItemMeta(itemMeta);
    }

    @EditDescribe( describe = {"§eⒺ §2请输入实体类型名称"})
    @Override
    public TagBase inputParams(TagBase tagBase, String... params) {
        if (tagBase instanceof TagCompound){
            TagList spawnPotentials = (TagList) ((TagCompound) tagBase).getMap().get("SpawnPotentials");
            if (spawnPotentials == null){
                spawnPotentials = new TagList();
            }
            TagCompound entitySpawnPotentials = new TagCompound();
            entitySpawnPotentials.getMap().put("Weight", new TagInt(1));
            Map<String,TagBase> tagMap = new HashMap<>();
            tagMap.put("id", NewNBTTag.type((byte)8).newTag(params[0]));
            entitySpawnPotentials.getMap().put("Entity", new TagCompound(tagMap));
            Map<String,TagBase> data = new HashMap<>();
            data.put("id", NewNBTTag.type((byte)8).newTag(params[0]));
            spawnPotentials.getData().add(entitySpawnPotentials);
            ((TagCompound) tagBase).getMap().put(key, spawnPotentials);
            ((TagCompound) tagBase).getMap().put("SpawnData", new TagCompound(data));
        }
        return tagBase;
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
