package pers.tuershen.nbtedit.function.edit.item.mate;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagBase;
;
import pers.tuershen.nbtedit.function.annotation.EditDescribe;
import pers.tuershen.nbtedit.function.annotation.TagParameter;
import pers.tuershen.nbtedit.function.handle.TagType;



public class EditRemoveLoreKeyword extends EditLoreKeywordSubstitution {


    @TagParameter( TYPE = TagType.STRING)
    private String old;

    public EditRemoveLoreKeyword(){
        this.key = "display";
        this.slotItem = new ItemStack(Material.BOOK);
        ItemMeta itemMeta = this.slotItem.getItemMeta();
        itemMeta.setDisplayName("§2快速删除lore里面的指定关键字");
        this.slotItem.setItemMeta(itemMeta);
    }


    @EditDescribe( describe = {
            "§eⒺ §2lore关键字替换",
            "§eⒺ §2第一个参数为需要被删除的关键字"})
    @Override
    public TagBase inputParams(TagBase tagBase, String... params) {
        return super.inputParams(tagBase,params[0],"");
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
