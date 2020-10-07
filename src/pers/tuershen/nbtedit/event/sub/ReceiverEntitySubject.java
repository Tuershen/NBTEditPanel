package pers.tuershen.nbtedit.event.sub;

import pers.tuershen.nbtedit.panel.edit.AbstractPanelEditEntity;
import pers.tuershen.nbtedit.function.AbstractEditManager;
import pers.tuershen.nbtedit.setting.handle.HandleEventTypeEnum;

import java.util.UUID;

public interface ReceiverEntitySubject {

    void registerReceiverAddMsgPlayer(UUID uid, AbstractPanelEditEntity panelEditEntity, int slot,
                                      HandleEventTypeEnum eventTypeEnum);

    void registerReceiverSetMsgPlayer(UUID uuid, AbstractPanelEditEntity panelEditEntity, Object key, byte type,
                                      HandleEventTypeEnum eventTypeEnum);

    void registerReceiverAddFunctionMsgPlayer(UUID uuid, AbstractPanelEditEntity editItem,
                                              AbstractEditManager editManager, HandleEventTypeEnum handleEventTypeEnum);




}
