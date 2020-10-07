package pers.tuershen.nbtedit.function.edit.item.slashblade;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagBase;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagCompound;
import pers.tuershen.nbtedit.function.AbstractEditManager;
import pers.tuershen.nbtedit.function.annotation.EditDescribe;
import pers.tuershen.nbtedit.function.annotation.TagParameter;
import pers.tuershen.nbtedit.function.handle.NewNBTTag;
import pers.tuershen.nbtedit.function.handle.TagType;

import java.util.ArrayList;
import java.util.List;

public class EditSummonedSwordColor extends AbstractEditManager {

    @TagParameter(TYPE = TagType.INT)
    private int summonedSwordColor;

    public EditSummonedSwordColor(){
        this.key = "SummonedSwordColor";
        this.slotItem = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta itemMeta = this.slotItem.getItemMeta();
        itemMeta.setDisplayName("§2修改拔刀剑的剑气颜色");
        List<String> lore = new ArrayList<>();
        lore.add("§eⒺ §b这里只列举部分");
        lore.add("§eⒺ §b有兴趣百度搜索颜色的十六进制代码，然后再转成十进制输入即可");
        lore.add("§eⒺ §bⓇ §a牡丹红    §e⇒ §716711935");
        lore.add("§eⒺ §bⓇ §a红色      §e⇒ §716711680");
        lore.add("§eⒺ §bⓇ §a蓝色      §e⇒ §7255");
        lore.add("§eⒺ §bⓇ §a青色      §e⇒ §765535");
        lore.add("§eⒺ §bⓇ §a黄色      §e⇒ §716776960");
        lore.add("§eⒺ §bⓇ §a霓虹粉红  §e⇒ §716740039");
        lore.add("§eⒺ §bⓇ §a艳粉红色  §e⇒ §7167199022");
        lore.add("§eⒺ §bⓇ §a春绿色    §e⇒ §765407");
        lore.add("§eⒺ §bⓇ §a中春绿色  §e⇒ §78388352");
        lore.add("§eⒺ §bⓇ §a亮天蓝色  §e⇒ §73715294");
        lore.add("§eⒺ §bⓇ §a白色      §e⇒ §716777215");
        itemMeta.setLore(lore);
        this.slotItem.setItemMeta(itemMeta);
    }


    @EditDescribe( describe = {"§eⒺ §2请输入十进制的颜色代码，取值范围为"+Integer.MIN_VALUE+"~"+Integer.MAX_VALUE})
    @Override
    public TagBase inputParams(TagBase tagBase, String... params) {
        if (!(tagBase instanceof TagCompound)) return tagBase;
        TagCompound tagCompound = (TagCompound) tagBase;
        tagCompound.getMap().put(key, NewNBTTag.type((byte) 3).newTag(params[0]));
        return tagCompound;
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
