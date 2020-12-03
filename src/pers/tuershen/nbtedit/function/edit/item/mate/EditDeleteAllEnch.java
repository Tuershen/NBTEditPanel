package pers.tuershen.nbtedit.function.edit.item.mate;

import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagBase;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagCompound;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pers.tuershen.nbtedit.function.AbstractEditManager;

public class EditDeleteAllEnch extends AbstractEditManager {

    public EditDeleteAllEnch(){
        this.key = "ench";
        this.slotItem = new ItemStack(Material.ENCHANTMENT_TABLE);
        ItemMeta itemMeta = this.slotItem.getItemMeta();
        itemMeta.setDisplayName("§c清除物品的所有附魔");
        this.slotItem.setItemMeta(itemMeta);
    }

    @Override
    public TagBase inputParams(TagBase tagBase, String... params) {
        if (!(tagBase instanceof TagCompound)) return tagBase;
        TagCompound tagCompound = (TagCompound) tagBase;
        tagCompound.getMap().remove(this.key);
        return tagCompound;
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
