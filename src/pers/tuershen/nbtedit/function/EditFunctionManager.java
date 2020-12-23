package pers.tuershen.nbtedit.function;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.nbtedit.panel.AbstractEdit;
import pers.tuershen.nbtedit.panel.AbstractPanel;
import pers.tuershen.nbtedit.panel.edit.AbstractPanelEditEntity;
import pers.tuershen.nbtedit.panel.edit.AbstractPanelEditEntityTile;
import pers.tuershen.nbtedit.panel.edit.AbstractPanelEditItem;
import pers.tuershen.nbtedit.setting.handle.ToolsFunction;

import java.util.*;

public class EditFunctionManager extends AbstractPanel implements InventoryHolder  {

    private AbstractEdit abstractEdit;

    private static List<AbstractEditManager> managerList = new ArrayList<>();

    private static Map<UUID, EditFunctionManager> playerEditManagerMap = new HashMap<>();

    private static final int PANEL_MAX_SIZE = 45;

    public Inventory settingDefault(EditFunctionManager manager, int size){
        Inventory panel = Bukkit.createInventory(manager, size, "§a快§b捷§d功§c能§e表");
        for (int i = 45; i <= 53; i++) {
            panel.setItem(i, ToolsFunction.functionItem());
        }
        panel.setItem(PREVIOUS_SLOT_POS, ToolsFunction. previousButton());
        panel.setItem(MIDDLE_SLOT_POS,ToolsFunction. middleButton());
        panel.setItem(NEXT_SLOT_POS,ToolsFunction. nextButton());
        return panel;
    }

    public EditFunctionManager(AbstractEdit abstractEdit){
        this.abstractEdit = abstractEdit;
        this.panel = functionPanel();
    }

    @Override
    public Inventory getInventory() {
        return panel;
    }

    public Inventory openEditPanel(){
        return this.abstractEdit.getInventory();
    }

    /**
     * 创建功能表
     * 自动分页
     * @return
     */
    public Inventory functionPanel(){
        //总自定义功能数
        int mangerSize = managerList.size();
        //
        this.page = mangerSize / PANEL_MAX_SIZE + 1;
        int slot = 0;
        for (int j = 0; j < this.page ; j++) {
            Inventory inventory = settingDefault(this, MAX_SLOT);
            for (int i = 0; ((i < PANEL_MAX_SIZE) && (slot < mangerSize)) ; i++, slot++) {
                ItemStack itemStack =  managerList.get(slot).getSlotItem();
                inventory.setItem(i, itemStack);
            }
            this.inventoryList.add(inventory);
        }
        this.panel = this.inventoryList.get(nowPage);
        return panel;
    }


    public AbstractEditManager getEditManager(int index){
        /**
         * 2020-12-22:17:21
         * 修复分页
         * 正确下标应该是  (当前页面下标 * 页面最大容量) + 当前鼠标点击的网格下标
         */
        index = (this.nowPage * PANEL_MAX_SIZE) + index;

        return managerList.get(index);
    }

    /***
     * 事件处理
     * @param editManager
     * @param player
     */
    public void pushEditEvent(AbstractEditManager editManager, Player player){
        switch (this.abstractEdit.getEditObjectType()){
            case ITEM:
                AbstractPanelEditItem abstractPanelEditItem = (AbstractPanelEditItem) this.abstractEdit;
                player.closeInventory();
                abstractPanelEditItem.triggerAddFunctionNBT(editManager,player);
                break;
            case ENTITY:
                AbstractPanelEditEntity panelEditEntity = (AbstractPanelEditEntity) this.abstractEdit;
                player.closeInventory();
                panelEditEntity.triggerAddFunctionNBT(editManager,player);
                break;
            case TILE_ENTITY:
                AbstractPanelEditEntityTile panelEditEntityTile = (AbstractPanelEditEntityTile) this.abstractEdit;
                player.closeInventory();
                panelEditEntityTile.triggerAddFunctionNBT(editManager,player);
                break;
        }
    }

    public void replaceAbstractEdit(AbstractEdit edit){
        this.abstractEdit = edit;
    }

    public static EditFunctionManager getEditManager(UUID uuid, AbstractEdit edit){
        if (playerEditManagerMap.containsKey(uuid)){
            EditFunctionManager functionManager = playerEditManagerMap.get(uuid);
            functionManager.replaceAbstractEdit(edit);
            return functionManager;
        }
        EditFunctionManager functionManager = new EditFunctionManager(edit);
        playerEditManagerMap.put(uuid, functionManager);
        return functionManager;
    }

    public static void reloadPlayerEditManagerMap (){
        playerEditManagerMap = new HashMap<>();
    }

    public static void reloadManagerList() {
        managerList = new ArrayList<>();
    }

    public static void registerEditFunction(AbstractEditManager editManager){
        managerList.add(editManager);
    }




}
