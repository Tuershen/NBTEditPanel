package pers.tuershen.nbtedit.setting.type;

import org.bukkit.entity.Player;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.*;
import pers.tuershen.nbtedit.setting.handle.NBTTag;


public enum NewSingleNBT implements SingleNBT {

    BYTE((byte) 1){
        @Override
        public TagBase newNBT(String data, Player player) {
            if (matches(data)){
                player.sendMessage("§c错误! "+ NBTTag.type((byte) 1).tagMsg());
                return null;
            }
            return new TagByte((byte)NumericalEnum.BYTE.conversion(data));
        }
    },
    SHORT((byte)2) {
        @Override
        public TagBase newNBT(String data, Player player) {
            if (matches(data)){
                player.sendMessage("§c错误! "+ NBTTag.type((byte) 2).tagMsg());
                return null;
            }
            return new TagShort((short)NumericalEnum.SHORT.conversion(data));
        }
    },
    INT((byte)3) {
        @Override
        public TagBase newNBT(String data, Player player) {
            if (matches(data)){
                player.sendMessage("§c错误! "+ NBTTag.type((byte) 3).tagMsg());
                return null;
            }
            return new TagInt((int)NumericalEnum.INT.conversion(data));
        }
    },
    LONG((byte)4) {
        @Override
        public TagBase newNBT(String data, Player player) {
            if (matches(data)){
                player.sendMessage("§c错误! "+ NBTTag.type((byte) 4).tagMsg());
                return null;
            }
            return new TagLong((long) NumericalEnum.LONG.conversion(data));
        }
    },
    FLOAT((byte)5) {
        @Override
        public TagBase newNBT(String data, Player player) {
            if (matches(data)){
                player.sendMessage("§c错误! "+ NBTTag.type((byte) 5).tagMsg());
                return null;
            }
            return new TagFloat((float)NumericalEnum.FLOAT.conversion(data));
        }
    },
    DOUBLE((byte)6) {
        @Override
        public TagBase newNBT(String data, Player player) {
            if (matches(data)){
                player.sendMessage("§c错误! "+ NBTTag.type((byte) 6).tagMsg());
                return null;
            }
            return new TagDouble((double)NumericalEnum.DOUBLE.conversion(data));
        }
    },
    STRING((byte)8) {
        @Override
        public TagBase newNBT(String data, Player player) {
            return new TagString(data);
        }
    };

    private byte type;

    private static final String regex = "^\\d{1,16}(\\.\\d{1,12})?$";

    public static boolean matches(String data){
        return !data.matches(regex);
    }

    NewSingleNBT(byte type) {
        this.type = type;
    }

    public static NewSingleNBT type(byte type){
        for (NewSingleNBT singleNBT : NewSingleNBT.values()){
            if (singleNBT.type == type)return singleNBT;
        }
        return null;
    }




}


interface SingleNBT {

    TagBase newNBT(String data, Player player);

}
