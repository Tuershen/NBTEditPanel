package pers.tuershen.nbtedit.event;

import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagBase;
import org.bukkit.entity.Player;
import pers.tuershen.nbtedit.function.AbstractEditManager;


public interface HandleMessagesEntityEvent {

    void addSlotPositionNBTTag(String msg, int slot, Player player);

    void setSlotPositionNBTTag(String msg, Object key, Player player, byte type);

    void addFunctionNBTTag(String msg, Player player, AbstractEditManager editManager);

    void triggerAddEvent(int slot, Player player);

    void triggerSetEvent(Object key, int slot, TagBase tagBase, Player player);

    void triggerAddFunctionNBT(AbstractEditManager editManager, Player player);



}
