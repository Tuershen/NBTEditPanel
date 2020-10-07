package pers.tuershen.nbtedit.setting.handle;

import org.bukkit.entity.Player;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.*;
import pers.tuershen.nbtedit.setting.ButtonPromptMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public enum ButtonEnum implements ButtonPromptMessage {

    BYTE(36){
        @Override
        public String receiverMsg(){
            return "§eⒷ §2Byte类型:请输入一个-128-127的整数";
        }

        @Override
        public TagBase newNBTTag(String data, Player player) {
            if (matches(data)){
                player.sendMessage("§c错误! "+receiverMsg());
                return null;
            }
            return new TagByte((byte)NumericalEnum.BYTE.conversion(data));
        }
    },
    BYTE_ARRAY(37) {
        @Override
        public String receiverMsg() {
            return "§eⒷ §2ByteArray数组类型:";
        }

        @Override
        public TagBase newNBTTag(String data, Player player) {
            if (matches(data)){
                player.sendMessage("§c错误! "+receiverMsg());
                return null;
            }
            return new TagByte((byte)NumericalEnum.BYTE.conversion(data));
        }
    },
    COMPOUND(53) {
        @Override
        public String receiverMsg() {
            return "§eⒸ §2创建一个Compound复合类型";
        }

        @Override
        public TagBase newNBTTag(String data, Player player) {
            Map<String, TagBase> tagBaseMap = new HashMap<>();
            tagBaseMap.put("1",new TagString("占位符，右键可删除"));
            return new TagCompound(tagBaseMap);
        }
    },
    DOUBLE(39) {
        @Override
        public String receiverMsg() {
            return "§eⒹ §2Double浮点类型:请输入一个大小为1.797693e+308~ 4.9000000e-324的小数点类型";
        }

        @Override
        public TagBase newNBTTag(String data, Player player) {
            if (matches(data)){
                player.sendMessage("§c 错误! "+receiverMsg());
                return null;
            }
            return new TagDouble((double)NumericalEnum.DOUBLE.conversion(data));
        }
    },
    FLOAT(40) {
        @Override
        public String receiverMsg() {
            return "§eⒻ §2Float浮点类型:请输入一个大小为3.402823e+38 ~ 1.401298e-45的小数点类型";
        }

        @Override
        public TagBase newNBTTag(String data, Player player) {
            if (matches(data)){
                player.sendMessage("§c 错误! "+receiverMsg());
                return null;
            }
            return new TagFloat((float)NumericalEnum.FLOAT.conversion(data));
        }
    },
    INT(41) {
        @Override
        public String receiverMsg() {
            return "§eⓘ §2Int整形:请输入一个大小为-2147483648~2147483647的整数";
        }

        @Override
        public TagBase newNBTTag(String data, Player player) {
            if (matches(data)){
                player.sendMessage("§c错误! "+receiverMsg());
                return null;
            }
            return new TagInt((int)NumericalEnum.INT.conversion(data));
        }
    },
    INT_ARRAY(42) {
        @Override
        public String receiverMsg( ) {
            return "§eⓘ §2Int创建整型数组";
        }

        @Override
        public TagBase newNBTTag(String data, Player player) {

            System.out.println(data);

            if (matches(data)){
                player.sendMessage("§c错误! "+receiverMsg());
                return null;
            }
            return new TagInt((int)NumericalEnum.INT.conversion(data));
        }
    },
    LIST(45) {
        @Override
        public String receiverMsg( ) {
            return "§eⒾ §2创建List集合，需要在集合内添加值";
        }

        @Override
        public TagBase newNBTTag(String data, Player player) {
            List<TagBase> list = new ArrayList<>();
            return new TagList(list);
        }
    },
    LONG(43) {
        @Override
        public String receiverMsg( ) {
            return "§eⓁ §2Long整形: 请输入一个大小为-"+Long.MIN_VALUE+"~"+Long.MAX_VALUE+"的整数";
        }
        @Override
        public TagBase newNBTTag(String data, Player player) {
            if (matches(data)){
                player.sendMessage("错误!"+receiverMsg());
                return null;
            }
            return new TagLong((long)NumericalEnum.LONG.conversion(data));
        }
    },
    SHORT(44) {
        @Override
        public String receiverMsg() {
            return "§eⒻ §2Short浮点类型: 请输入一个大小为-"+Short.MIN_VALUE+"~"+Short.MAX_VALUE+"的整数";
        }

        @Override
        public TagBase newNBTTag(String data, Player player) {
            if (matches(data)){
                player.sendMessage("错误!"+receiverMsg());
                return null;
            }
            return new TagShort((short)NumericalEnum.SHORT.conversion(data));
        }
    },
    STRING(38) {
        @Override
        public String receiverMsg( ) {
            return "§eⓈ §2String类型: 请随意输入";
        }

        @Override
        public TagBase newNBTTag(String data, Player player) {
            return new TagString(data);
        }
    };

    private int slot;

    private static final String                          regex = "^\\d{1,16}(\\.\\d{1,12})?$";

    ButtonEnum(int i) {
        this.slot = i;
    }

    public static ButtonEnum slot(int slot){
        for (ButtonEnum buttonEnum : ButtonEnum.values()){
            if (buttonEnum.slot == slot)return buttonEnum;
        }
        return null;
    }

    public static boolean matches(String data){
        return !data.matches(regex);
    }



}
