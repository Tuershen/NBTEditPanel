package pers.tuershen.nbtedit.event.sub;

import pers.tuershen.nbtedit.event.HandleMessagesEntityTileEvent;
import pers.tuershen.nbtedit.event.HandleMessagesEntityEvent;
import pers.tuershen.nbtedit.event.HandleMessagesItemEvent;

public interface RegisterHandleEvent {

    void registerHandleItemEvent(HandleMessagesItemEvent event);


    void registerHandleEntityEvent(HandleMessagesEntityEvent event);


    void registerHandleEntityTileEvent(HandleMessagesEntityTileEvent event);





}
