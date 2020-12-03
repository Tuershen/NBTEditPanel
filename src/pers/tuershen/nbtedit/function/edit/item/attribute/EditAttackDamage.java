package pers.tuershen.nbtedit.function.edit.item.attribute;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagBase;
import pers.tuershen.nbtedit.function.annotation.EditDescribe;
import pers.tuershen.nbtedit.function.annotation.TagParameter;
import pers.tuershen.nbtedit.function.handle.TagType;

import java.util.ArrayList;
import java.util.List;

public class EditAttackDamage extends AttributeBase {

    @TagParameter(TYPE = TagType.DOUBLE)
    private double attackDamage;

    public EditAttackDamage(){
        this.key = "generic.attackDamage";
        this.slotItem = new ItemStack(Material.STONE_SWORD);
        ItemMeta itemMeta = this.slotItem.getItemMeta();
        itemMeta.setDisplayName("§a修改物品的§d攻击力§a，不限于装备，什么物品都可以");
        List<String> lore = new ArrayList<>();
        lore.add("§eⒺ §b普通攻击造成的伤害，一点表示半个心形标志。");
        itemMeta.setLore(lore);
        this.slotItem.setItemMeta(itemMeta);
    }

    @EditDescribe( describe = {"§eⒺ §2请输入物品的攻击力，取值范围"+Double.MIN_VALUE+"~"+Double.MAX_VALUE})
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
