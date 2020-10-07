package pers.tuershen.nbtedit.function.edit.item.attribute;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagBase;
import pers.tuershen.nbtedit.function.annotation.EditDescribe;
import pers.tuershen.nbtedit.function.annotation.TagParameter;
import pers.tuershen.nbtedit.function.handle.TagType;

import java.util.ArrayList;
import java.util.List;


public class EditKnockBackResistance extends AttributeBase {


    @TagParameter(TYPE = TagType.DOUBLE)
    private double knockBackResistance;

    public EditKnockBackResistance(){
        this.key = "generic.knockbackResistance";
        this.slotItem = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        ItemMeta itemMeta = this.slotItem.getItemMeta();
        itemMeta.setDisplayName("§a给物品天机抗击退效果（包括攻击的击退、爆炸和弹射物冲击）的程度");
        List<String> lore = new ArrayList<>();
        lore.add("§eⒺ §d值为1.0代表完全抵抗");
        itemMeta.setLore(lore);
        this.slotItem.setItemMeta(itemMeta);
    }

    @EditDescribe( describe = {"§eⒺ §2请输入物品的击退坑性，取值范围"+Double.MIN_VALUE+"~"+Double.MAX_VALUE})
    @Override
    public TagBase inputParams(TagBase tagBase, String... params) {
        return super.inputParams(tagBase,params);
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
