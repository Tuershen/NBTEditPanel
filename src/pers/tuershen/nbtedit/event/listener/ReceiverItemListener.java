package pers.tuershen.nbtedit.event.listener;

import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.nbtedit.panel.edit.AbstractPanelEditItem;
import pers.tuershen.nbtedit.event.sub.ReceiverItemSubject;
import pers.tuershen.nbtedit.function.AbstractEditManager;
import pers.tuershen.nbtedit.event.listener.bukkit.PushMessages;
import pers.tuershen.nbtedit.setting.handle.HandleEventTypeEnum;

import java.util.*;

public class ReceiverItemListener implements ReceiverItemSubject, PushMessages {

    private static Map<UUID, AbstractPanelEditItem> uuidAbstractPanelEditMap = new HashMap<>();

    private static Map<UUID, Integer> enumTypeMap = new HashMap<>();

    private static Map<UUID, ItemStack> handStack = new HashMap<>();

    private static Map<UUID, HandleEventTypeEnum> handleEventTypeEnumMap = new HashMap<>();

    private static Map<UUID, Byte> slotPositionMap = new HashMap<>();

    private static Map<UUID, Object> compoundKeyMap = new HashMap<>();

    private static Map<UUID, AbstractEditManager> managerMap = new HashMap<>();


    /**
     *
     * @param uid
     * @param panelEdit
     * @param slot
     * @param itemStack
     * @param eventTypeEnum
     */
    @Override
    public void registerReceiverAddMsgPlayer(UUID uid, AbstractPanelEditItem panelEdit, int slot, ItemStack itemStack,
                                                HandleEventTypeEnum eventTypeEnum) {
        uuidAbstractPanelEditMap.put(uid,panelEdit);
        enumTypeMap.put(uid,slot);
        handStack.put(uid,itemStack);
        handleEventTypeEnumMap.put(uid, eventTypeEnum);
    }

    @Override
    public void registerReceiverSetMsgPlayer(UUID uid, AbstractPanelEditItem panelEdit, Object key, ItemStack itemStack,
                                             byte type, HandleEventTypeEnum eventTypeEnum) {
        uuidAbstractPanelEditMap.put(uid,panelEdit);
        compoundKeyMap.put(uid,key);
        handStack.put(uid,itemStack);
        handleEventTypeEnumMap.put(uid, eventTypeEnum);
        slotPositionMap.put(uid,type);

    }

    @Override
    public void registerReceiverAddFunctionMsgPlayer(UUID uuid,
                                                     AbstractPanelEditItem editItem,
                                                     AbstractEditManager editManager,
                                                     ItemStack itemStack,
                                                     HandleEventTypeEnum handleEventTypeEnum) {
        uuidAbstractPanelEditMap.put(uuid,editItem);
        managerMap.put(uuid, editManager);
        handleEventTypeEnumMap.put(uuid, handleEventTypeEnum);
        handStack.put(uuid,itemStack);
    }

    public void push(AsyncPlayerChatEvent event){
        if (uuidAbstractPanelEditMap.containsKey(event.getPlayer().getUniqueId())){
            UUID uuid = event.getPlayer().getUniqueId();
            HandleEventTypeEnum handleEventTypeEnum = handleEventTypeEnumMap.get(uuid);
            AbstractPanelEditItem abstractPanelEdit = uuidAbstractPanelEditMap.get(uuid);
            switch (handleEventTypeEnum){
                case ADD:
                    ItemStack addItem = abstractPanelEdit.handleMessagesItemEvent().addSlotPositionNBTTag(
                            event.getMessage(),
                            enumTypeMap.get(uuid),
                            handStack.get(uuid),
                            event.getPlayer());
                    event.getPlayer().getInventory().setItemInHand(addItem);
                    uuidAbstractPanelEditMap.remove(uuid);
                    enumTypeMap.remove(uuid);
                    handStack.remove(uuid);
                    handleEventTypeEnumMap.remove(uuid);
                    event.setCancelled(true);
                    break;
                case SET:
                    ItemStack setItem = abstractPanelEdit.handleMessagesItemEvent().setSlotPositionNBTTag(
                            event.getMessage(),
                            compoundKeyMap.get(uuid),
                            handStack.get(uuid),
                            event.getPlayer(),
                            slotPositionMap.get(uuid));
                    event.getPlayer().getInventory().setItemInHand(setItem);
                    uuidAbstractPanelEditMap.remove(uuid);
                    handStack.remove(uuid);
                    handleEventTypeEnumMap.remove(uuid);
                    slotPositionMap.remove(uuid);
                    event.setCancelled(true);
                    break;
                case FUNCTION_CALL:
                    ItemStack addFunction = abstractPanelEdit.addFunctionNBTTag(
                            event.getMessage(),
                            event.getPlayer(),
                            handStack.get(uuid),
                            managerMap.get(uuid));
                    event.getPlayer().getInventory().setItemInHand(addFunction);
                    uuidAbstractPanelEditMap.remove(uuid);
                    managerMap.remove(uuid);
                    handleEventTypeEnumMap.remove(uuid);
                    handStack.remove(uuid);
                    event.setCancelled(true);
                    break;
            }
        }
    }
}



