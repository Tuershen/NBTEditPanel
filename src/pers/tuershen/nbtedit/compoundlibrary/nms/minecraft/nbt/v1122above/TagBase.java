package pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.v1122above;



/**
 * @auther Tuershen Create Date on 2020/12/23
 */
public interface TagBase {


   String[] a = new String[] { "END", "BYTE", "SHORT", "INT", "LONG", "FLOAT", "DOUBLE", "BYTE[]", "STRING", "LIST", "COMPOUND", "INT[]", "LONG[]" };

    static String getType(int var0) {
        switch (var0) {
            case 0:
                return "TAG_End";
            case 1:
                return "TAG_Byte";
            case 2:
                return "TAG_Short";
            case 3:
                return "TAG_Int";
            case 4:
                return "TAG_Long";
            case 5:
                return "TAG_Float";
            case 6:
                return "TAG_Double";
            case 7:
                return "TAG_Byte_Array";
            case 11:
                return "TAG_Int_Array";
            case 12:
                return "TAG_Long_Array";
            case 8:
                return "TAG_String";
            case 9:
                return "TAG_List";
            case 10:
                return "TAG_Compound";
            case 99:
                return "Any Numeric Tag";
        }
        return "UNKNOWN";
    }

}
