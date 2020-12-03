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

public class EditSpawnDelay extends AbstractEditManager {

    @TagParameter( TYPE = TagType.SHORT)
    private short minSpawnDelay;

    @TagParameter( TYPE = TagType.SHORT)
    private short maxSpawnDelay;

    public EditSpawnDelay(){
        this.slotItem = new ItemStack(Material.MOB_SPAWNER);
        ItemMeta itemMeta = this.slotItem.getItemMeta();
        itemMeta.setDisplayName("§7设置刷怪笼每次刷怪的时间间隔");
        List<String> lore = new ArrayList<>();
        lore.add("§eⒺ §bMinSpawnDelay：生成延迟的随机范围的下限");
        lore.add("§eⒺ §bMinSpawnDelay：生成延迟的随机范围的上限");
        lore.add("§eⒺ §b注意：单位为tick 20tick = 1秒");
        itemMeta.setLore(lore);
        this.slotItem.setItemMeta(itemMeta);
    }

    @EditDescribe( describe = {
            "§eⒺ §2请输入时间间隔，取值范围"+Short.MIN_VALUE+"~"+Short.MAX_VALUE,
            "§eⒺ §2第一个参数为随机范围的下限(MinSpawnDelay)",
            "§eⒺ §2第一个参数为随机范围的上限(MaxSpawnDelay)"})
    @Override
    public TagBase inputParams(TagBase tagBase, String... params) {
        if (tagBase instanceof TagCompound){
            TagCompound tagCompound = (TagCompound) tagBase;
            tagCompound.getMap().put("MinSpawnDelay", NewNBTTag.type((byte)2).newTag(params[0]));
            tagCompound.getMap().put("MaxSpawnDelay", NewNBTTag.type((byte)2).newTag(params[1]));
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
        return "SpawnDelay";
    }

    @Override
    public ItemStack getSlotItem() {
        return this.slotItem;
    }
}
