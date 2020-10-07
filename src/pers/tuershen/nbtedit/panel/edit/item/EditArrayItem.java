package pers.tuershen.nbtedit.panel.edit.item;


import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagBase;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagList;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.nbtedit.panel.AbstractEdit;
import pers.tuershen.nbtedit.panel.edit.AbstractPanelEditItem;
import pers.tuershen.nbtedit.function.AbstractEditManager;
import pers.tuershen.nbtedit.setting.handle.EditPanelManagerEnum;
import pers.tuershen.nbtedit.setting.handle.TagTypeEnum;
import pers.tuershen.nbtedit.setting.handle.ButtonEnum;
import pers.tuershen.nbtedit.setting.handle.HandleEventTypeEnum;


import java.util.ArrayList;
import java.util.UUID;


public class EditArrayItem extends AbstractPanelEditItem {

    private TagList      list;

    private AbstractEdit editItem;

    protected int slot;

    protected Object key;


    public EditArrayItem(UUID uuid, ItemStack itemStack, TagList tagList, AbstractEdit editItem, Object key, int slot) {
        super(uuid, itemStack);
        this.list = tagList;
        this.editItem = editItem;
        this.key = key;
        this.slot = slot;
    }

    @Override
    public Inventory newOpenPanel() {
        if (this.list.getData().size() == 0){
            this.panel = settingDefaultPanel(this);
            return this.panel;
        }
        return analysisList(this.list);
    }

    /**
     * 修改NBT节点
     * @param key 节点名称或者索引
     * @param slot 位置
     * @param tagBase 修改类型
     * @param player 玩家
     */
    @Override
    public void triggerSetEvent(Object key, int slot, TagBase tagBase, Player player) {
        triggerSetListMsg(key,tagBase,player);
        receiverItemListener.registerReceiverSetMsgPlayer(this.uuid, this, slot, itemStack, tagBase.getTypeId(), HandleEventTypeEnum.SET);
    }

    @Override
    public void triggerAddFunctionNBT(AbstractEditManager editFunctionManager, Player player) {
        if (this.triggerMsg(editFunctionManager,player)){
            saveArray((TagList) EditPanelManagerEnum.getInstance(TagTypeEnum.LIST).addFunction("",player,this.list,editFunctionManager));
            ItemStack itemStack = updateNode(this);
            player.getInventory().setItemInMainHand(itemStack);
            return;
        }
        receiverItemListener.registerReceiverAddFunctionMsgPlayer(this.uuid, this, editFunctionManager, itemStack, HandleEventTypeEnum.FUNCTION_CALL);
    }


    /**
     * 添加NBT
     * @param slot 点击位置
     * @param player 玩家
     */
    @Override
    public void triggerAddEvent(int slot, Player player) {
        triggerAddArrayMsg(player,slot,this.list.getType());
        receiverItemListener.registerReceiverAddMsgPlayer(this.uuid, this, slot, itemStack, HandleEventTypeEnum.ADD);
    }


    /**
     * 添加NBT节点
     * @param msg 数据
     * @param slot 位置
     * @param itemStack 物品
     * @param player 玩家
     * @return item 添加nbt数据后的物品
     */
    @Override
    public ItemStack addSlotPositionNBTTag(String msg, int slot, ItemStack itemStack, Player player) {
        saveArray((TagList) EditPanelManagerEnum.getInstance(TagTypeEnum.LIST).addTag(msg,player,slot,this.list));
        player.openInventory(panel);
        return updateNode(this);
    }

    /**
     * 处理修改NBT事件
     * @param msg 数据
     * @param key 节点索引
     * @param itemStack 修改物品
     * @param player 玩家
     * @param type 类型
     * @return 修改后物品
     */
    @Override
    public ItemStack setSlotPositionNBTTag(String msg, Object key, ItemStack itemStack, Player player, byte type) {
        TagBase tagBase = this.newSingleNBT(type,msg,player);
        if (tagBase != null) {
            saveArray((TagList) EditPanelManagerEnum.getInstance(TagTypeEnum.LIST).setTag(this.list,player,key,tagBase));
            return updateNode(this);
        }
        return itemStack;
    }

    @Override
    public ItemStack addFunctionNBTTag(String msg, Player player, ItemStack itemStack, AbstractEditManager editManager) {

        saveArray((TagList) EditPanelManagerEnum.getInstance(TagTypeEnum.LIST).addFunction(msg,player,this.list,editManager));
        return updateNode(this);
    }

    /**
     * 删除节点
     * @param var key
     * @param itemStack 物品
     * @param slot 槽位
     * @return 更新节点
     */
    @Override
    public ItemStack remove(Object var, ItemStack itemStack, int slot) {
        this.list.remove(slot);
        this.panel.setItem(slot, new ItemStack(Material.AIR));
        this.inventoryList = new ArrayList<>();
        this.analysisList(this.list);
        return updateNode(this);
    }

    /**
     * 更新节点
     * @param key
     * @param slot
     * @param tagBase
     * @param editItem
     * @return
     */
    @Override
    public ItemStack updateNode(Object key, int slot, TagBase tagBase, AbstractPanelEditItem editItem) {
        this.list.getData().set(Integer.parseInt(key.toString()),tagBase);
        editItem.getInventory().setItem(slot,setSlotItemStack(key,tagBase,true));
        return this.updateNode(this);
    }

    public void saveArray(TagList list){
        this.list = list;
        this.inventoryList = new ArrayList<>();
        this.analysisList(this.list);
    }


    @Override
    public AbstractEdit getEdit() { return this.editItem; }

    @Override
    public TagBase getTagBase() {
        return this.list;
    }

    @Override
    public Inventory getInventory() {
        return this.panel;
    }

    public Object getKey(){
        return this.key;
    }

    public int getSlot(){
        return this.slot;
    }





}
