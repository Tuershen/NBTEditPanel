package pers.tuershen.nbtedit.setting;

import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagBase;
import org.bukkit.entity.Player;

public interface SingleNBT {

    TagBase newNBT(String data, Player player);

}