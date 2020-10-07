package pers.tuershen.nbtedit.setting;

import org.bukkit.entity.Player;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagBase;

import pers.tuershen.nbtedit.function.AbstractEditManager;

public interface EditPanelManager {

    TagBase addTag(String msg, Player player, int slot, TagBase tagBase);

    TagBase addFunction(String msg, Player player, TagBase tagBase, AbstractEditManager editManager);

    TagBase setTag(TagBase tag, Player player, Object key, TagBase tagBase);





}
