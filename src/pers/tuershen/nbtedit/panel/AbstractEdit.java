package pers.tuershen.nbtedit.panel;

import pers.tuershen.nbtedit.compoundlibrary.api.CompoundLibraryApi;
import pers.tuershen.nbtedit.compoundlibrary.api.NBTTagCompoundApi;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagBase;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagCompound;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagList;
import pers.tuershen.nbtedit.function.AbstractEditManager;
import pers.tuershen.nbtedit.function.annotation.EditDescribe;
import pers.tuershen.nbtedit.event.listener.HandleMessages;
import pers.tuershen.nbtedit.setting.handle.*;
import pers.tuershen.nbtedit.setting.type.ButtonEnum;
import pers.tuershen.nbtedit.setting.type.NewSingleNBT;
import pers.tuershen.nbtedit.setting.type.TagBaseEnum;

import java.lang.reflect.Method;
import java.util.*;

public abstract class AbstractEdit extends AbstractPanel implements InventoryHolder {

    //可用最大槽数
    protected static final int MAX_EffECTIVE_SLOT = 35;

    //接口集
    protected static CompoundLibraryApi compoundLibraryApi;

    //玩家uuid
    protected UUID uuid;

    //输入事件
    protected HandleMessages handleMessages = new HandleMessages();

    public AbstractEdit(UUID uuid) {
        this.uuid = uuid;
    }

    public static void init(CompoundLibraryApi api) {
        compoundLibraryApi = api;
    }

    protected Inventory settingDefaultPanel(AbstractEdit edit) {
        Inventory inventory = Bukkit.createInventory(edit, MAX_SLOT, "NBT编辑器");
        for (int i = 45; i <= 53; i++) {
            inventory.setItem(i, ToolsFunction.functionItem());
        }
        inventory.setItem(PREVIOUS_SLOT_POS, ToolsFunction. previousButton());
        inventory.setItem(MIDDLE_SLOT_POS, ToolsFunction. middleButton());
        inventory.setItem(NEXT_SLOT_POS, ToolsFunction. nextButton());
        ToolsFunction.nbtBaseTypeButton(inventory);
        return inventory;
    }

    public ItemStack setSlotItemStack(Object key, TagBase tagBase, boolean isNumber) {
        if (tagBase != null) {
            TagBaseEnum tagBaseEnum = TagBaseEnum.getBaseType(tagBase.getClass().getSimpleName());
            ItemStack itemStack = tagBaseEnum.setStyle(key, tagBase, isNumber);
            return setSlotNBTTag(key,tagBase,itemStack,compoundLibraryApi.getCompound(itemStack));
        }
        return null;
    }



    public <T extends TagBase> String setBase64(T tag, CompoundLibraryApi compoundLibraryApi){
        return compoundLibraryApi.getSerializeItem().getTagBaseByteStream(tag);
    }

    public TagBase deserializeTagBase(ItemStack itemStack){
        return compoundLibraryApi.getSerializeItem().deserializeTagBase(compoundLibraryApi.getCompound(itemStack).getString("NBTTagBase64"));
    }

    public String getSlotNBTTagKey(ItemStack itemStack){
        return compoundLibraryApi.getCompound(itemStack).getString("NBTTagBaseKey");
    }

    public byte getTypeId(int slot){
       return SlotTypeOfEnum.typeOf(slot).slotTypeOf();
    }

    public String typeOf(byte typeByte){
        return TypeOfEnum.typeOf(typeByte).typeOf();
    }



    public <T extends TagBase> ItemStack setSlotNBTTag(Object note, T tag, ItemStack itemStack, NBTTagCompoundApi compoundApi) {
        if (compoundApi != null){
            compoundApi.setString("NBTTagBase64",setBase64(tag,compoundLibraryApi));
            compoundApi.setString("NBTTagBaseKey",note.toString());
            return compoundLibraryApi.setCompound(itemStack,compoundApi);
        }
        return this.setSlotNBTTag(note, tag, itemStack, compoundApi.newCompoundApi());
    }

    public String[] getFunctionToolsMsg(AbstractEditManager manager){
        Method[] methods = manager.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(EditDescribe.class)) {
                EditDescribe describe = method.getAnnotation(EditDescribe.class);
                return describe.describe();
            }
        }
        return null;
    }


    public boolean triggerMsg(AbstractEditManager editManager, Player player){
        String[] msg = this.getFunctionToolsMsg(editManager);
        if (msg != null){
            for (int i = 0; i < msg.length ; i++) {
                player.sendMessage(msg[i]);
            }
            return false;
        }
        return true;
    }

    public void triggerSetCompoundMsg(Object key, TagBase tagBase, Player player){
        if (isCompoundType(tagBase)) return;
        player.closeInventory();
        player.sendMessage("§eⒸ §a节点名称: §b"+ key);
        player.sendMessage("§eⒸ §a节点类型: §b"+ TypeOfEnum.typeOf(tagBase.getTypeId()).typeOf());
        NBTTag tag = NBTTag.type(tagBase.getTypeId());
        player.sendMessage("§eⒸ §2"+tag.tagMsg());
    }

    public void triggerAddCompoundMsg(int slot, Player player){
        ButtonEnum buttonEnum = ButtonEnum.slot(slot);
        player.closeInventory();
        player.sendMessage("§eⒸ §d当前数据类型为:Compound，需要输入两个以上参数,第一个必须为键，其余为值");
        player.sendMessage(buttonEnum.receiverMsg());
    }

    public void triggerSetListMsg(Object key,TagBase tagBase, Player player){
        if (isCompoundType(tagBase)) return;
        player.closeInventory();
        player.sendMessage("§eⒺ §a节点索引: §b"+ key);
        player.sendMessage("§eⒺ §a节点类型: §b"+ TypeOfEnum.typeOf(tagBase.getTypeId()).typeOf());
        NBTTag tag = NBTTag.type(tagBase.getTypeId());
        player.sendMessage("§eⒺ §2"+tag.tagMsg());
    }

    public void triggerAddArrayMsg(Player player, int slot, int typeId){
        player.closeInventory();
        if (getTypeId(slot) == 0){
            player.sendMessage("§eⒺ §cList集合内不能嵌套§aList§c");
            return;
        }
        if (typeId != getTypeId(slot) && typeId != 0){
            player.sendMessage("§eⒺ §c当前集合内的数据类型是§a"+ this.typeOf((byte) typeId) +"§c，你只能添加同类型的数据!");
            return;
        }
        //提示
        // 玩家输入数据
        player.sendMessage("§eⒺ §b当前数据类型为:List，需要输入一个以上参数");
        player.sendMessage(ButtonEnum.slot(slot).receiverMsg());
    }

    public boolean isCompoundType(TagBase tagBase){
        return tagBase.getTypeId() == 10 || tagBase.getTypeId() == 9;
    }

    public TagBase newSingleNBT(byte type, String msg, Player player){
        return NewSingleNBT.type(type).newNBT(msg,player);
    }

    public Inventory analysisCompound(TagCompound tagCompound) {
        Map<String, TagBase> tagBaseMap = tagCompound.getMap();
        Iterator<String> itr = tagBaseMap.keySet().iterator();
        int slot = 0;
        int pos = 1;
        int nullNumber = 0;
        Inventory inventory = settingDefaultPanel(this);
        while (itr.hasNext()) {
            String next = itr.next();
            TagBase tagBase = tagBaseMap.get(next);
            if (tagBase == null) {
                pos++;
                nullNumber++;
            }
            if (slot >= MAX_EffECTIVE_SLOT || pos == (tagBaseMap.size() + nullNumber)) {
                inventory.setItem(slot, setSlotItemStack(next, tagBase, false));
                this.inventoryList.add(inventory);
                inventory = settingDefaultPanel(this);
                slot = 0;
                pos++;
                continue;
            }
            inventory.setItem(slot, setSlotItemStack(next, tagBase, false));
            slot++;
            pos++;

        }
        this.panel = this.inventoryList.size() <= 0 ? inventory : this.inventoryList.get(nowPage);
        return this.panel;
    }


    public Inventory analysisList(TagList list){
        List<TagBase> tagBaseList = list.getData();
        this.page = (tagBaseList.size() / MAX_EffECTIVE_SLOT) + 1;
        int slot = 0;
        for (int j = 0; j < this.page; j++) {
            Inventory inventory = settingDefaultPanel(this);
            for (int i = 0;  ((i <= MAX_EffECTIVE_SLOT) && (slot < tagBaseList.size())); i++,slot++) {
                TagBase tagBase = tagBaseList.get(slot);
                if (tagBase == null) continue;
                inventory.setItem(i,setSlotItemStack(i,tagBase,true));
            }
            this.inventoryList.add(inventory);
        }
        this.panel = this.inventoryList.get(this.nowPage);
        return this.panel;
    }

    //首次打开界面
    public abstract Inventory newOpenPanel();

    //编辑类型
    public abstract EditObjectTypeEnum getEditObjectType();

    public abstract AbstractEdit getEdit();

    public abstract TagBase getTagBase();

}

interface TypeOf{ String typeOf();}
interface SlotTypeOf{ byte slotTypeOf();}
enum TypeOfEnum implements TypeOf{
    BYTE       ((byte) 1)  {@Override public String typeOf() {
        return "Byte";
    }},
    SHORT      ((byte) 2)  {@Override public String typeOf() {
        return "Short";
    }},
    INT        ((byte) 3)  {@Override public String typeOf() {
        return "Int";
    }},
    LONG       ((byte) 4)  {@Override public String typeOf() {
        return "Long";
    }},
    FLOAT      ((byte) 5)  {@Override public String typeOf() {
        return "Float";
    }},
    DOUBLE     ((byte) 6)  {@Override public String typeOf() {
        return "Double";
    }},
    BYTE_ARRAY ((byte) 7)  {@Override public String typeOf() {
        return "ByteArray";
    }},
    STRING     ((byte) 8)  {@Override public String typeOf() {
        return "String";
    }},
    LIST       ((byte) 9)  {@Override public String typeOf() {
        return "List";
    }},
    COMPOUND   ((byte) 10) {@Override public String typeOf() { return "Compound"; }},
    INT_ARRAY  ((byte) 11) {@Override public String typeOf() {
        return "IntArray";
    }},
    LONG_ARRAY ((byte) 12) {@Override public String typeOf() {
        return "LongArray";
    }};
    private int type;

    TypeOfEnum(int type) {
        this.type = type;
    }

    public static TypeOfEnum typeOf(int type){
        TypeOfEnum[] typeOfEnums = TypeOfEnum.values();
        for (TypeOfEnum typeOfEnum : typeOfEnums){
            if (typeOfEnum.type == type) return typeOfEnum;
        }
        return null;
    }
}

enum SlotTypeOfEnum implements SlotTypeOf{
    SLOT_36(36) {@Override public byte slotTypeOf() {
            return 1;
        }},
    SLOT_37(37) {@Override public byte slotTypeOf() {
            return 7;
        }},
    SLOT_38(38) {@Override public byte slotTypeOf() {
            return 8;
        }},
    SLOT_39(39) {@Override public byte slotTypeOf() { return 6; }},
    SLOT_40(40) {@Override public byte slotTypeOf() {
            return 5;
        }},
    SLOT_41(41) {@Override public byte slotTypeOf() {
            return 3;
        }},
    SLOT_42(42) {@Override public byte slotTypeOf() { return 11; }},
    SLOT_43(43) {@Override public byte slotTypeOf() { return 4; }},
    SLOT_44(44) {@Override public byte slotTypeOf() {
            return 2;
        }},
    SLOT_45(45) {@Override public byte slotTypeOf() {
            return 0;
        }},
    SLOT_53(53) {@Override public byte slotTypeOf() {
            return 10;
        }};

    private int slot;

    SlotTypeOfEnum(int slot) {
        this.slot = slot;
    }

    public static SlotTypeOfEnum typeOf(int slot){
        SlotTypeOfEnum[] typeOfEnums = SlotTypeOfEnum.values();
        for (SlotTypeOfEnum typeOfEnum : typeOfEnums){
            if (typeOfEnum.slot == slot) return typeOfEnum;
        }
        return null;
    }
}



