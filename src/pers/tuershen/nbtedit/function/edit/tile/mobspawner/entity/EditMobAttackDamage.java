package pers.tuershen.nbtedit.function.edit.tile.mobspawner.entity;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagBase;
import pers.tuershen.nbtedit.function.annotation.EditDescribe;
import pers.tuershen.nbtedit.function.annotation.TagParameter;
import pers.tuershen.nbtedit.function.handle.TagType;

import java.util.ArrayList;
import java.util.List;

public class EditMobAttackDamage extends EditMobSpawnManager {

    @TagParameter( TYPE = TagType.DOUBLE)
    private double attackDamage;

    public EditMobAttackDamage(){
        this.key = "generic.attackDamage";
        this.slotItem = new ItemStack(Material.MOB_SPAWNER);
        ItemMeta itemMeta = this.slotItem.getItemMeta();
        itemMeta.setDisplayName("§7设置刷怪笼中的实体攻击力");
        List<String> lore = new ArrayList<>();
        itemMeta.setLore(lore);
        this.slotItem.setItemMeta(itemMeta);
    }

    @EditDescribe( describe = {"§eⒺ §2请输入生物实体攻击力，取值范围"+Double.MIN_VALUE+"~"+Double.MAX_VALUE})
    @Override
    public TagBase inputParams(TagBase tagBase, String... params) {
        return super.inputParams(tagBase,params[0]);
    }

}