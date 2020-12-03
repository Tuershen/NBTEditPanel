package pers.tuershen.nbtedit.function.edit.item.mate;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagBase;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagCompound;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagList;
import pers.tuershen.nbtedit.function.AbstractEditManager;
import pers.tuershen.nbtedit.function.annotation.EditDescribe;
import pers.tuershen.nbtedit.function.annotation.TagParameter;
import pers.tuershen.nbtedit.function.handle.NewNBTTag;
import pers.tuershen.nbtedit.function.handle.TagType;

public class EditRemoveIndexLore extends AbstractEditManager {

    @TagParameter( TYPE = TagType.INT)
    private int index;

    public EditRemoveIndexLore(){
        this.key = "display";
        this.slotItem = new ItemStack(Material.BOOK);
        ItemMeta itemMeta = this.slotItem.getItemMeta();
        itemMeta.setDisplayName("§2删除指定下标的lore");
        this.slotItem.setItemMeta(itemMeta);

    }

    @EditDescribe( describe = {"§eⒺ §2删除指定下标的lore，请输入下标"})
    @Override
    public TagBase inputParams(TagBase tagBase, String... params) {
        if (!(tagBase instanceof TagCompound)) return tagBase;
        TagCompound tagCompound = (TagCompound) ((TagCompound) tagBase).getMap().get(this.key);
        if (tagCompound == null) return tagBase;
        TagList lore = (TagList) tagCompound.getMap().get("Lore");
        if (lore == null) return tagBase;
        int index = (int)NewNBTTag.type((byte)3).newTag(params[0]).data();
        if (index >= lore.getData().size()) return tagBase;
        lore.remove(index);
        tagCompound.getMap().put("Lore", lore);
        ((TagCompound) tagBase).getMap().put(key, tagCompound);
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
