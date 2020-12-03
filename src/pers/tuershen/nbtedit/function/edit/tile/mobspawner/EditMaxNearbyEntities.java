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

public class EditMaxNearbyEntities extends AbstractEditManager {

    @TagParameter( TYPE = TagType.SHORT)
    private short maxNearbyEntities;

    public EditMaxNearbyEntities(){
        this.key = "MaxNearbyEntities";
        this.slotItem = new ItemStack(Material.MOB_SPAWNER);
        ItemMeta itemMeta = this.slotItem.getItemMeta();
        itemMeta.setDisplayName("§7设置刷怪笼周围实体id相同的实体最大存在数量");
        List<String> lore = new ArrayList<>();
        lore.add("§eⒺ §b实体ID与刷怪笼EntityID相同的实体在刷怪笼周遭最大存在数量");
        lore.add("§eⒺ §b需要注意的是这与一个生物的碰撞箱有关而不是他的实际位置");
        lore.add("§eⒺ §b也就是任何符合的在一个区块中任何区域中的实体但凡处于这个检查区域中，检查的是他们的ID和碰撞箱");
        lore.add("§eⒺ §b相对的，如果只检查他们是否在这个区域里，将会有很多实体没有被判断进去而导致继续生成，这将会造成不小的卡顿！");
        lore.add("§eⒺ §b这个值一般默认为6");
        itemMeta.setLore(lore);
        this.slotItem.setItemMeta(itemMeta);
    }

    @EditDescribe( describe = {"§eⒺ §2请输入实体最大存在数量，取值范围"+Short.MIN_VALUE+"~"+Short.MAX_VALUE})
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
