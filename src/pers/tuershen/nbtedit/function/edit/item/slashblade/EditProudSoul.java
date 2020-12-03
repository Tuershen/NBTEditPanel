package pers.tuershen.nbtedit.function.edit.item.slashblade;

import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagBase;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagCompound;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pers.tuershen.nbtedit.function.AbstractEditManager;
import pers.tuershen.nbtedit.function.annotation.EditDescribe;
import pers.tuershen.nbtedit.function.annotation.TagParameter;
import pers.tuershen.nbtedit.function.handle.NewNBTTag;
import pers.tuershen.nbtedit.function.handle.TagType;

public class EditProudSoul extends AbstractEditManager {

    @TagParameter(TYPE = TagType.INT)
    private int proudSoul;

    public EditProudSoul(){
        this.key = "ProudSoul";
        this.slotItem = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta itemMeta = this.slotItem.getItemMeta();
        itemMeta.setDisplayName("§2修改拔刀剑的荣耀值");
        this.slotItem.setItemMeta(itemMeta);
    }

    @EditDescribe( describe = {"§eⒺ §2修改拔刀荣耀值，请输入一个大小为-2147483648~2147483647的整数"})
    @Override
    public TagBase inputParams(TagBase tagBase, String... params) {
        TagCompound tagCompound = (TagCompound) tagBase;
        tagCompound.getMap().put(key, NewNBTTag.type((byte) 3).newTag(params[0]));
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
