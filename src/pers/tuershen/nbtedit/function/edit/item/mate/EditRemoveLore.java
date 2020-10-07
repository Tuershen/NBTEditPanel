package pers.tuershen.nbtedit.function.edit.item.mate;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagBase;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagCompound;
import pers.tuershen.nbtedit.function.AbstractEditManager;

public class EditRemoveLore extends AbstractEditManager {

    public EditRemoveLore(){
        this.key = "display";
        this.slotItem = new ItemStack(Material.BOOK);
        ItemMeta itemMeta = this.slotItem.getItemMeta();
        itemMeta.setDisplayName("§2删除物品的所有lore");
        this.slotItem.setItemMeta(itemMeta);

    }

    @Override
    public TagBase inputParams(TagBase tagBase, String... params) {
        if (!(tagBase instanceof TagCompound)) return tagBase;
        TagCompound tagCompound = (TagCompound) ((TagCompound) tagBase).getMap().get(this.key);
        if (tagCompound == null) return tagBase;
        tagCompound.getMap().remove("Lore");
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
