package pers.tuershen.nbtedit.setting;

import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagBase;
import org.bukkit.entity.Player;

public interface ButtonPromptMessage {

    String receiverMsg();

    TagBase newNBTTag(String data, Player player);


}
