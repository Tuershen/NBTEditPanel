package pers.tuershen.nbtedit.listener;

import pers.tuershen.nbtedit.compoundlibrary.api.CompoundLibraryApi;
import pers.tuershen.nbtedit.compoundlibrary.api.TileEntityCompoundApi;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import pers.tuershen.nbtedit.command.ListenerValve;
import pers.tuershen.nbtedit.panel.edit.tile.EditCompoundEntityTile;

public class PlayerClickBlock implements Listener {

    private CompoundLibraryApi compoundLibraryApi;

    private ListenerValve listenerValve;

    public PlayerClickBlock(CompoundLibraryApi compoundLibraryApi, ListenerValve listenerValve){
        this.compoundLibraryApi = compoundLibraryApi;
        this.listenerValve = listenerValve;
    }

    @EventHandler
    public void clickBlock(PlayerInteractEvent event){

        Block block = event.getClickedBlock();
        if (block == null) return;
        if (event.getPlayer().hasPermission("NBTEdit") || event.getPlayer().isOp()){
            if (listenerValve.valueTile(event.getPlayer().getUniqueId())){
                TileEntityCompoundApi tileEntityCompoundApi = this.compoundLibraryApi.getTileEntityCompoundApi(block);
                Player player = event.getPlayer();
                player.openInventory(new EditCompoundEntityTile(player.getUniqueId(), tileEntityCompoundApi).newOpenPanel());
                event.setCancelled(true);
            }
        }
    }


}
