package pers.tuershen.nbtedit.function.edit;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagBase;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagCompound;
import pers.tuershen.nbtedit.function.AbstractEditManager;

/**
 * 清除所有NBT
 */
public class EditTagRemove extends AbstractEditManager {


    public EditTagRemove(){
        this.slotItem = new ItemStack(Material.LAVA_BUCKET);
        ItemMeta itemMeta = this.slotItem.getItemMeta();
        itemMeta.setDisplayName("§c清除所有NBT");
        this.slotItem.setItemMeta(itemMeta);
    }

    @Override
    public TagBase inputParams(TagBase tagBase, String... params) {
        return new TagCompound();
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
