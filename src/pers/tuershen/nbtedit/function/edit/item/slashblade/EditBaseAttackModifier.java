package pers.tuershen.nbtedit.function.edit.item.slashblade;

import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagBase;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagCompound;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pers.tuershen.nbtedit.function.AbstractEditManager;
import pers.tuershen.nbtedit.function.annotation.EditDescribe;
import pers.tuershen.nbtedit.function.annotation.TagParameter;
import pers.tuershen.nbtedit.function.handle.NewNBTTag;
import pers.tuershen.nbtedit.function.handle.TagType;


public class EditBaseAttackModifier extends AbstractEditManager {


    @TagParameter(TYPE = TagType.FLOAT)
    private float baseAttackModifier;

    public EditBaseAttackModifier(){
        this.key = "baseAttackModifier";
        this.slotItem = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta itemMeta = this.slotItem.getItemMeta();
        itemMeta.setDisplayName("§2修改拔刀剑的伤害值");
        this.slotItem.setItemMeta(itemMeta);
    }


    @EditDescribe( describe = {"§eⒺ §2请输入伤害值，取值范围为"+Float.MIN_VALUE+"~"+Float.MAX_VALUE})
    @Override
    public TagBase inputParams(TagBase tagBase, String... params) {
        if (!(tagBase instanceof TagCompound)) return tagBase;
        TagCompound tagCompound = (TagCompound) tagBase;
        tagCompound.getMap().put(key, NewNBTTag.type((byte) 5).newTag(params[0]));
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
