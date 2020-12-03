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

public class EditSpawnRange extends AbstractEditManager {

    @TagParameter( TYPE = TagType.SHORT)
    private short spawnRange;

    public EditSpawnRange(){
        this.key = "SpawnRange";
        this.slotItem = new ItemStack(Material.MOB_SPAWNER);
        ItemMeta itemMeta = this.slotItem.getItemMeta();
        itemMeta.setDisplayName("§7设置刷怪笼可以随机生成实体的范围");
        List<String> lore = new ArrayList<>();
        lore.add("§eⒺ §b生成区域为正方体，含刷怪笼所在的方块，并以刷怪笼的 X、Z 坐标为中心的周遭区域——不是以刷怪笼本身为中心");
        lore.add("§eⒺ §b和以刷怪笼底部的Y坐标为中心的两格高的区域");
        lore.add("§eⒺ §b允许实体可以有足够的空间在这块区域的顶部和底部再向下一格的区域内生成");
        lore.add("§eⒺ §b这块区域中生成实体的顶点的Y坐标均为整数，同时水平的X、Z坐标是与刷怪笼自身相似的浮点值。默认值是4");
        lore.add("§eⒺ §b范围越大刷怪就会越分散，相反越小刷怪就会越内聚");
        itemMeta.setLore(lore);
        this.slotItem.setItemMeta(itemMeta);
    }

    @EditDescribe( describe = {"§eⒺ §2请输入随机生成实体的范围，取值范围"+Short.MIN_VALUE+"~"+Short.MAX_VALUE})
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
