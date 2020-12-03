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



public class EditSpawnCount extends AbstractEditManager {

    @TagParameter( TYPE = TagType.SHORT)
    private short spawnCount;

    public EditSpawnCount(){
        this.key = "SpawnCount";
        this.slotItem = new ItemStack(Material.MOB_SPAWNER);
        ItemMeta itemMeta = this.slotItem.getItemMeta();
        itemMeta.setDisplayName("§7设置刷怪笼每次尝试生成生物的数量");
        this.slotItem.setItemMeta(itemMeta);
    }

    @EditDescribe( describe = {"§eⒺ §2请输入生物生成数量，取值范围"+Short.MIN_VALUE+"~"+Short.MAX_VALUE})
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
