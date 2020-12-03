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

public class EditSpawnReinforcements extends AttributeBase {

    @TagParameter(TYPE = TagType.DOUBLE)
    private double spawnReinforcements;

    public EditSpawnReinforcements(){
        this.key = "zombie.spawnReinforcements";
        this.slotItem = new ItemStack(Material.SIGN);
        ItemMeta itemMeta = this.slotItem.getItemMeta();
        itemMeta.setDisplayName("§a修改物品的§d僵尸增援§a，不限于装备，什么物品都可以");
        List<String> lore = new ArrayList<>();
        lore.add("§eⒺ §b僵尸在一次攻击中在周围生成另一个僵尸的可能性。即使是僵尸猪人也会生成僵尸。");
        itemMeta.setLore(lore);
        this.slotItem.setItemMeta(itemMeta);
    }


    @EditDescribe( describe = {"§eⒺ §2请输入物品的僵尸增援，取值范围"+Double.MIN_VALUE+"~"+Double.MAX_VALUE})
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
