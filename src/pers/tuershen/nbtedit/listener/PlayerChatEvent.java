package pers.tuershen.nbtedit.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import pers.tuershen.nbtedit.event.listener.PushBukkitChatMessages;


public class PlayerChatEvent implements Listener {


    private PushBukkitChatMessages pushBukkitChatMessages;

    public PlayerChatEvent(PushBukkitChatMessages pushBukkitChatMessages){
        this.pushBukkitChatMessages = pushBukkitChatMessages;
    }

    @EventHandler
    public void chatMessages(AsyncPlayerChatEvent event){
        pushBukkitChatMessages.push(event);
    }





}

