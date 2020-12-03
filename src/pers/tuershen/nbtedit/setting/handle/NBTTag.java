package pers.tuershen.nbtedit.setting.handle;


import pers.tuershen.nbtedit.setting.NewTag;

public enum NBTTag implements NewTag {

    BYTE((byte)1){@Override public String tagMsg( ) {
            return "请输入一个大小为-127-128的整数";
        }},
    SHORT((byte)2) {@Override public String tagMsg( ) {
            return "请输入一个大小为"+Short.MIN_VALUE+"~"+Short.MAX_VALUE+"的整数";
        }},
    INT((byte)3) {@Override public String tagMsg( ) { return "请输入一个大小为"+Integer.MIN_VALUE+"~"+Integer.MAX_VALUE+"的整数"; }},
    LONG((byte)4) {@Override public String tagMsg( ) {
            return "请输入一个大小为"+Long.MIN_VALUE+"~"+Long.MAX_VALUE+"的整数";
        }},
    FLOAT((byte)5) {@Override public String tagMsg( ) {
            return "请输入一个大小为"+Float.MIN_VALUE+"~"+Float.MAX_VALUE+"的数";
        }},
    DOUBLE((byte)6) {@Override public String tagMsg( ) { return "请输入一个大小为"+Double.MIN_VALUE+"~"+Double.MAX_VALUE+"的数"; }},
    STRING((byte) 8) {@Override public String tagMsg( ) {
            return "请随意输入";
        }};

    private byte type;

    NBTTag(byte type) {
        this.type = type;
    }

    public static NBTTag type(byte type){
        for (NBTTag nbtTag : NBTTag.values()){
            if (nbtTag.type == type)return nbtTag;
        }
        return null;
    }


}
