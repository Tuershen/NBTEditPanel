package pers.tuershen.nbtedit.event.sub;

import pers.tuershen.nbtedit.panel.edit.AbstractPanelEditEntityTile;
import pers.tuershen.nbtedit.function.AbstractEditManager;
import pers.tuershen.nbtedit.setting.handle.HandleEventTypeEnum;

import java.util.UUID;

public interface ReceiverEntityTileSubject {

    void registerReceiverAddMsgPlayer(UUID uid, AbstractPanelEditEntityTile panelEditEntity, int slot,
                                      HandleEventTypeEnum eventTypeEnum);

    void registerReceiverSetMsgPlayer(UUID uuid, AbstractPanelEditEntityTile panelEditEntity, Object key, byte type,
                                      HandleEventTypeEnum eventTypeEnum);

    void registerReceiverAddFunctionMsgPlayer(UUID uuid, AbstractPanelEditEntityTile editItem,
                                              AbstractEditManager editManager, HandleEventTypeEnum handleEventTypeEnum);



}
