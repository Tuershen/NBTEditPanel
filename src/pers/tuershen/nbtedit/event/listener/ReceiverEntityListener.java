package pers.tuershen.nbtedit.event.listener;

import org.bukkit.event.player.AsyncPlayerChatEvent;
import pers.tuershen.nbtedit.panel.edit.AbstractPanelEditEntity;
import pers.tuershen.nbtedit.event.sub.ReceiverEntitySubject;
import pers.tuershen.nbtedit.function.AbstractEditManager;
import pers.tuershen.nbtedit.event.listener.bukkit.PushMessages;
import pers.tuershen.nbtedit.setting.handle.HandleEventTypeEnum;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ReceiverEntityListener implements ReceiverEntitySubject, PushMessages {


    private static Map<UUID, AbstractPanelEditEntity> panelEditEntityMap = new HashMap<>();

    private static Map<UUID, Integer> slotMap = new HashMap<>();

    private static Map<UUID, HandleEventTypeEnum> handleEventTypeEnumMap = new HashMap<>();

    private static Map<UUID, Object> objectKeyMap = new HashMap<>();

    private static Map<UUID, Byte> tagTypeMap = new HashMap<>();

    private static Map<UUID, AbstractEditManager> managerMap = new HashMap<>();



    @Override
    public void registerReceiverAddMsgPlayer(UUID uid, AbstractPanelEditEntity panelEdit,
                                             int slot, HandleEventTypeEnum eventTypeEnum) {
        panelEditEntityMap.put(uid, panelEdit);
        slotMap.put(uid,slot);
        handleEventTypeEnumMap.put(uid, eventTypeEnum);

    }

    @Override
    public void registerReceiverSetMsgPlayer(UUID uuid, AbstractPanelEditEntity panelEdit,
                                             Object key, byte type, HandleEventTypeEnum eventTypeEnum) {
        panelEditEntityMap.put(uuid, panelEdit);
        objectKeyMap.put(uuid,key);
        tagTypeMap.put(uuid,type);
        handleEventTypeEnumMap.put(uuid, eventTypeEnum);
    }

    @Override
    public void registerReceiverAddFunctionMsgPlayer(UUID uuid, AbstractPanelEditEntity editEntity, AbstractEditManager editManager,
                                                     HandleEventTypeEnum handleEventTypeEnum) {
        panelEditEntityMap.put(uuid, editEntity);
        managerMap.put(uuid, editManager);
        handleEventTypeEnumMap.put(uuid, handleEventTypeEnum);

    }

    @Override
    public void push(AsyncPlayerChatEvent event) {
        if (panelEditEntityMap.containsKey(event.getPlayer().getUniqueId())){
            UUID uuid = event.getPlayer().getUniqueId();
            HandleEventTypeEnum handleEventTypeEnum = handleEventTypeEnumMap.get(uuid);
            AbstractPanelEditEntity editEntity = panelEditEntityMap.get(uuid);
            switch (handleEventTypeEnum){
                case ADD:
                    editEntity.handleMessagesEntityEvent().addSlotPositionNBTTag(
                            event.getMessage(),
                            slotMap.get(uuid),
                            event.getPlayer());
                    panelEditEntityMap.remove(uuid);
                    slotMap.remove(uuid);
                    handleEventTypeEnumMap.remove(uuid);
                    event.setCancelled(true);
                    break;
                case SET:
                    editEntity.handleMessagesEntityEvent().setSlotPositionNBTTag(
                            event.getMessage(),
                            objectKeyMap.get(uuid),
                            event.getPlayer(),
                            tagTypeMap.get(uuid));
                    panelEditEntityMap.remove(uuid);
                    handleEventTypeEnumMap.remove(uuid);
                    objectKeyMap.remove(uuid);
                    tagTypeMap.remove(uuid);
                    event.setCancelled(true);
                    break;
                case FUNCTION_CALL:
                    editEntity.handleMessagesEntityEvent().addFunctionNBTTag(
                            event.getMessage(),
                            event.getPlayer(),
                            managerMap.get(uuid)
                    );
                    panelEditEntityMap.remove(uuid);
                    managerMap.remove(uuid);
                    handleEventTypeEnumMap.remove(uuid);
                    event.setCancelled(true);
                    break;
            }
        }
    }
}
