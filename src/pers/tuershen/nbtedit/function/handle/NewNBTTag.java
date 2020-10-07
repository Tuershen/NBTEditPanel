package pers.tuershen.nbtedit.function.handle;

import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.*;
import pers.tuershen.nbtedit.setting.handle.NumericalEnum;

public enum NewNBTTag implements NBTTagApi {


    BYTE((byte) 1){
        @Override
        public pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagBase newTag(Object params) {
            if (matches(params.toString())){
                return null;
            }
            return new TagByte((byte) NumericalEnum
                    .BYTE
                    .conversion(params.toString()));
        }
    },
    SHORT((byte)2) {
        @Override
        public pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagBase newTag(Object data) {
            if (matches(data.toString())){
                return null;
            }
            return new TagShort((short)NumericalEnum
                    .SHORT
                    .conversion(data.toString()));
        }
    },
    INT((byte)3) {
        @Override
        public pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagBase newTag(Object data) {
            if (matches(data.toString())){
                return null;
            }
            return new TagInt((int)NumericalEnum
                    .INT
                    .conversion(data.toString()));
        }
    },
    LONG((byte)4) {
        @Override
        public TagBase newTag(Object data ) {
            if (matches(data.toString())){
                return null;
            }
            return new TagLong((long) NumericalEnum
                    .LONG
                    .conversion(data.toString()));
        }
    },
    FLOAT((byte)5) {
        @Override
        public TagBase newTag(Object data) {
            if (matches(data.toString())){
                return null;
            }
            return new TagFloat((float)NumericalEnum
                    .FLOAT
                    .conversion(data.toString()));
        }
    },
    DOUBLE((byte)6) {
        @Override
        public TagBase newTag(Object data) {
                if (matches(data.toString())){
                return null;
            }
            return new TagDouble((double)NumericalEnum
                    .DOUBLE
                    .conversion(data.toString()));
        }
    },
    STRING((byte)8) {
        @Override
        public TagBase newTag(Object data) {
            return new TagString(data.toString()
                    .replace("&","§")
                    .replace("&s"," "));
        }
    };

    private byte type;

    private static final String regex = "^\\d{1,16}(\\.\\d{1,12})?$";

    public static boolean matches(String data){
        return !data.matches(regex);
    }

    NewNBTTag(byte type) {
        this.type = type;
    }

    public static NewNBTTag type(byte type){
        for (NewNBTTag singleNBT : NewNBTTag.values()){
            if (singleNBT.type == type)return singleNBT;
        }
        return null;
    }




}
