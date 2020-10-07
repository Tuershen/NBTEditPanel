package pers.tuershen.nbtedit.compoundlibrary.nms.nbt;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class TagBase implements Serializable {

    //序列化版本id
    private   static final long                                        serialVersionUID  = 658655221344413188L;

    //子类工厂
    protected static transient Map<String,Class<? extends TagBase>>    tagFactory        = new HashMap<>();

    //nms子类工厂
    protected static transient Map<String,Class<?>>                    nmsFactory        = new HashMap<>();

    //net.minecraft.sever.NBTTagCompound
    protected static transient Class<?>
            nbtTagCompoundClass;

    //net.minecraft.sever.NBTTagList
    protected static transient Class<?>
            nbtTagList;

    //net.minecraft.sever.NBTTagBase
    protected static transient Class<?>
            nbtTagBase;
    //net.minecraft.sever.NBTTagBase
    protected static transient Class<?>
            nbtTagDouble;
    //net.minecraft.sever.NBTTagBase
    protected static transient Class<?>
            nbtTagByte;

    //net.minecraft.sever.NBTTagBase
    protected static transient Class<?>
            nbtTagByteArray;

    //net.minecraft.sever.NBTTagBase
    protected static transient Class<?>
            nbtTagFloat;

    //net.minecraft.sever.NBTTagBase
    protected static transient Class<?>
            nbtTagInt;

    //net.minecraft.sever.NBTTagBase
    protected static transient Class<?>
            nbtTagIntArray;

    //net.minecraft.sever.NBTTagBase
    protected static transient Class<?>
            nbtTagLong;

    //net.minecraft.sever.NBTTagBase
    protected static transient Class<?>
            nbtTagShort;

    //net.minecraft.sever.NBTTagBase
    protected static transient Class<?>
            nbtTagString;

    protected static transient Class<?>
            nbtTagLongArray;


    public static void intiFactoryClass(String version){
        tagFactory.put("byte"                                                       , TagByte.class);
        tagFactory.put("byteArray"                                                  , TagByteArray.class);
        tagFactory.put("double"                                                     , TagDouble.class);
        tagFactory.put("float"                                                      , TagFloat.class);
        tagFactory.put("int"                                                        , TagInt.class);
        tagFactory.put("intArray"                                                   , TagIntArray.class);
        tagFactory.put("long"                                                       , TagLong.class);
        tagFactory.put("longArray"                                                  , TagLongArray.class);
        tagFactory.put("short"                                                      , TagShort.class);
        tagFactory.put("string"                                                     , TagString.class);
        nmsFactory.put("pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagByte"      , byte.class);
        nmsFactory.put("pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagByteArray" , byte[].class);
        nmsFactory.put("pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagDouble"    , double.class);
        nmsFactory.put("pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagFloat"     , float.class);
        nmsFactory.put("pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagInt"       , int.class);
        nmsFactory.put("pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagIntArray"  , int[].class);
        nmsFactory.put("pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagLong"      , long.class);
        nmsFactory.put("pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagLongArray" , long[].class);
        nmsFactory.put("pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagShort"     , short.class);
        nmsFactory.put("pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagString"    , String.class);
        try {
            if (version.contains("v1_12")){
                nbtTagLongArray     = Class.forName("net.minecraft.server." + version + ".NBTTagLongArray");
            }
            nmsFactory.put("pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagList"    ,      Class.forName("net.minecraft.server." + version + ".NBTBase"));
            nmsFactory.put("pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagCompound",      Class.forName("net.minecraft.server." + version + ".NBTBase"));
            nbtTagCompoundClass = Class.forName("net.minecraft.server." + version + ".NBTTagCompound");
            nbtTagList          = Class.forName("net.minecraft.server." + version + ".NBTTagList");
            nbtTagBase          = Class.forName("net.minecraft.server." + version + ".NBTBase");
            nbtTagByte          = Class.forName("net.minecraft.server." + version + ".NBTTagByte");
            nbtTagByteArray     = Class.forName("net.minecraft.server." + version + ".NBTTagByteArray");
            nbtTagInt           = Class.forName("net.minecraft.server." + version + ".NBTTagInt");
            nbtTagIntArray      = Class.forName("net.minecraft.server." + version + ".NBTTagIntArray");
            nbtTagFloat         = Class.forName("net.minecraft.server." + version + ".NBTTagFloat");
            nbtTagLong          = Class.forName("net.minecraft.server." + version + ".NBTTagLong");
            nbtTagString        = Class.forName("net.minecraft.server." + version + ".NBTTagString");
            nbtTagShort         = Class.forName("net.minecraft.server." + version + ".NBTTagShort");
            nbtTagDouble        = Class.forName("net.minecraft.server." + version + ".NBTTagDouble");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected static Class<?> newNBTTagCompound(){
        return nbtTagCompoundClass;
    }

    protected static Class<?> newNBTTagList(){
        return  nbtTagList;
    }

    protected static Class<?> newNBTTagBase(){
        return nbtTagBase;
    }



    protected static <T> String getNBTTagType(Class<T> nbtClass) {
        switch (nbtClass.getSimpleName()) {
            case "NBTTagEnd":
                return "end";
            case "NBTTagByte":
                return "byte";
            case "NBTTagShort":
                return "short";
            case "NBTTagInt":
                return "int";
            case "NBTTagLong":
                return "long";
            case "NBTTagFloat":
                return "float";
            case "NBTTagDouble":
                return "double";
            case "NBTTagByteArray":
                return "byteArray";
            case "NBTTagString":
                return "string";
            case "NBTTagList":
                return "list";
            case "NBTTagCompound":
                return "nbtTagCompound";
            case "NBTTagIntArray":
                return "intArray";
            case "NBTTagLongArray":
                return "longArray";
        }
        return null;
    }

    protected static <T> String getNmsNBTTagType(Class<T> clazz) {
        switch (clazz.getTypeName()) {
            case "pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagByte":
                return "setByte";
            case "pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagByteArray":
                return "setByteArray";
            case "pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagDouble":
                return "setDouble";
            case "pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagFloat":
                return "setFloat";
            case "pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagInt":
                return "setInt";
            case "pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagIntArray":
                return "setIntArray";
            case "pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagLong":
                return "setLong";
            case "pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagLongArray":
                return "setLongArray";
            case "pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagShort":
                return "setShort";
            case "pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagString":
                return "setString";
            case "pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagCompound":
            case "pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagList":
                return "set";
        }
        return null;
    }

    protected static Class<?> getNBTTag(byte typeByte){
        switch (typeByte){
            case 1:
                return nbtTagByte;
            case 2:
                return nbtTagShort;
            case 3:
                return nbtTagInt;
            case 4:
                return nbtTagLong;
            case 5:
                return nbtTagFloat;
            case 6:
                return nbtTagDouble;
            case 7:
                return nbtTagByteArray;
            case 8:
                return nbtTagString;
            case 9:
                return nbtTagList;
            case 10:
                return nbtTagCompoundClass;
            case 11:
                return nbtTagIntArray;
            case 12:
                return nbtTagLongArray;
        }
        return null;
    }

    public abstract Object data();

    public abstract byte getTypeId();



}
