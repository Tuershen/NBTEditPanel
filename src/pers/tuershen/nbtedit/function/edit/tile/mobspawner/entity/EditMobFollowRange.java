package pers.tuershen.nbtedit.function.edit.tile.mobspawner.entity;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagBase;
import pers.tuershen.nbtedit.function.annotation.EditDescribe;
import pers.tuershen.nbtedit.function.annotation.TagParameter;
import pers.tuershen.nbtedit.function.handle.TagType;

import java.util.ArrayList;
import java.util.List;

public class EditMobFollowRange extends EditMobSpawnManager {

    @TagParameter( TYPE = TagType.DOUBLE)
    private double attackDamage;

    public EditMobFollowRange(){
        this.key = "generic.followRange";
        this.slotItem = new ItemStack(Material.MOB_SPAWNER);
        ItemMeta itemMeta = this.slotItem.getItemMeta();
        itemMeta.setDisplayName("§7设置刷怪笼中的实体追踪范围");
        List<String> lore = new ArrayList<>();
        lore.add("§eⒺ §d这个生物追踪玩家或者其他生物的最大范围，以方块数为单位");
        lore.add("§eⒺ §d目标离开这个区域意味着它们将停止追踪");
        lore.add("§eⒺ §d目前大多数生物这个值为16，而僵尸则有40");
        this.slotItem.setItemMeta(itemMeta);
    }

    @EditDescribe( describe = {"§eⒺ §2请输入生物实体追踪范围，取值范围"+Double.MIN_VALUE+"~"+Double.MAX_VALUE})
    @Override
    public TagBase inputParams(TagBase tagBase, String... params) {
        return super.inputParams(tagBase,params[0]);
    }
}
