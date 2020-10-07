package pers.tuershen.nbtedit.function.edit.item.attribute;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.*;
import pers.tuershen.nbtedit.function.annotation.EditDescribe;
import pers.tuershen.nbtedit.function.annotation.TagParameter;
import pers.tuershen.nbtedit.function.handle.TagType;

import java.util.ArrayList;
import java.util.List;


public class EditMaxHealth extends AttributeBase {

    @TagParameter(TYPE = TagType.DOUBLE)
    private double maxHealth;

    public EditMaxHealth(){
        this.key = "generic.maxHealth";
        this.slotItem = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta itemMeta = this.slotItem.getItemMeta();
        itemMeta.setDisplayName("§a给物品增加§d最大生命值§a，不限于装备，什么物品都可以");
        List<String> lore = new ArrayList<>();
        lore.add("§eⒺ §d这个生物的最大生命值；亦或这个生物通过生命恢复最多可以恢复至的极限。");
        itemMeta.setLore(lore);
        this.slotItem.setItemMeta(itemMeta);
    }


    @EditDescribe( describe = {"§eⒺ §2请输入物品最大生命值，取值范围"+Double.MIN_VALUE+"~"+Double.MAX_VALUE})
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
