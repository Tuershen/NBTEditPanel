package pers.tuershen.nbtedit.function.edit.tile.mobspawner.entity;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagBase;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagCompound;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagList;
import pers.tuershen.nbtedit.function.annotation.EditDescribe;
import pers.tuershen.nbtedit.function.annotation.TagParameter;
import pers.tuershen.nbtedit.function.handle.NewNBTTag;
import pers.tuershen.nbtedit.function.handle.TagType;

import java.util.ArrayList;
import java.util.List;

public class EditMobEntityMaxHealth extends EditMobSpawnManager {


    @TagParameter( TYPE = TagType.DOUBLE)
    private double maxHealth;

    public EditMobEntityMaxHealth(){
        this.key = "generic.maxHealth";
        this.slotItem = new ItemStack(Material.MOB_SPAWNER);
        ItemMeta itemMeta = this.slotItem.getItemMeta();
        itemMeta.setDisplayName("§7设置刷怪笼中的实体最大生命值");
        List<String> lore = new ArrayList<>();
        lore.add("§eⒺ §b最大生命值不会超过你服务器的最大上限");
        itemMeta.setLore(lore);
        this.slotItem.setItemMeta(itemMeta);
    }

    @EditDescribe( describe = {"§eⒺ §2请输入生物生成的最大生命值，取值范围"+Double.MIN_VALUE+"~"+Double.MAX_VALUE})
    @Override
    public TagBase inputParams(TagBase tagBase, String... params) {
        TagCompound tagCompound = (TagCompound) super.inputParams(tagBase,params);
        TagList spawnPotentials = (TagList) tagCompound.getMap().get("SpawnPotentials");
        List<TagBase> entityArray = spawnPotentials.getData();
        for (int i = 0; i < entityArray.size(); i++) {
            TagCompound entityWeight = (TagCompound) entityArray.get(i);
            TagCompound entity = (TagCompound) entityWeight.getMap().get("Entity");
            entity.getMap().put("Health", NewNBTTag.type((byte)5).newTag(params[0]));
            entityWeight.getMap().put("Entity", entity);
            entityArray.set(i,entityWeight);
        }
        tagCompound.getMap().put("SpawnPotentials", tagCompound);
        return tagCompound;
    }





}
