package pers.tuershen.nbtedit.panel.edit;


import pers.tuershen.nbtedit.compoundlibrary.api.NBTTagCompoundApi;
import pers.tuershen.nbtedit.compoundlibrary.api.TileEntityCompoundApi;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagBase;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagCompound;
import pers.tuershen.nbtedit.panel.AbstractEdit;
import pers.tuershen.nbtedit.event.HandleMessagesEntityTileEvent;
import pers.tuershen.nbtedit.event.listener.ReceiverEntityTileListener;
import pers.tuershen.nbtedit.setting.handle.EditObjectTypeEnum;


import java.util.UUID;

public abstract class AbstractPanelEditEntityTile extends AbstractEdit implements HandleMessagesEntityTileEvent {

    protected TileEntityCompoundApi tileEntityCompoundApi;

    public static ReceiverEntityTileListener receiverEntityTileListener = new ReceiverEntityTileListener();

    public AbstractPanelEditEntityTile(UUID uuid, TileEntityCompoundApi tileEntityCompoundApi) {
        super(uuid);
        this.tileEntityCompoundApi = tileEntityCompoundApi;
        this.handleMessages.registerHandleEntityTileEvent(this);
    }

    @Override
    public EditObjectTypeEnum getEditObjectType() {
        return EditObjectTypeEnum.TILE_ENTITY;
    }

    public void save(AbstractPanelEditEntityTile edit){
        AbstractPanelEditEntityTile panelEditItem = (AbstractPanelEditEntityTile) edit.getEdit();
        if (panelEditItem != null){
            panelEditItem.updateNode(edit.getKey(),edit.getSlot(),edit.getTagBase(),panelEditItem);
            return;
        }
        TagCompound tagCompound = (TagCompound) edit.getTagBase();
        NBTTagCompoundApi nbtTagCompound = edit.tileEntityCompoundApi.getNBTTagCompound();
        nbtTagCompound.setCompoundMap(tagCompound);
        tileEntityCompoundApi.saveNBTTag(nbtTagCompound);
    }


    public HandleMessagesEntityTileEvent handleMessagesEntityEvent(){
        return this.handleMessages.getEntityTileEvent();
    }


    public abstract Object getKey();

    public abstract int getSlot();

    public abstract void remove(Object var, int slot);

    public abstract void updateNode(Object key, int slot, TagBase tagBase, AbstractPanelEditEntityTile panelEditEntity);

    public abstract TileEntityCompoundApi tileEntityCompoundApi();


}
