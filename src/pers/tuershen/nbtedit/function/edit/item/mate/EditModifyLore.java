package pers.tuershen.nbtedit.function.edit.item.mate;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagBase;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagCompound;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagList;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagString;
import pers.tuershen.nbtedit.function.AbstractEditManager;
import pers.tuershen.nbtedit.function.annotation.EditDescribe;
import pers.tuershen.nbtedit.function.annotation.TagParameter;
import pers.tuershen.nbtedit.function.handle.NewNBTTag;
import pers.tuershen.nbtedit.function.handle.TagType;

import java.util.Map;

public class EditModifyLore extends AbstractEditManager {


    @TagParameter( TYPE = TagType.INT)
    private int index;

    @TagParameter( TYPE = TagType.STRING)
    private String lore;

    public EditModifyLore(){
        this.key = "display";
        this.slotItem = new ItemStack(Material.BOOK);
        ItemMeta itemMeta = this.slotItem.getItemMeta();
        itemMeta.setDisplayName("§2修改指定下标的lore");
        this.slotItem.setItemMeta(itemMeta);
    }

    @EditDescribe( describe = {
            "§eⒺ §2修改指定位置的lore，请输入两个参数",
            "§eⒺ §2第一个为lore的下标",
            "§eⒺ §2第二个为新的lore"})
    @Override
    public TagBase inputParams(TagBase tagBase, String... params) {
        if (!(tagBase instanceof TagCompound)) return tagBase;
        TagCompound tagCompound = (TagCompound) ((TagCompound) tagBase).getMap().get(this.key);
        if (tagCompound == null){
            tagCompound = new TagCompound();
        }
        Map<String, TagBase> map = tagCompound.getMap();
        TagList list = (TagList) map.get("Lore");
        if (list == null){
            list = new TagList();
        }
        try {
            int index = (int)NewNBTTag.type((byte) 3).newTag(params[0]).data();
            if (index >= list.getData().size()) return tagBase;
            list.getData().set(index, new TagString(params[1]));
        }catch (Exception e){
            e.printStackTrace();
        }
        map.put("Lore", list);
        TagCompound base = (TagCompound) tagBase;
        base.getMap().put(key, tagCompound);
        return base;
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
