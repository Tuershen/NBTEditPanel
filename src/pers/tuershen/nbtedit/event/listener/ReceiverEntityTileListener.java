package pers.tuershen.nbtedit.event.listener;

import org.bukkit.event.player.AsyncPlayerChatEvent;
import pers.tuershen.nbtedit.panel.edit.AbstractPanelEditEntityTile;
import pers.tuershen.nbtedit.event.sub.ReceiverEntityTileSubject;
import pers.tuershen.nbtedit.function.AbstractEditManager;
import pers.tuershen.nbtedit.event.listener.bukkit.PushMessages;
import pers.tuershen.nbtedit.setting.handle.HandleEventTypeEnum;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ReceiverEntityTileListener implements PushMessages, ReceiverEntityTileSubject {


    private static Map<UUID, AbstractPanelEditEntityTile> panelEditEntityMap = new HashMap<>();

    private static Map<UUID, Integer> slotMap = new HashMap<>();

    private static Map<UUID, HandleEventTypeEnum> handleEventTypeEnumMap = new HashMap<>();

    private static Map<UUID, Object> objectKeyMap = new HashMap<>();

    private static Map<UUID, Byte> tagTypeMap = new HashMap<>();

    private static Map<UUID, AbstractEditManager> managerMap = new HashMap<>();

    /**
     * 添加NBT事件
     * @param uid uid
     * @param panelEditEntity 添加实体
     * @param slot 界面坐标
     * @param eventTypeEnum 事件类型
     */
    @Override public void
    registerReceiverAddMsgPlayer(
            UUID uid,
            AbstractPanelEditEntityTile panelEditEntity,
            int slot,
            HandleEventTypeEnum eventTypeEnum) {
        panelEditEntityMap.put(uid, panelEditEntity);
        slotMap.put(uid,slot);
        handleEventTypeEnumMap.put(uid, eventTypeEnum);

    }

    /**
     *
     * @param uuid
     * @param panelEditEntity
     * @param key
     * @param type
     * @param eventTypeEnum
     */
    @Override public void
    registerReceiverSetMsgPlayer(
            UUID uuid,
            AbstractPanelEditEntityTile panelEditEntity,
            Object key,
            byte type,
            HandleEventTypeEnum eventTypeEnum) {
        panelEditEntityMap.put(uuid, panelEditEntity);
        objectKeyMap.put(uuid,key);
        tagTypeMap.put(uuid,type);
        handleEventTypeEnumMap.put(uuid, eventTypeEnum);
    }

    @Override public void
    registerReceiverAddFunctionMsgPlayer(
            UUID uuid,
            AbstractPanelEditEntityTile panelEditEntity,
            AbstractEditManager editManager,
            HandleEventTypeEnum handleEventTypeEnum) {
        panelEditEntityMap.put(uuid, panelEditEntity);
        managerMap.put(uuid, editManager);
        handleEventTypeEnumMap.put(uuid, handleEventTypeEnum);
    }


    @Override
    public void push(AsyncPlayerChatEvent event) {
        if (panelEditEntityMap.containsKey(event.getPlayer().getUniqueId())){
            UUID uuid = event.getPlayer().getUniqueId();
            HandleEventTypeEnum handleEventTypeEnum = handleEventTypeEnumMap.get(uuid);
            AbstractPanelEditEntityTile editEntity = panelEditEntityMap.get(uuid);
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
