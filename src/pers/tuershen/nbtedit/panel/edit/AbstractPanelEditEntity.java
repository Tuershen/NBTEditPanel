package pers.tuershen.nbtedit.panel.edit;


import pers.tuershen.nbtedit.compoundlibrary.api.EntityNBTTagCompoundApi;
import pers.tuershen.nbtedit.compoundlibrary.api.NBTTagCompoundApi;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagBase;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagCompound;
import pers.tuershen.nbtedit.panel.AbstractEdit;
import pers.tuershen.nbtedit.event.HandleMessagesEntityEvent;
import pers.tuershen.nbtedit.event.listener.ReceiverEntityListener;
import pers.tuershen.nbtedit.setting.handle.EditObjectTypeEnum;

import java.util.UUID;

public abstract class AbstractPanelEditEntity extends AbstractEdit implements HandleMessagesEntityEvent {

    protected EntityNBTTagCompoundApi entityNBTTagCompoundApi;

    public static ReceiverEntityListener receiverEntityListener = new ReceiverEntityListener();

    public AbstractPanelEditEntity(UUID uuid, EntityNBTTagCompoundApi entityNBTTagCompoundApi) {
        super(uuid);
        this.entityNBTTagCompoundApi = entityNBTTagCompoundApi;
        this.handleMessages.registerHandleEntityEvent(this);
    }


    /**
     * 保存实体nbt数据
     * @param edit 抽象父类
     */
    public void save(AbstractPanelEditEntity edit){
        //获取上一个节点，如果为空说明该节点为头节点
        AbstractPanelEditEntity panelEditItem = (AbstractPanelEditEntity) edit.getEdit();
        if (panelEditItem != null){
            //双重递归，更新界面与NBT数据
            panelEditItem.updateNode(edit.getKey(),edit.getSlot(),edit.getTagBase(),panelEditItem);
            return;
        }
        //头节点
        TagCompound tagCompound = (TagCompound) edit.getTagBase();
        NBTTagCompoundApi nbtTagCompound = edit.entityNBTTagCompoundApi.getNBTTagCompound();
        nbtTagCompound.setCompoundMap(tagCompound);
        //保存数据
        entityNBTTagCompoundApi.saveNBTTag(nbtTagCompound);
    }

    /**
     * 编辑类型
     * @return 实体 Entity
     */
    @Override
    public EditObjectTypeEnum getEditObjectType() { return EditObjectTypeEnum.ENTITY; }

    public HandleMessagesEntityEvent handleMessagesEntityEvent(){ return this.handleMessages.getEntityEvent(); }

    public abstract void updateNode(Object key, int slot, TagBase tagBase, AbstractPanelEditEntity panelEditEntity);

    public abstract EntityNBTTagCompoundApi getEntityNBTTagCompoundApi();

    public abstract Object getKey();

    public abstract int getSlot();

    public abstract void remove(Object var, int slot);



}
