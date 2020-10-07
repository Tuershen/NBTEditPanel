package pers.tuershen.nbtedit.panel.edit.item;


import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagBase;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagCompound;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.nbtedit.panel.AbstractEdit;
import pers.tuershen.nbtedit.panel.edit.AbstractPanelEditItem;
import pers.tuershen.nbtedit.function.AbstractEditManager;
import pers.tuershen.nbtedit.setting.handle.EditPanelManagerEnum;
import pers.tuershen.nbtedit.setting.handle.TagTypeEnum;
import pers.tuershen.nbtedit.setting.handle.HandleEventTypeEnum;



import java.util.ArrayList;
import java.util.UUID;

public class EditCompoundItem extends AbstractPanelEditItem {


    //当前Compound
    private TagCompound tagCompound;

    //指向上一个节点
    private AbstractEdit panelEditItem;

    //该节点在界面中的槽位
    protected int slot;

    //从父节点中派生下来的
    protected Object key;


    public EditCompoundItem(UUID uuid, ItemStack itemStack) {
        super(uuid, itemStack);
        this.tagCompound = this.itemApi.getNBTTagCompoundApi();
    }


    public EditCompoundItem(UUID uuid, ItemStack itemStack, TagCompound tagCompound, AbstractEdit editItem, Object key, int slot) {
        this(uuid, itemStack);
        this.tagCompound = tagCompound;
        this.panelEditItem = editItem;
        this.key = key;
        this.slot = slot;
    }


    @Override
    public Inventory newOpenPanel() {
        if (this.tagCompound == null || this.tagCompound.getMap().size() <= 0) {
            this.tagCompound = new TagCompound();
            this.panel = settingDefaultPanel(this);
            this.inventoryList.add(panel);
            return this.panel;
        }
        return this.analysisCompound(this.tagCompound);
    }

    /**
     * 修改节点
     * @param key 节点名称
     * @param slot 位置
     * @param tagBase 节点数据
     * @param player 修改玩家
     */
    @Override
    public void triggerSetEvent(Object key, int slot, TagBase tagBase, Player player) {
        this.triggerSetCompoundMsg(key,tagBase,player);
        receiverItemListener.registerReceiverSetMsgPlayer(this.uuid, this, key, itemStack, tagBase.getTypeId(), HandleEventTypeEnum.SET);
    }


    /**
     * 添加节点
     * @param slot 位置
     * @param player 玩家
     */
    @Override
    public void triggerAddEvent(int slot, Player player) {
        this.triggerAddCompoundMsg(slot,player);
        receiverItemListener.registerReceiverAddMsgPlayer(this.uuid, this, slot, itemStack, HandleEventTypeEnum.ADD);
    }


    @Override
    public void triggerAddFunctionNBT(AbstractEditManager editManager, Player player){
        if (triggerMsg(editManager,player)){
            saveCompound((TagCompound) EditPanelManagerEnum.getInstance(TagTypeEnum.COMPOUND).addFunction("", player, this.tagCompound, editManager));
            ItemStack itemStack = updateNode(this);
            player.getInventory().setItemInMainHand(itemStack);
            return;
        }
        receiverItemListener.registerReceiverAddFunctionMsgPlayer(this.uuid, this, editManager, itemStack, HandleEventTypeEnum.FUNCTION_CALL);
    }

    @Override
    public ItemStack addSlotPositionNBTTag(String msg, int slot, ItemStack itemStack, Player player) {
        saveCompound((TagCompound) EditPanelManagerEnum.getInstance(TagTypeEnum.COMPOUND).addTag(msg,player,slot,this.tagCompound));
        player.openInventory(panel);
        return updateNode(this);
    }

    @Override
    public ItemStack setSlotPositionNBTTag(String msg, Object slot, ItemStack itemStack, Player player, byte type) {
        TagBase tagBase = this.newSingleNBT(type,msg,player);
        if (tagBase != null) {
            saveCompound((TagCompound) EditPanelManagerEnum.getInstance(TagTypeEnum.COMPOUND).setTag(this.tagCompound,player,slot,tagBase));
            return updateNode(this);
        }
        return itemStack;
    }

    @Override
    public ItemStack addFunctionNBTTag(String msg, Player player, ItemStack itemStack, AbstractEditManager editManager) {
        saveCompound((TagCompound) EditPanelManagerEnum.getInstance(TagTypeEnum.COMPOUND).addFunction(msg,player,this.tagCompound,editManager));
        return updateNode(this);
    }


    @Override
    public ItemStack remove(Object var, ItemStack itemStack, int slot) {
        this.panel.setItem(slot, new ItemStack(Material.AIR));
        this.tagCompound.remove(var);
        return this.updateNode(this);
    }


    @Override
    public ItemStack updateNode(Object key, int slot, TagBase tagBase, AbstractPanelEditItem panelEditItem) {
        this.tagCompound.getMap().put(key.toString(),tagBase);
        panelEditItem.getInventory().setItem(slot,setSlotItemStack(key,tagBase,false));
        return this.updateNode(this);
    }

    public void saveCompound(TagCompound tagCompound){
        this.tagCompound = tagCompound;
        this.inventoryList = new ArrayList<>();
        this.analysisCompound(tagCompound);
    }


    @Override
    public AbstractEdit getEdit() {
        return this.panelEditItem;
    }

    @Override
    public TagBase getTagBase() {
        return this.tagCompound;
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
