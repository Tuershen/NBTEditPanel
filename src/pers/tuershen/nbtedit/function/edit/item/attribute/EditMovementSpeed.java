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

public class EditMovementSpeed extends AttributeBase {

    @TagParameter(TYPE = TagType.DOUBLE)
    private double movementSpeed;

    public EditMovementSpeed(){
        this.key = "generic.movementSpeed";
        this.slotItem = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta itemMeta = this.slotItem.getItemMeta();
        itemMeta.setDisplayName("§a给物品增加§d移动速度§a，不限于装备，什么物品都可以");
        List<String> lore = new ArrayList<>();
        lore.add("§eⒺ §b在某种不明度量标准下这个生物的速度。");
        lore.add("§eⒺ §b以格/秒为单位的生物最大速度略高于此值的43倍，但可能会受到各种条件的影响。");
        lore.add("§eⒺ §b例如：");
        lore.add("§eⒺ §b骑马（如果是马），疾跑，逃跑（如果是被动生物），");
        lore.add("§eⒺ §b攻击（如果是一个末影人或僵尸猪人），被拴绳牵引，受速度或迟缓药水影响，为幼年僵尸或者是一个女巫和喝药水。");
        lore.add("§eⒺ §b可以使用以下等式计算每秒格数的速度，其中x是movementSpeed属性y = 43.178x-0.02141");
        itemMeta.setLore(lore);
        this.slotItem.setItemMeta(itemMeta);
    }


    @EditDescribe( describe = {"§eⒺ §2请输入物品的移动速度，取值范围"+Double.MIN_VALUE+"~"+Double.MAX_VALUE})
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
