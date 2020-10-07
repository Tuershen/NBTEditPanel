package pers.tuershen.nbtedit.panel.edit.tile;


import pers.tuershen.nbtedit.compoundlibrary.api.TileEntityCompoundApi;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagBase;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagList;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.nbtedit.panel.AbstractEdit;
import pers.tuershen.nbtedit.panel.edit.AbstractPanelEditEntityTile;
import pers.tuershen.nbtedit.function.AbstractEditManager;
import pers.tuershen.nbtedit.setting.handle.EditPanelManagerEnum;
import pers.tuershen.nbtedit.setting.handle.TagTypeEnum;
import pers.tuershen.nbtedit.setting.handle.HandleEventTypeEnum;

import java.util.ArrayList;
import java.util.UUID;

public class EditArrayEntityTile extends AbstractPanelEditEntityTile {

    private TagList list;

    protected int slot;

    protected Object key;

    private AbstractEdit edit;

    public EditArrayEntityTile(UUID uuid, TagList tagList, AbstractEdit edit, Object key, int slot, TileEntityCompoundApi entityCompoundApi) {
        super(uuid,entityCompoundApi);
        this.list = tagList;
        this.key = key;
        this.slot = slot;
        this.edit = edit;
    }


    @Override
    public Inventory newOpenPanel() {
        if (this.list.getData().size() == 0){
            this.panel = settingDefaultPanel(this);
            return this.panel;
        }
        return analysisList(this.list);
    }

    @Override
    public void remove(Object var, int slot) {
        this.list.remove(slot);
        this.panel.setItem(slot, new ItemStack(Material.AIR));
        this.inventoryList = new ArrayList<>();
        this.analysisList(this.list);
        this.save(this);
    }

    @Override
    public void updateNode(Object key, int slot, TagBase tagBase, AbstractPanelEditEntityTile panelEditEntity) {
        this.list.getData().set(Integer.parseInt(key.toString()),tagBase);
        edit.getInventory().setItem(slot,setSlotItemStack(key,tagBase,true));
        this.save(this);
    }


    /**
     * 添加NBT
     * @param slot
     * @param player
     */
    @Override
    public void triggerAddEvent(int slot, Player player) {
        triggerAddArrayMsg(player,slot,this.list.getType());
        receiverEntityTileListener.registerReceiverAddMsgPlayer(this.uuid, this, slot, HandleEventTypeEnum.ADD);
    }


    /**
     * 设置NBT
     * @param key
     * @param slot
     * @param tagBase
     * @param player
     */
    @Override
    public void triggerSetEvent(Object key, int slot, TagBase tagBase, Player player) {
        triggerSetListMsg(key,tagBase,player);
        receiverEntityTileListener.registerReceiverSetMsgPlayer(this.uuid, this, key, tagBase.getTypeId(), HandleEventTypeEnum.SET);
    }

    /**
     * 自定义功能
     * @param editManager
     * @param player
     */
    @Override
    public void triggerAddFunctionNBT(AbstractEditManager editManager, Player player) {
        if (this.triggerMsg(editManager,player)) {
            saveList((TagList) EditPanelManagerEnum.getInstance(TagTypeEnum.LIST).addFunction("", player, this.list, editManager));
            return;
        }
        receiverEntityTileListener.registerReceiverAddFunctionMsgPlayer(this.uuid, this, editManager, HandleEventTypeEnum.FUNCTION_CALL);
    }

    /**
     * 添加事件处理
     * @param msg 玩家输入的信息
     * @param slot
     * @param player
     */
    @Override
    public void addSlotPositionNBTTag(String msg, int slot, Player player) {
        saveList((TagList) EditPanelManagerEnum.getInstance(TagTypeEnum.LIST).addTag(msg,player,slot,this.list));
        player.openInventory(panel);
    }


    /**
     * 设置NBT事件处理
     * @param msg
     * @param key
     * @param player
     * @param type
     */
    @Override
    public void setSlotPositionNBTTag(String msg, Object key, Player player, byte type) {
        TagBase tagBase = this.newSingleNBT(type,msg,player);
        if (tagBase != null) {
            saveList((TagList) EditPanelManagerEnum.getInstance(TagTypeEnum.LIST).setTag(this.list,player,key,tagBase));
        }
    }

    /**
     * 自定义功能事件处理
     * @param msg
     * @param player
     * @param editManager
     */
    @Override
    public void addFunctionNBTTag(String msg, Player player, AbstractEditManager editManager) {
        saveList((TagList) EditPanelManagerEnum.getInstance(TagTypeEnum.LIST).addFunction(msg,player,this.list,editManager));
    }

    public void saveList(TagList list){
        this.list = list;
        this.inventoryList = new ArrayList<>();
        this.analysisList(this.list);
        save(this);
    }

    @Override
    public TileEntityCompoundApi tileEntityCompoundApi() {
        return this.tileEntityCompoundApi;
    }

    @Override
    public AbstractEdit getEdit() {
        return this.edit;
    }

    @Override
    public TagBase getTagBase() {
        return this.list;
    }

    @Override
    public Inventory getInventory() {
        return this.panel;
    }

    @Override
    public Object getKey() {
        return this.key;
    }

    @Override
    public int getSlot() {
        return this.slot;
    }


}
