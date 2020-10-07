package pers.tuershen.nbtedit.setting;

import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagBase;
import org.bukkit.inventory.ItemStack;

public interface TagBaseStack {

    ItemStack setStyle(Object key, TagBase tagBase, boolean isNumber);



}
