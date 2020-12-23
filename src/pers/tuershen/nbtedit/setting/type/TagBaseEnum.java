package pers.tuershen.nbtedit.setting.type;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.*;


import java.util.*;

interface TagBaseStack {

    ItemStack setStyle(Object key, TagBase tagBase, boolean isNumber);

}

public enum  TagBaseEnum implements TagBaseStack {

    BYTE("TagByte"){
        @Override public ItemStack setStyle(Object key, TagBase tagBase, boolean isNumber) {
            return setStyle(editButtonMap.get("Byte"),
                    key,
                    "Byte",
                    "§7字节类型-取值范围-128-127",
                    isNumber,
                    String.valueOf(tagBase.data()));
        }
    },


    BYTE_ARRAY("TagByteArray"){
        @Override public ItemStack setStyle(Object key, TagBase tagBase, boolean isNumber) {
            return setStyle(editButtonMap.get("ByteArray"),
                    key,
                    "ByteArray",
                    "§7字节数组类型-单个取值范围-128-127",
                    isNumber,
                    String.valueOf(tagBase.data()));
        }
    },


    COMPOUND("TagCompound"){
        @Override public ItemStack setStyle(Object key, TagBase tagBase, boolean isNumber) {
            return setCompoundStyle(editButtonMap.get("Compound"),
                    key,
                    "Compound",
                    (TagCompound)tagBase,
                    "§7哈希数据结构-一键对应一值，属于复合类型",
                    isNumber,
                    getNBTTagCompound(((TagCompound)tagBase).getMap()));
        }
    },


    DOUBLE("TagDouble"){
        @Override public ItemStack setStyle(Object key, TagBase tagBase, boolean isNumber) {
            return setStyle(editButtonMap.get("Double"),
                    key,
                    "Double",
                    "§7浮点类型-单个取值范围1.797693e+308~ 4.9000000e-324",
                    isNumber,
                    String.valueOf(tagBase.data()));
        }
    },


    FLOAT("TagFloat"){
        @Override public ItemStack setStyle(Object key, TagBase tagBase, boolean isNumber) {
            return setStyle(editButtonMap.get("Float"),
                    key,
                    "Float",
                    "§7浮点型类型-单个取值范围3.402823e+38 ~ 1.401298e-45",
                    isNumber,
                    String.valueOf(tagBase.data()));
        }
    },


    INT("TagInt"){
        @Override public ItemStack setStyle(Object key , TagBase tagBase, boolean isNumber) {
            return setStyle(editButtonMap.get("Int"),
                    key,
                    "Int",
                    "§7整型数组类型-单个取值范围-2147483648~2147483647",
                    isNumber,
                    String.valueOf(tagBase.data()));
        }
    },


   INT_ARRAY("TagIntArray"){
        @Override public ItemStack setStyle(Object key, TagBase tagBase, boolean isNumber) {
            return setStyle(editButtonMap.get("IntArray"),
                    key,
                    "IntArray",
                    "§7整型数组类型-单个取值范围-2147483648~2147483647",
                    isNumber,
                    String.valueOf(tagBase.data()));
        }
    },


    LIST("TagList"){
        @Override public ItemStack setStyle(Object key, TagBase tagBase, boolean isNumber) {
            return setListStyle(editButtonMap.get("List"),
                    key,
                    "List",
                    (TagList)tagBase,
                    "§7数据集-一个集合内只能添加一种数据类型",
                    isNumber,
                    getNBTTagList(((TagList)tagBase).getData()));
        }
    },


    LONG("TagLong"){
        @Override public ItemStack setStyle(Object key, TagBase tagBase, boolean isNumber) {
            return setStyle(editButtonMap.get("Long"),
                    key,
                    "Long",
                    "§7整型-单个取值范围-9223372036854774808~9223372036854774807",
                    isNumber,
                    String.valueOf(tagBase.data()));
        }
    },


    LONG_ARRAY("TagLongArray"){
        @Override public ItemStack setStyle(Object key, TagBase tagBase, boolean isNumber) {
            return setStyle(editButtonMap.get("LongArray"),
                    key,
                    "LongArray",
                    "§7整型Long数组-单个取值范围-9223372036854774808~9223372036854774807",
                    isNumber,
                    String.valueOf(tagBase.data()));
        }
    },


    SHORT("TagShort"){
        @Override public ItemStack setStyle(Object key, TagBase tagBase, boolean isNumber) {
            return setStyle(editButtonMap.get("Short"),
                    key,
                    "Short",
                    "§7整型-单个取值范围-32768~32767",
                    isNumber,
                    String.valueOf(tagBase.data()));
        }
    },


    STRING("TagString"){
        @Override public ItemStack setStyle(Object key, TagBase tagBase, boolean isNumber) {
            return setStyle(editButtonMap.get("String"),
                    key,
                    "String",
                    "§7字符类型类型-取值随意",
                    isNumber,
                    String.valueOf(tagBase.data()));
        }
    };

    protected static Map<Integer, String> specialSymbols = new HashMap<>();

    //所有Tag按钮
    protected static Map<String, ItemStack> editButtonMap = new HashMap<>();

    static {
        specialSymbols.put(0, "⓪");
        specialSymbols.put(1, "➀");
        specialSymbols.put(2, "➁");
        specialSymbols.put(3, "➂");
        specialSymbols.put(4, "➃");
        specialSymbols.put(5, "➄");
        specialSymbols.put(6, "➅");
        specialSymbols.put(7, "➆");
        specialSymbols.put(8, "➇");
        specialSymbols.put(9, "➈");
        editButtonMap.put("Compound", new ItemStack(Material.BOOK_AND_QUILL));
        editButtonMap.put("List", new ItemStack(Material.BOOK));
        editButtonMap.put("Byte", new ItemStack(Material.WOOL, 1, (short)0));
        editButtonMap.put("ByteArray", new ItemStack(Material.WOOL, 1, (short)1));
        editButtonMap.put("String", new ItemStack(Material.WOOL, 1, (short)2));
        editButtonMap.put("Double", new ItemStack(Material.WOOL, 1, (short)3));
        editButtonMap.put("Float", new ItemStack(Material.WOOL, 1, (short)4));
        editButtonMap.put("Int", new ItemStack(Material.WOOL, 1, (short)5));
        editButtonMap.put("IntArray", new ItemStack(Material.WOOL, 1, (short)6));
        editButtonMap.put("Long", new ItemStack(Material.WOOL, 1, (short)7));
        editButtonMap.put("Short", new ItemStack(Material.THIN_GLASS, 1));
    }

    String tagType;

    TagBaseEnum(String tagType) {
        this.tagType = tagType;
    }


    protected static ItemStack setStyle(ItemStack itemStack, Object key, String type, String explain, boolean isNumber,
                              String... data) {
        setSlotExplain(itemStack, key, type, explain, isNumber);
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> list = itemMeta.getLore();
        for (int i = 0; i < data.length; i++) {
            list.add(i + 3, "§7" + getSymbol(i + 1) + " §e值: §2" + data[i]);
        }
        itemMeta.setLore(list);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }


    protected static ItemStack setSlotExplainBase(ItemStack itemStack, Object key, String type, String explain, boolean isNumber) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> list = new ArrayList<>();
        String disPlayer = DataTypeEnum.getDataType(isNumber).msg(key.toString());
        itemMeta.setDisplayName(disPlayer);
        list.add(0, "§3节点类型: §d" + type);
        list.add(1, "§3说明: §d" + explain);
        list.add(2, "");
        itemMeta.setLore(list);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    protected static void setSlotExplain(ItemStack itemStack, Object key, String type, String explain, boolean isNumber) {
        setSlotExplainBase(itemStack, key, type, explain, isNumber);
    }

    protected static String getSymbol(int slot) {
        return getString(slot, specialSymbols);
    }

    protected static String getString(int slot, Map<Integer, String> specialSymbols) {
        char[] chars = String.valueOf(slot).toCharArray();
        StringBuilder buffer = new StringBuilder();
        for (char aChar : chars) {
            int index = Integer.parseInt(String.valueOf(aChar));
            buffer.append(specialSymbols.get(index));
        }
        return buffer.toString();
    }

    protected static ItemStack setListStyle(ItemStack itemStack, Object key, String type, TagList tagList, String explain, boolean isNumber, String... data) {
        setSlotExplain(itemStack, key, type, explain, isNumber);
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> list = itemMeta.getLore();
        for (int i = 0; i < tagList.getData().size(); i++) {
            list.add(i + 3, "§7" + getSymbol(i + 1) + " §3值类型: §2" + data[i]);
        }
        itemMeta.setLore(list);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }



    protected static ItemStack setCompoundStyle(ItemStack itemStack, Object key, String type, TagCompound tagCompound, String explain, boolean isNumber, String... data) {
        setSlotExplain(itemStack, key, type, explain, isNumber);
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> list = itemMeta.getLore();
        Iterator<String> itr = tagCompound.getMap().keySet().iterator();
        int index = 0;
        while (itr.hasNext()) {
            String next = itr.next();
            list.add(index + 3, "§7" + getSymbol(index + 1) + " §3键: §e" + next + " §3值类型: §2" + data[index]);
            index++;
        }
        itemMeta.setLore(list);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    protected static String[] getNBTTagList(List<TagBase> list) {
        String[] array = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            String type = compoundTypeName(list.get(i));
            array[i] = type;
        }
        return array;
    }

    protected static String[] getNBTTagCompound(Map<String, TagBase> tagBaseMap) {
        Iterator<String> itr = tagBaseMap.keySet().iterator();
        int index = 0;
        String[] array = new String[tagBaseMap.size()];
        while (itr.hasNext()) {
            array[index] = compoundTypeName(tagBaseMap.get(itr.next()));
            index++;
        }
        return array;
    }



    protected static String compoundTypeName(TagBase tagBase) {
        if (tagBase instanceof TagByte) {
            return "Byte";
        } else if (tagBase instanceof TagByteArray) {
            return "ByteArray";
        } else if (tagBase instanceof TagString) {
            return "String";
        } else if (tagBase instanceof TagDouble) {
            return "Double";
        } else if (tagBase instanceof TagFloat) {
            return "Float";
        } else if (tagBase instanceof TagInt) {
            return "Int";
        } else if (tagBase instanceof TagIntArray) {
            return "IntArray";
        } else if (tagBase instanceof TagLong) {
            return "Long";
        } else if (tagBase instanceof TagShort) {
            return "Short";
        } else if (tagBase instanceof TagList) {
            return "List";
        } else if (tagBase instanceof TagCompound) {
            return "Compound";
        }
        return "null";
    }


    public static TagBaseEnum getBaseType(String type){
        for (TagBaseEnum tagBaseEnum : TagBaseEnum.values()){
            if (tagBaseEnum.tagType.equalsIgnoreCase(type))return tagBaseEnum;
        }
        return null;
    }


}


