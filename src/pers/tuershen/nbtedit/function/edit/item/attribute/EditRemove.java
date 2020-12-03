package pers.tuershen.nbtedit.function.edit.item.attribute;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagBase;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagCompound;
import pers.tuershen.nbtedit.function.AbstractEditManager;

/**
 *清除物品的属性
 */
public class EditRemove extends AbstractEditManager {

    public EditRemove(){
        this.key = "AttributeModifiers";
        this.slotItem = new ItemStack(Material.TNT);
        ItemMeta itemMeta = this.slotItem.getItemMeta();
        itemMeta.setDisplayName("§c清除物品的属性效果(最大生命值，速度，攻击伤害，击退坑性等等)");
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
