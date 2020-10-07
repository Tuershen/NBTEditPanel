package pers.tuershen.nbtedit.listener;


import pers.tuershen.nbtedit.compoundlibrary.api.CompoundLibraryApi;
import pers.tuershen.nbtedit.compoundlibrary.api.EntityNBTTagCompoundApi;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import pers.tuershen.nbtedit.command.ListenerValve;
import pers.tuershen.nbtedit.panel.edit.entity.EditCompoundEntity;

public class PlayerClickEntity implements Listener {

    private CompoundLibraryApi compoundLibraryApi;

    private ListenerValve listenerValve;

    public PlayerClickEntity(CompoundLibraryApi compoundLibraryApi, ListenerValve listenerValve){
        this.compoundLibraryApi = compoundLibraryApi;
        this.listenerValve = listenerValve;
    }

    @EventHandler
    public void onClickEntity(PlayerInteractEntityEvent event){
        if (event.getPlayer().hasPermission("NBTEdit") || event.getPlayer().isOp()){
            if (listenerValve.value(event.getPlayer().getUniqueId())) {
                if (event.getRightClicked() instanceof LivingEntity) {
                    EntityNBTTagCompoundApi compoundApi = compoundLibraryApi.getEntityCompoundApi((LivingEntity) event.getRightClicked());
                    Player player = event.getPlayer();
                    player.openInventory(new EditCompoundEntity(player.getUniqueId(), compoundApi).newOpenPanel());
                    event.setCancelled(true);
                }
            }
        }
    }
}
