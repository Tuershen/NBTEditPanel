package pers.tuershen.nbtedit.function.edit.item.mate;

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

import java.util.Map;

public class EditItemDisplay extends AbstractEditManager {

    @TagParameter( TYPE = TagType.STRING)
    private String display;

    public EditItemDisplay(){
        this.key = "display";
        this.slotItem = new ItemStack(Material.BOOK);
        ItemMeta itemMeta = this.slotItem.getItemMeta();
        itemMeta.setDisplayName("§2设置物品的名称，支持颜色代码");
        this.slotItem.setItemMeta(itemMeta);

    }


    @EditDescribe( describe = {"§eⒺ §2修改物品名称，请需要输入一个String类型的参数，支持颜色代码"})
    @Override
    public TagBase inputParams(TagBase tagBase, String... params) {
        if (!(tagBase instanceof TagCompound)) return tagBase;
        TagCompound tagCompound = (TagCompound) ((TagCompound) tagBase).getMap().get(this.key);
        if (tagCompound == null){
            tagCompound = new TagCompound();
        }
        Map<String, TagBase> map = tagCompound.getMap();
        map.put("Name", NewNBTTag.type((byte)8).newTag(params[0]));
        tagCompound.setMap(map);

        TagCompound base = (TagCompound) tagBase;
        base.getMap().put(key, tagCompound);
        return base;
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
