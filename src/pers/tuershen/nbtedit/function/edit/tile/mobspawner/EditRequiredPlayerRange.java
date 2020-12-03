package pers.tuershen.nbtedit.function.edit.tile.mobspawner;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagBase;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagCompound;
import pers.tuershen.nbtedit.function.AbstractEditManager;
import pers.tuershen.nbtedit.function.annotation.EditDescribe;
import pers.tuershen.nbtedit.function.annotation.TagParameter;
import pers.tuershen.nbtedit.function.handle.NewNBTTag;
import pers.tuershen.nbtedit.function.handle.TagType;

import java.util.ArrayList;
import java.util.List;

public class EditRequiredPlayerRange extends AbstractEditManager {

    @TagParameter( TYPE = TagType.SHORT)
    private short requiredPlayerRange;

    public EditRequiredPlayerRange(){
        this.key = "RequiredPlayerRange";
        this.slotItem = new ItemStack(Material.MOB_SPAWNER);
        ItemMeta itemMeta = this.slotItem.getItemMeta();
        itemMeta.setDisplayName("§7设置刷怪笼起效所需玩家与刷怪笼之间的最近距离");
        List<String> lore = new ArrayList<>();
        lore.add("§eⒺ §b值得注意的是刷怪笼将会在每一个游戏刻检查当前世界是否有玩家进入该范围");
        lore.add("§eⒺ §b这个值一般默认为16");
        itemMeta.setLore(lore);
        this.slotItem.setItemMeta(itemMeta);
    }

    @EditDescribe( describe = {"§eⒺ §2请输入范围大小，取值范围"+Short.MIN_VALUE+"~"+Short.MAX_VALUE})
    @Override
    public TagBase inputParams(TagBase tagBase, String... params) {
        if (tagBase instanceof TagCompound){
            TagCompound tagCompound = (TagCompound) tagBase;
            tagCompound.getMap().put(key, NewNBTTag.type((byte)2).newTag(params[0]));
            return tagCompound;
        }
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
