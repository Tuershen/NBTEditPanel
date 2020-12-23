package pers.tuershen.nbtedit.listener;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import pers.tuershen.nbtedit.panel.AbstractEdit;
import pers.tuershen.nbtedit.function.EditFunctionManager;
import pers.tuershen.nbtedit.setting.handle.BukkitEventHandle;
import pers.tuershen.nbtedit.setting.handle.HandleEventDistributionEnum;
import pers.tuershen.nbtedit.setting.handle.HandleEventTypeEnum;

import java.util.UUID;

public class PlayerEditPanelEvent implements Listener {

    @EventHandler
    public void editPanel(InventoryClickEvent event){
        HandleEventTypeEnum handleEventType = HandleEventDistributionEnum.DISTRIBUTION.assignTasks(event);
        switch (handleEventType){
            //添加NBT
            case ADD:
                BukkitEventHandle.add(event);
                event.setCancelled(true);
                break;
                //设置节点的NBT
            case SET:
                BukkitEventHandle.set(event);
                event.setCancelled(true);
                break;
                //删除节点
            case DELETE:
                BukkitEventHandle.delete(event);
                event.setCancelled(true);
                break;
                //插件复合NBT的编辑界面
            case NEW_PANEL:
                BukkitEventHandle.newPanel(event);
                event.setCancelled(true);
                break;
                //下一页
            case NEXT_PAGE:
                Inventory nextPage = ((AbstractEdit) event.getInventory().getHolder()).nextPage();
                event.getWhoClicked().openInventory(nextPage);
                event.setCancelled(true);
                break;
                //返回
            case MIDDLE_PAGE:
                AbstractEdit abstractEdit = (AbstractEdit)event.getInventory().getHolder();
                AbstractEdit edit = abstractEdit.getEdit();
                if (edit != null){
                    event.getWhoClicked().openInventory(edit.getInventory());
                }
                event.setCancelled(true);
                break;
                //上一页
            case PREVIOUS_PAGE:
                Inventory thePreviousPage = ((AbstractEdit) event.getInventory().getHolder()).thePreviousPage();
                event.getWhoClicked().openInventory(thePreviousPage);
                event.setCancelled(true);
                break;
            case FUNCTION_TABLE:
                UUID uniqueId = event.getWhoClicked().getUniqueId();
                AbstractEdit holder = (AbstractEdit)event.getInventory().getHolder();
                Inventory functionPanel = EditFunctionManager.getEditManager(uniqueId, holder).functionPanel();
                event.getWhoClicked().openInventory(functionPanel);
                event.setCancelled(true);
                break;
            case CANCELLED:
                event.setCancelled(true);
                break;
            case FUNCTION_CALL:
                BukkitEventHandle.editFunctionPanel(event);
                event.setCancelled(true);
                break;
            case NULL:
            default:
                break;
        }
    }
}


