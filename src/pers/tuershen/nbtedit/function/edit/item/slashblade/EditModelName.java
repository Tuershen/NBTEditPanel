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

public class EditModelName extends AbstractEditManager {

    @TagParameter( TYPE = TagType.STRING)
    private String modelName;

    public EditModelName(){
        this.key = "ModelName";
        this.slotItem = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta itemMeta = this.slotItem.getItemMeta();
        itemMeta.setDisplayName("§2修改拔刀剑模型");
        this.slotItem.setItemMeta(itemMeta);
    }


    @EditDescribe( describe = {"§eⒺ §2请输入模型路径"})
    @Override
    public TagBase inputParams(TagBase tagBase, String... params) {
        TagCompound tagCompound = (TagCompound) tagBase;
        tagCompound.getMap().put(key, NewNBTTag.type((byte) 8).newTag(params[0]));
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
