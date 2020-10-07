package pers.tuershen.nbtedit.function.edit.item.mate;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagBase;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagCompound;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagList;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagString;
import pers.tuershen.nbtedit.function.AbstractEditManager;
import pers.tuershen.nbtedit.function.annotation.EditDescribe;
import pers.tuershen.nbtedit.function.annotation.TagParameter;
import pers.tuershen.nbtedit.function.handle.TagType;

import java.util.List;

public class EditLoreKeywordSubstitution extends AbstractEditManager {

    @TagParameter( TYPE = TagType.STRING)
    private String old;

    @TagParameter( TYPE = TagType.STRING)
    private String newString;

    public EditLoreKeywordSubstitution(){
        this.key = "display";
        this.slotItem = new ItemStack(Material.BOOK);
        ItemMeta itemMeta = this.slotItem.getItemMeta();
        itemMeta.setDisplayName("§2快速替换lore里面的指定关键字");
        this.slotItem.setItemMeta(itemMeta);

    }

    @EditDescribe( describe = {
            "§eⒺ §2lore关键字替换",
            "§eⒺ §2第一个参数为需要被替换的关键字",
            "§eⒺ §2第二个参数为新的关键字"})
    @Override
    public TagBase inputParams(TagBase tagBase, String... params) {
        if (!(tagBase instanceof TagCompound)) return tagBase;
        TagCompound tagCompound = (TagCompound) ((TagCompound) tagBase).getMap().get(this.key);
        if (tagCompound == null) return tagBase;
        TagList lore = (TagList) tagCompound.getMap().get("Lore");
        if (lore == null) return tagBase;
        List<TagBase> list = lore.getData();
        for (int i = 0; i < list.size() ; i++) {
            TagBase content = list.get(i);
            if (content instanceof TagString){
                TagString string = (TagString) content;
                String str = string.getData().replace(params[0],params[1]);
                list.set(i,new TagString(str));
            }
        }
        lore.setData(list);
        tagCompound.getMap().put("Lore", lore);
        ((TagCompound) tagBase).getMap().put(key, tagCompound);
        return tagBase;
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
