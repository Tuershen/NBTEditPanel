package pers.tuershen.nbtedit.event.listener;


import pers.tuershen.nbtedit.event.HandleMessagesEntityEvent;
import pers.tuershen.nbtedit.event.HandleMessagesEntityTileEvent;
import pers.tuershen.nbtedit.event.HandleMessagesItemEvent;
import pers.tuershen.nbtedit.event.sub.RegisterHandleEvent;


public class HandleMessages implements RegisterHandleEvent {

    private HandleMessagesItemEvent itemEvent;

    private HandleMessagesEntityEvent entityEvent;

    private HandleMessagesEntityTileEvent entityTileEvent;

    @Override
    public void registerHandleItemEvent(HandleMessagesItemEvent event) {
        this.itemEvent = event;
    }

    @Override
    public void registerHandleEntityEvent(HandleMessagesEntityEvent event) {
        this.entityEvent = event;
    }

    @Override
    public void registerHandleEntityTileEvent(HandleMessagesEntityTileEvent event) { this.entityTileEvent = event; }

    public HandleMessagesItemEvent getItemEvent() {
        return itemEvent;
    }

    public HandleMessagesEntityEvent getEntityEvent() {
        return entityEvent;
    }

    public HandleMessagesEntityTileEvent getEntityTileEvent() {
        return entityTileEvent;
    }




}
