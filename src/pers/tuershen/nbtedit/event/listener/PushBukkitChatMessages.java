package pers.tuershen.nbtedit.event.listener;

import org.bukkit.event.player.AsyncPlayerChatEvent;
import pers.tuershen.nbtedit.panel.edit.AbstractPanelEditEntity;
import pers.tuershen.nbtedit.panel.edit.AbstractPanelEditEntityTile;
import pers.tuershen.nbtedit.panel.edit.AbstractPanelEditItem;
import pers.tuershen.nbtedit.event.listener.bukkit.PushMessages;
import pers.tuershen.nbtedit.event.listener.bukkit.RegisterBukkitChat;

import java.util.ArrayList;
import java.util.List;

public class PushBukkitChatMessages implements RegisterBukkitChat {

    private List<PushMessages> pushMessages = new ArrayList<>();

    @Override
    public void registerChatEvent(PushMessages pushMessages) {
        this.pushMessages.add(pushMessages);
    }

    public void push(AsyncPlayerChatEvent event){
        for (int i = 0; i < pushMessages.size() ; i++) {
            this.pushMessages.get(i).push(event);
        }
    }


    public static PushBukkitChatMessages registerReceiveObject(){
        PushBukkitChatMessages pushBukkitChatMessages = new PushBukkitChatMessages();
        pushBukkitChatMessages.registerChatEvent(AbstractPanelEditItem.receiverItemListener);
        pushBukkitChatMessages.registerChatEvent(AbstractPanelEditEntity.receiverEntityListener);
        pushBukkitChatMessages.registerChatEvent(AbstractPanelEditEntityTile.receiverEntityTileListener);
        return pushBukkitChatMessages;
    }




}
