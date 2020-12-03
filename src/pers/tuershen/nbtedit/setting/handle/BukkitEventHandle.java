package pers.tuershen.nbtedit.setting.handle;

import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagBase;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.nbtedit.panel.AbstractEdit;
import pers.tuershen.nbtedit.panel.AbstractPanel;
import pers.tuershen.nbtedit.panel.edit.AbstractPanelEditEntity;
import pers.tuershen.nbtedit.panel.edit.AbstractPanelEditEntityTile;
import pers.tuershen.nbtedit.panel.edit.AbstractPanelEditItem;
import pers.tuershen.nbtedit.function.AbstractEditManager;
import pers.tuershen.nbtedit.function.EditFunctionManager;

public class BukkitEventHandle {

    /**
     * 删除节点
     * @param event
     */
    public static void delete(InventoryClickEvent event){
        AbstractEdit abstractEdit = (AbstractEdit) event.getInventory().getHolder();
        ItemStack itemStack = event.getCurrentItem();
        int slot = event.getSlot();
        Object key = abstractEdit.getSlotNBTTagKey(itemStack);
        switch (abstractEdit.getEditObjectType()){
            case ITEM:
                ItemStack item = ((AbstractPanelEditItem) abstractEdit).remove(key, event.getWhoClicked().getItemInHand(), slot);
                event.getWhoClicked().getInventory().setItemInHand(item);
                break;
            case TILE_ENTITY:
                ((AbstractPanelEditEntityTile)abstractEdit).remove(key,slot);
                break;
            case ENTITY:
                ((AbstractPanelEditEntity)abstractEdit).remove(key,slot);
                break;
        }
    }


    /**
     * 添加节点
     * @param event
     */
    public static void add(InventoryClickEvent event){
        AbstractEdit abstractEdit = (AbstractEdit) event.getInventory().getHolder();
        switch (abstractEdit.getEditObjectType()){
            case ITEM:
                AbstractPanelEditItem panelEditItem = (AbstractPanelEditItem) abstractEdit;
                panelEditItem.triggerAddEvent(event.getRawSlot(), (Player) event.getWhoClicked());
                break;
            case TILE_ENTITY:
                AbstractPanelEditEntityTile panelEditEntityTile = (AbstractPanelEditEntityTile) abstractEdit;
                panelEditEntityTile.triggerAddEvent(event.getRawSlot(), (Player) event.getWhoClicked());
                break;
            case ENTITY:
                AbstractPanelEditEntity panelEditEntity = (AbstractPanelEditEntity) abstractEdit;
                panelEditEntity.triggerAddEvent(event.getRawSlot(), (Player) event.getWhoClicked());
                break;

        }
    }

    /**
     * 设置节点
     * @param event
     */
    public static void set(InventoryClickEvent event){
        AbstractEdit abstractEdit = (AbstractEdit) event.getInventory().getHolder();
        TagBase tagBase = abstractEdit.deserializeTagBase(event.getCurrentItem());
        String key = abstractEdit.getSlotNBTTagKey(event.getCurrentItem());
        switch (abstractEdit.getEditObjectType()){
            case ITEM:
                AbstractPanelEditItem editItem = (AbstractPanelEditItem) abstractEdit;
                editItem.triggerSetEvent(key,event.getRawSlot(),tagBase, (Player) event.getWhoClicked());
                break;
            case ENTITY:
                AbstractPanelEditEntity panelEditEntity = (AbstractPanelEditEntity) abstractEdit;
                panelEditEntity.triggerSetEvent(key,event.getRawSlot(),tagBase, (Player) event.getWhoClicked());
                break;
            case TILE_ENTITY:
                AbstractPanelEditEntityTile panelEditEntityTile = (AbstractPanelEditEntityTile) abstractEdit;
                panelEditEntityTile.triggerSetEvent(key,event.getRawSlot(),tagBase, (Player) event.getWhoClicked());
                break;
        }

    }

    /**
     * 创建子节点的界面
     * @param event
     */
    public static void newPanel(InventoryClickEvent event){
        AbstractEdit abstractEdit = (AbstractEdit) event.getInventory().getHolder();
        String key = abstractEdit.getSlotNBTTagKey(event.getCurrentItem());
        TagBase tagBase = abstractEdit.deserializeTagBase(event.getCurrentItem());
        Player player = (Player) event.getWhoClicked();
        switch (abstractEdit.getEditObjectType()){
            case ENTITY:
                HandleNewPanel newEntityPanel = HandleNewPanel.getInstance(EditObjectTypeEnum.ENTITY);
                AbstractPanelEditEntity editEntity = (AbstractPanelEditEntity) event.getInventory().getHolder();
                Inventory entityInventory = newEntityPanel.newPanel(
                        new CreatePanel(
                                tagBase,
                                abstractEdit,
                                event.getWhoClicked().getUniqueId(),
                                key,
                                event.getRawSlot(),
                                editEntity.getEntityNBTTagCompoundApi()));
                player.openInventory(entityInventory);
                break;
            case TILE_ENTITY:
                HandleNewPanel newTilePanel = HandleNewPanel.getInstance(EditObjectTypeEnum.TILE_ENTITY);
                AbstractPanelEditEntityTile editEntityTile = (AbstractPanelEditEntityTile) event.getInventory().getHolder();
                Inventory tileInventory = newTilePanel.newPanel(
                        new CreatePanel(
                                tagBase,
                                abstractEdit,
                                event.getWhoClicked().getUniqueId(),
                                key,
                                event.getRawSlot(),
                                editEntityTile.tileEntityCompoundApi()));
                player.openInventory(tileInventory);
                break;
            case ITEM:
                HandleNewPanel newItemPanel = HandleNewPanel.getInstance(EditObjectTypeEnum.ITEM);
                Inventory itemInventory = newItemPanel.newPanel(
                        new CreatePanel(
                                tagBase,
                                abstractEdit,
                                event.getWhoClicked().getUniqueId(),
                                key,
                                event.getWhoClicked().getItemInHand(),
                                event.getRawSlot()));
                player.openInventory(itemInventory);
                break;
        }
    }


    /**
     * 自定义功能
     * @param event
     */
    public static void editFunctionPanel(InventoryClickEvent event){
        EditFunctionManager editFunctionManager = (EditFunctionManager) event.getInventory().getHolder();
        if (event.getRawSlot() >= 45){
            switch (event.getRawSlot()){
                case AbstractPanel.PREVIOUS_SLOT_POS:
                    event.getWhoClicked().openInventory(editFunctionManager.thePreviousPage());
                    return;
                case AbstractPanel.MIDDLE_SLOT_POS:
                    event.getWhoClicked().openInventory(editFunctionManager.openEditPanel());
                    return;
                case AbstractPanel.NEXT_SLOT_POS:
                    event.getWhoClicked().openInventory(editFunctionManager.nextPage());
                    return;
                default:
                    event.setCancelled(true);
                    return;
            }
        }
        AbstractEditManager editManager = editFunctionManager.getEditManager(event.getRawSlot());
        editFunctionManager.pushEditEvent(editManager, (Player) event.getView().getPlayer());
        return;
    }

}
