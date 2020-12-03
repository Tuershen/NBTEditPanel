package pers.tuershen.nbtedit.function.edit.item.mate;

import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagBase;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagCompound;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagList;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pers.tuershen.nbtedit.function.AbstractEditManager;
import pers.tuershen.nbtedit.function.annotation.EditDescribe;
import pers.tuershen.nbtedit.function.annotation.TagParameter;
import pers.tuershen.nbtedit.function.handle.NewNBTTag;
import pers.tuershen.nbtedit.function.handle.TagType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditEnchanting  extends AbstractEditManager {


    @TagParameter(TYPE = TagType.SHORT)
    private short id;


    @TagParameter(TYPE = TagType.SHORT)
    private short lvl;


    public EditEnchanting(){
        this.key = "ench";
        this.slotItem = new ItemStack(Material.NETHER_STAR);
        ItemMeta itemMeta = this.slotItem.getItemMeta();
        itemMeta.setDisplayName("§2快速添加附魔");
        List<String> lore = new ArrayList<>();
        lore.add("§eⒺ §2需要输入附魔id(id)和等级(lvl)");
        lore.add("§eⒺ §b0 §7- §a防御，Protection (头盔，装甲，鞋子，裤子)");
        lore.add("§eⒺ §b1 §7- §a火焰防御，Fire Protection (头盔，装甲，鞋子，裤子)");
        lore.add("§eⒺ §b2 §7- §a摔伤减半，Feather Falling (鞋子)");
        lore.add("§eⒺ §b3 §7- §a爆炸防御，Blast Protection (头盔，装甲，鞋子，裤子)");
        lore.add("§eⒺ §b4 §7- §a远程攻击防御，Projectile Protection (头盔，装甲，鞋子，裤子)");
        lore.add("§eⒺ §b5 §7- §a水下呼吸，Respiration (头盔)");
        lore.add("§eⒺ §b6 §7- §a水下挖掘，Aqua Affinity (头盔)");
        lore.add("§eⒺ §b7 §7- §a伤害反射，Thorns (头盔，装甲，鞋子，裤子)");
        lore.add("§eⒺ §b8 §7- §a深海探索者，Thorns (头盔)，高版本才会有");
        lore.add("§eⒺ §b9 §7- §a冰霜行者，允许水上行走，Thorns (鞋子)");
        lore.add("§eⒺ §b10 §7- §a绑定诅咒，被诅咒物品穿在身上后除非玩家处于创造模式，否则无法卸下，Thorns (头盔，装甲，鞋子，裤子, 物品)");
        lore.add("§eⒺ §b16 §7- §a锋利，Sharpness (剑)");
        lore.add("§eⒺ §b17 §7- §a亡灵杀手，Smite (剑)");
        lore.add("§eⒺ §b18 §7- §a节肢杀手，Bane Of Arthropods (剑)");
        lore.add("§eⒺ §b19 §7- §a击退，Knock Back (剑)");
        lore.add("§eⒺ §b20 §7- §a火焰附加，Fire Aspect (剑)");
        lore.add("§eⒺ §b21 §7- §a抢夺，Looting (剑)");
        lore.add("§eⒺ §b22 §7- §a横扫，增加横扫攻击伤害，Looting (剑)");
        lore.add("§eⒺ §b32 §7- §a挖掘效率，Efficiency (稿子，斧子，铲子)");
        lore.add("§eⒺ §b33 §7- §a精准采集，Silk Touch (稿子，斧子，铲子)");
        lore.add("§eⒺ §b35 §7- §a时运挖掘，Fortune (稿子，斧子，铲子)");
        lore.add("§eⒺ §b48 §7- §a力量，Power (弓箭)");
        lore.add("§eⒺ §b49 §7- §a弓箭击退，Punch (弓箭)");
        lore.add("§eⒺ §b50 §7- §a火元素，Flame (弓箭)");
        lore.add("§eⒺ §b51 §7- §a无限弓箭，Infinity (弓箭)");
        lore.add("§eⒺ §b61 §7- §a海之眷顾");
        lore.add("§eⒺ §b62 §7- §a饵钓");
        lore.add("§eⒺ §b71 §7- §a经验修补");
        lore.add("§eⒺ §b71 §7- §a消除诅咒");
        itemMeta.setLore(lore);
        this.slotItem.setItemMeta(itemMeta);
    }


    @EditDescribe( describe = {"§eⒺ §2添加附魔，需要两个Short类型的参数","§eⒺ §b第一个为附魔种类(id)","§eⒺ §b第二参数为附魔等级(lvl)"})
    @Override
    public TagBase inputParams(TagBase tagBase, String... params) {
        if (!(tagBase instanceof TagCompound)) return tagBase;
        TagList tagList = (TagList) ((TagCompound)tagBase).getMap().get(key);
        TagCompound tagCompound = new TagCompound();
        Map<String,TagBase> map = new HashMap<>();
        map.put("id", NewNBTTag.type((byte) 2).newTag(params[0]));
        map.put("lvl", NewNBTTag.type((byte) 2).newTag(params[1]));
        tagCompound.setMap(map);
        if (tagList == null) {
            tagList = new TagList();
        }
        tagList.add(tagCompound);
        TagCompound base = (TagCompound) tagBase;
        base.getMap().put(key, tagList);
        return base;
    }

    @Override
    public ItemStack getSlotItem() {
        return this.slotItem;
    }

    @Override
    public int getIndex() {
        return 0;
    }

    @Override
    public String getKey() {
        return this.key;
    }
}
