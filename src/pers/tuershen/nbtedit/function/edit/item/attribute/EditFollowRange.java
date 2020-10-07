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

public class EditFollowRange extends AttributeBase {

    @TagParameter(TYPE = TagType.DOUBLE)
    private double followRange;

    public EditFollowRange(){
        this.key = "generic.followRange";
        this.slotItem = new ItemStack(Material.LEATHER_BOOTS);
        ItemMeta itemMeta = this.slotItem.getItemMeta();
        itemMeta.setDisplayName("§a修改物品的§d生物跟随距离§a，不限于装备，什么物品都可以");
        List<String> lore = new ArrayList<>();
        lore.add("§eⒺ §d这个生物追踪玩家或者其他生物的最大范围，以方块数为单位");
        lore.add("§eⒺ §d目标离开这个区域意味着它们将停止追踪");
        lore.add("§eⒺ §d目前大多数生物这个值为16，而僵尸则有40");
        itemMeta.setLore(lore);
        this.slotItem.setItemMeta(itemMeta);
    }

    @EditDescribe( describe = {"§eⒺ §2请输入物品的生物跟随距离，取值范围"+Double.MIN_VALUE+"~"+Double.MAX_VALUE})
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
