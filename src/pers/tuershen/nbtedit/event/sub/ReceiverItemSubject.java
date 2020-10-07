package pers.tuershen.nbtedit.event.sub;

import org.bukkit.inventory.ItemStack;
import pers.tuershen.nbtedit.panel.edit.AbstractPanelEditItem;
import pers.tuershen.nbtedit.function.AbstractEditManager;
import pers.tuershen.nbtedit.setting.handle.HandleEventTypeEnum;

import java.util.UUID;

public interface ReceiverItemSubject {

    void registerReceiverAddMsgPlayer(UUID uid, AbstractPanelEditItem panelEdit, int slot, ItemStack itemStack, HandleEventTypeEnum eventTypeEnum);

    void registerReceiverSetMsgPlayer(UUID uuid, AbstractPanelEditItem panelEdit, Object key, ItemStack itemStack, byte type, HandleEventTypeEnum eventTypeEnum);

    void registerReceiverAddFunctionMsgPlayer(UUID uuid, AbstractPanelEditItem editItem, AbstractEditManager editManager, ItemStack itemStack, HandleEventTypeEnum handleEventTypeEnum);




}
