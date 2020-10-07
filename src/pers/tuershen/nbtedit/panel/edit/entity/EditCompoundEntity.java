package pers.tuershen.nbtedit.panel.edit.entity;

import pers.tuershen.nbtedit.compoundlibrary.api.EntityNBTTagCompoundApi;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagBase;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagCompound;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.nbtedit.panel.AbstractEdit;
import pers.tuershen.nbtedit.panel.edit.AbstractPanelEditEntity;
import pers.tuershen.nbtedit.function.AbstractEditManager;
import pers.tuershen.nbtedit.setting.handle.EditPanelManagerEnum;
import pers.tuershen.nbtedit.setting.handle.TagTypeEnum;
import pers.tuershen.nbtedit.setting.handle.HandleEventTypeEnum;

import java.util.ArrayList;
import java.util.UUID;

public class EditCompoundEntity extends AbstractPanelEditEntity {

    private TagCompound tagCompound;

    protected int slot;

    protected Object key;

    private AbstractEdit editItem;


    public EditCompoundEntity(UUID uuid, EntityNBTTagCompoundApi entityNBTTagCompoundApi){
        super(uuid,entityNBTTagCompoundApi);
        this.tagCompound = entityNBTTagCompoundApi.getNBTTagCompound().getNBTTagCompoundApi();
    }


    public EditCompoundEntity(UUID uuid, TagCompound tagCompound, AbstractEdit editItem, Object key, int slot, EntityNBTTagCompoundApi entityNBTTagCompoundApi) {
        super(uuid,entityNBTTagCompoundApi);
        this.entityNBTTagCompoundApi = entityNBTTagCompoundApi;
        this.tagCompound = tagCompound;
        this.key = key;
        this.slot = slot;
        this.editItem = editItem;
    }



    @Override
    public void remove(Object var, int slot) {
        this.tagCompound.getMap().remove(var.toString());
        this.panel.setItem(slot, new ItemStack(Material.AIR));
        this.save(this);
    }

    @Override
    public void updateNode(Object key, int slot, TagBase tagBase, AbstractPanelEditEntity panelEditEntity){
        this.tagCompound.getMap().put(key.toString(),tagBase);
        panelEditEntity.getInventory().setItem(slot,setSlotItemStack(key,tagBase,false));
        this.save(this);
    }


    @Override
    public Inventory newOpenPanel() {
        if (tagCompound == null || this.tagCompound.getMap().size() <= 0){
            this.tagCompound = new TagCompound();
            this.panel = settingDefaultPanel(this);
            this.inventoryList.add(panel);
            return this.panel;
        }
        return this.analysisCompound(tagCompound);
    }

    @Override
    public void triggerAddEvent(int slot, Player player) {
        this.triggerAddCompoundMsg(slot,player);
        receiverEntityListener.registerReceiverAddMsgPlayer(this.uuid, this, slot, HandleEventTypeEnum.ADD);
    }

    @Override
    public void triggerSetEvent(Object key, int slot, TagBase tagBase, Player player) {
        this.triggerSetCompoundMsg(key,tagBase,player);
        receiverEntityListener.registerReceiverSetMsgPlayer(this.uuid, this, key, tagBase.getTypeId(), HandleEventTypeEnum.SET);
    }

    @Override
    public void triggerAddFunctionNBT(AbstractEditManager editManager, Player player) {
        if (this.triggerMsg(editManager,player)) {
            saveCompound((TagCompound) EditPanelManagerEnum.getInstance(TagTypeEnum.COMPOUND).addFunction("", player, this.tagCompound, editManager));
            return;
        }
        receiverEntityListener.registerReceiverAddFunctionMsgPlayer(this.uuid, this, editManager, HandleEventTypeEnum.FUNCTION_CALL);
    }

    @Override
    public void addSlotPositionNBTTag(String msg, int slot, Player player) {
        saveCompound((TagCompound) EditPanelManagerEnum.getInstance(TagTypeEnum.COMPOUND).addTag(msg, player, slot, this.tagCompound));
        player.openInventory(panel);
    }

    @Override
    public void setSlotPositionNBTTag(String msg, Object key, Player player, byte type) {
        TagBase tagBase = this.newSingleNBT(type,msg,player);
        if (tagBase != null) {
            saveCompound((TagCompound) EditPanelManagerEnum.getInstance(TagTypeEnum.COMPOUND).setTag(this.tagCompound,player,key,tagBase));
        }
    }

    @Override
    public void addFunctionNBTTag(String msg, Player player, AbstractEditManager editManager) {
        saveCompound((TagCompound) EditPanelManagerEnum.getInstance(TagTypeEnum.COMPOUND).addFunction(msg,player,this.tagCompound,editManager));
    }

    public void saveCompound(TagCompound tagCompound){
        this.tagCompound = tagCompound;
        this.inventoryList = new ArrayList<>();
        this.analysisCompound(this.tagCompound);
        save(this);
    }

    @Override
    public AbstractEdit getEdit() {
        return this.editItem;
    }

    @Override
    public TagBase getTagBase() {
        return this.tagCompound;
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

    @Override
    public EntityNBTTagCompoundApi getEntityNBTTagCompoundApi() {
        return this.entityNBTTagCompoundApi;
    }

}

