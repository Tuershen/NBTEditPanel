package pers.tuershen.nbtedit.function.edit.item.mate;

import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagBase;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagByte;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagCompound;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pers.tuershen.nbtedit.function.AbstractEditManager;
public class EditItemUnbreakable extends AbstractEditManager {


    public EditItemUnbreakable(){
        this.key = "Unbreakable";
        this.slotItem = new ItemStack(Material.BEDROCK);
        ItemMeta itemMeta = this.slotItem.getItemMeta();
        itemMeta.setDisplayName("§2设置物品为无法破坏 或者 无限耐久");
        this.slotItem.setItemMeta(itemMeta);
    }

    @Override
    public TagBase inputParams(TagBase tagBase, String... params) {
        if (!(tagBase instanceof TagCompound)) return tagBase;
        TagCompound tagCompound = (TagCompound) tagBase;
        tagCompound.getMap().put(key, new TagByte((byte) 1));
        return tagCompound;
    }

    @Override
    public ItemStack getSlotItem() {
        return this.slotItem;
    }

    @Override
    public int getIndex() {
        return 0;
    }

    @Override
    public String getKey() {
        return this.key;
    }

}
