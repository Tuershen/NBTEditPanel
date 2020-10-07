package pers.tuershen.nbtedit.event;

import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagBase;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.nbtedit.function.AbstractEditManager;
public interface HandleMessagesItemEvent {

    ItemStack addSlotPositionNBTTag(String msg, int slot, ItemStack itemStack, Player player);

    ItemStack setSlotPositionNBTTag(String msg, Object key, ItemStack itemStack, Player player, byte type);

    ItemStack addFunctionNBTTag(String msg, Player player, ItemStack itemStack, AbstractEditManager editManager);

    void triggerAddEvent(int slot, Player player);

    void triggerSetEvent(Object key, int slot, TagBase tagBase, Player player);

    void triggerAddFunctionNBT(AbstractEditManager editManager, Player player);






}
