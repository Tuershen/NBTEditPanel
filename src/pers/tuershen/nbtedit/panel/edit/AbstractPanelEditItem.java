package pers.tuershen.nbtedit.panel.edit;


import pers.tuershen.nbtedit.compoundlibrary.api.NBTTagCompoundApi;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagBase;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagCompound;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.nbtedit.panel.AbstractEdit;
import pers.tuershen.nbtedit.event.HandleMessagesItemEvent;
import pers.tuershen.nbtedit.event.listener.ReceiverItemListener;
import pers.tuershen.nbtedit.setting.handle.EditObjectTypeEnum;


import java.util.UUID;

public abstract class AbstractPanelEditItem extends AbstractEdit implements HandleMessagesItemEvent {

    //被编辑的物品
    protected ItemStack itemStack;

    //NBT接口
    protected NBTTagCompoundApi itemApi;

    //信息接受处理事件
    public static ReceiverItemListener receiverItemListener = new ReceiverItemListener();

    /**
     *
     * @param uuid
     * @param itemStack
     */
    public AbstractPanelEditItem(UUID uuid, ItemStack itemStack) {
        super(uuid);
        this.handleMessages.registerHandleItemEvent(this);
        this.itemStack = itemStack;
        itemApi = AbstractEdit.compoundLibraryApi.getCompound(itemStack);
    }

    /**
     * 更新链表数据
     * @param edit
     * @return 更新后的ItemStack
     */
    public ItemStack updateNode(AbstractPanelEditItem edit){
        AbstractPanelEditItem panelEditItem = (AbstractPanelEditItem) edit.getEdit();
        //如果头节点不为空说明该对象是子节点
        if (panelEditItem != null){
            //递归更新
            return panelEditItem.updateNode(edit.getKey(),edit.getSlot(),edit.getTagBase(),panelEditItem);
        }
        //否则该对象为链表头，头节点是没有父节点的
        TagCompound tagCompound = (TagCompound) edit.getTagBase();
        itemApi.setCompoundMap(tagCompound);
        edit.analysisCompound(tagCompound);
        return compoundLibraryApi.setCompound(itemStack,itemApi);
    }

    /**
     * 获取编辑类型
     * @return Item
     */
    @Override
    public EditObjectTypeEnum getEditObjectType() {
        return EditObjectTypeEnum.ITEM;
    }

    /**
     *
     * @return
     */
    public HandleMessagesItemEvent handleMessagesItemEvent(){
        return this.handleMessages.getItemEvent();
    }

    /**
     *
     * @return
     */
    public UUID getUUID(){
        return this.uuid;
    }

    /**
     * 删除节点，派生两个子类, List类型和Compound，数据结构不一样所以该方法为抽象方法
     * @param var
     * @param itemStack
     * @param slot
     * @return 删除NBT数据的物品
     */
    public abstract ItemStack remove(Object var, ItemStack itemStack, int slot);

    /**
     * 更新节点数据，在添加或者删除节点后该链表的数据都会发生改变，需要更新各各节点的数据
     * @param key
     * @param slot
     * @param tagBase
     * @param panelEditItem
     * @return 更新后的物品
     */
    public abstract ItemStack updateNode(Object key, int slot, TagBase tagBase, AbstractPanelEditItem panelEditItem);


    /**
     * 该抽象方法在对象为头节点时为空数据，不为空说明该对象是父节点中的某个节点派生下来的，list数据类型getKey为int下标，Compound为String键
     * @return
     */
    public abstract Object getKey();

    /**
     * 该抽象方法在对象为头节点时为空数据，这是值nbt数据显示在界面的中某个槽位
     * @return
     */
    public abstract int getSlot();




}
