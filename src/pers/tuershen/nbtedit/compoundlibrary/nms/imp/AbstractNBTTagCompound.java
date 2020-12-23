package pers.tuershen.nbtedit.compoundlibrary.nms.imp;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.nbtedit.compoundlibrary.annotation.TagAnnotation;
import pers.tuershen.nbtedit.compoundlibrary.api.NBTTagCompoundApi;
import pers.tuershen.nbtedit.compoundlibrary.api.SerializableItemApi;
import pers.tuershen.nbtedit.compoundlibrary.nms.CraftItemStack;
import pers.tuershen.nbtedit.compoundlibrary.nms.MinecraftItemStack;
import pers.tuershen.nbtedit.compoundlibrary.nms.imp.difference.*;
import pers.tuershen.nbtedit.compoundlibrary.nms.io.SerializableFormat;
import pers.tuershen.nbtedit.compoundlibrary.nms.io.SerializableStream;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @auther Tuershen update Date on 2020/12/2
 */
public abstract class AbstractNBTTagCompound implements
        NBTTagCompoundApi, SerializableItemApi {


    protected Object nbtTagCompound;

    protected static AbstractMinecraftNBTTag minecraftNBTTag;

    protected static  Map<String, Class<? extends TagBase>> tagFactory = new HashMap<>();

    protected static  Map<String, Class<?>>                 nmsFactory = new HashMap<>();

    public static DifferenceCompoundEnum differenceCompoundEnum;

    public AbstractNBTTagCompound(){}

    public AbstractNBTTagCompound(Object nbtTagCompound){
        this.nbtTagCompound = nbtTagCompound;
    }

    public static void init(AbstractMinecraftNBTTag abstractMinecraftNBTTag, String version) {
        differenceCompoundEnum = DifferenceCompoundEnum.getInstance(version);
        minecraftNBTTag = abstractMinecraftNBTTag;
        tagFactory.put("byte",         TagByte.class);
        tagFactory.put("byteArray",    TagByteArray.class);
        tagFactory.put("double",       TagDouble.class);
        tagFactory.put("float",        TagFloat.class);
        tagFactory.put("int",          TagInt.class);
        tagFactory.put("intArray",     TagIntArray.class);
        tagFactory.put("long",         TagLong.class);
        tagFactory.put("longArray",    TagLongArray.class);
        tagFactory.put("short",        TagShort.class);
        tagFactory.put("string",       TagString.class);

        nmsFactory.put("TagByte",      byte.class);
        nmsFactory.put("TagByteArray", byte[].class);
        nmsFactory.put("TagDouble",    double.class);
        nmsFactory.put("TagFloat",     float.class);
        nmsFactory.put("TagInt",       int.class);
        nmsFactory.put("TagIntArray",  int[].class);
        nmsFactory.put("TagLong",      long.class);
        nmsFactory.put("TagLongArray", long[].class);
        nmsFactory.put("TagShort",     short.class);
        nmsFactory.put("TagString",    String.class);
        nmsFactory.put("TagList",      minecraftNBTTag.getNBTTagClass((byte) -1));
        nmsFactory.put("TagCompound",  minecraftNBTTag.getNBTTagClass((byte) -1));
    }

    public static NBTTagCompoundApi getMinecraftNBTTag(Object nbtTagCompound){
        return differenceCompoundEnum.getAbstractNBTTagCompound(nbtTagCompound);
    }

    public static SerializableItemApi getSerializableItemApi(){
        return differenceCompoundEnum.getSerializableItemApi();
    }

    /**
     *
     */
    public void baseNewNBTTagCompound() {
        try {
            this.nbtTagCompound = minecraftNBTTag.getNBTTagClass((byte)10).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param tagCompound
     */
    public void baseSetCompoundMap(TagCompound tagCompound){
        if (tagCompound != null){
            this.nbtTagCompound = deserializeNBTTagCompound(tagCompound);
        } else {
            this.nbtTagCompound =  newCompound();
        }
    }

    /**
     *
     * @param key
     * @param removeMethod
     */
    public void baseRemove(String key, String removeMethod){
        try {
            Method method = this.nbtTagCompound.getClass().getDeclaredMethod(removeMethod, String.class);
            method.invoke(this.nbtTagCompound,key);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }



    /**
     *
     * @return
     */
    public static Object newCompound(){
        try {
            return minecraftNBTTag.getNBTTagClass((byte) 10).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return minecraftNBTTag.getNBTTagClass((byte) 10);
    }


    /**
     * 通过方法名获取nmsNBT
     * @param key 节点名称
     * @param method 方法名称
     * @return 节点值
     */
    protected Object getBase(String key, String method){
        Object val = null;
        try {
            Method m = this.nbtTagCompound.getClass().getDeclaredMethod(method,String.class);
            val = m.invoke(this.nbtTagCompound,key);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return val;
    }

    /**
     *
     * @param key
     * @param value
     * @param methodName
     * @param clazz
     */
    protected void setBase(String key, Object value, String methodName, Class<?> clazz){
        try {
            Method method = this.nbtTagCompound.getClass().getDeclaredMethod(methodName, String.class, clazz);
            method.invoke(this.nbtTagCompound, key, value);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * @param obj
     * @param field
     * @return
     */
    protected TagList baseListCrossoverValue(Object obj, String field) {
        try {
            Field list = obj.getClass().getDeclaredField(field);
            if (list != null) {
                list.setAccessible(true);
                List<Object> bastList = (List<Object>) list.get(obj);
                TagList tagList = new TagList();
                if ((bastList != null) && (bastList.size() > 0)) {
                    for (Object objNBTTag : bastList) {
                        String bastNBTTagType = NBTTypeEnum.getInstance(objNBTTag.getClass()).getType();
                        TagBase value = fieldValueConversion(bastNBTTagType, objNBTTag);
                        if (value == null)continue;
                        tagList.add(value);
                    }
                    return tagList;
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return new TagList();
    }


    /**
     * 字段交换值
     * 获取NMS_NBTTag字段值
     * @param field 字段名称
     * @param nbtTag 字段对象
     * @param <T> TagBase子类
     * @return TagBase对应的nms对象
     */
    protected <T extends TagBase> T dataCrossoverValue(String field, Object nbtTag) {
        if (nbtTag != null) {
            try {
                Field data = nbtTag.getClass().getDeclaredField(field);
                if (data != null) {
                    data.setAccessible(true);
                    String type = NBTTypeEnum.getInstance(nbtTag.getClass()).getType();
                    Class<?> tagClass = tagFactory.get(type);
                    if (tagClass != null) {
                        Object subObj = tagClass.newInstance();
                        Method[] subMethods = subObj.getClass().getMethods();
                        for (Method subMethod : subMethods) {
                            if (subMethod.isAnnotationPresent(TagAnnotation.class)) {
                                subMethod.invoke(subObj, data.get(nbtTag));
                                return (T) subObj;
                            }
                        }
                    }
                }
            } catch (NoSuchFieldException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     *
     * @param obj
     * @return
     */
    protected TagCompound compoundCrossoverValue(Object obj){
        if (obj != null){
            Map<String, Object> map = this.fieldMapConversion(obj);
            Map<String, TagBase> baseMap = new HashMap<>();
            TagCompound tagCompound = new TagCompound();
            if (map.size() > 0){
                for (String next : map.keySet()) {
                    Object objNBTTag = map.get(next);
                    String nbtTagType = NBTTypeEnum.getInstance(objNBTTag.getClass()).getType();
                    TagBase tagBase = fieldValueConversion(nbtTagType, objNBTTag);
                    baseMap.put(next, tagBase);
                }
                tagCompound.setMap(baseMap);
                return tagCompound;
            }
        }
        return new TagCompound();
    }

    /**
     *
     * @param obj
     * @param field
     * @return
     */
    protected Map<String,Object> baseFieldMapConversion(Object obj, String field){
        try {
            Field map = obj.getClass().getDeclaredField(field);
            if (map != null ){
                Map<String,Object> mapObject;
                map.setAccessible(true);
                mapObject = (Map<String, Object>) map.get(obj);
                return mapObject;
            }
        } catch (NoSuchFieldException
                | IllegalAccessException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    /**
     *
     * @param key
     * @param base
     * @param method
     * @param <T>
     */
    public <T extends TagBase> void baseSetTag(String key, T base, String method){
        NBTSettingEnum instance = NBTSettingEnum.getInstance(base.getClass());
        if (instance != null) {
            instance.setValue(key, base, method, this);
        }
    }

    /**
     *
     * @param tagBase
     * @param <T>
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public <T extends TagBase> Object newBase(T tagBase) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        if (tagBase instanceof TagByte){
            byte data = ((TagByte) tagBase).getData();
            return createNBT(minecraftNBTTag.getNBTTagClass((byte) 1), data);
        }else if (tagBase instanceof TagByteArray){
            byte[] data = ((TagByteArray) tagBase).getData();
            return createNBT(minecraftNBTTag.getNBTTagClass((byte) 7), data);
        }else if (tagBase instanceof TagString){
            String data = ((TagString) tagBase).getData();
            return createNBT(minecraftNBTTag.getNBTTagClass((byte) 8), data);
        }else if (tagBase instanceof TagDouble){
            double data = ((TagDouble) tagBase).getData();
            return createNBT(minecraftNBTTag.getNBTTagClass((byte) 6), data);
        }else if (tagBase instanceof TagFloat){
            float data = ((TagFloat) tagBase).getData();
            return createNBT(minecraftNBTTag.getNBTTagClass((byte) 5), data);
        }else if (tagBase instanceof TagInt){
            int data = ((TagInt) tagBase).getData();
            return createNBT(minecraftNBTTag.getNBTTagClass((byte) 3), data);
        }else if (tagBase instanceof TagIntArray){
            int[] data = ((TagIntArray) tagBase).getData();
            return createNBT(minecraftNBTTag.getNBTTagClass((byte) 11), data);
        }else if (tagBase instanceof TagLong){
            long data = ((TagLong) tagBase).getData();
            return createNBT(minecraftNBTTag.getNBTTagClass((byte) 4), data);
        }else if (tagBase instanceof TagShort){
            short data = ((TagShort) tagBase).getData();
            return createNBT(minecraftNBTTag.getNBTTagClass((byte) 2), data);
        }else if (tagBase instanceof TagList){
            return newNBTTagList(tagBase);
        }else if (tagBase instanceof TagCompound){
            return newNBTTagCompound(tagBase);
        }
        return null;
    }

    /**
     *
     * @param nbt
     * @param data
     * @return
     */
    public Object createNBT(Class<?> nbt, Object data){
        try {
            Constructor<?> constructor = nbt.getConstructor(data.getClass());
            Object example = constructor.newInstance(data);
            Field[] declaredFields = example.getClass().getDeclaredFields();
            for (int i = 0; i < declaredFields.length; i++) {
                if (declaredFields[i].getType().getSimpleName().equalsIgnoreCase(data.getClass().getSimpleName())){
                    declaredFields[i].setAccessible(true);
                    declaredFields[i].set(example, data);
                    break;
                }
            }
            return example;
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param sub
     * @param addMethod
     * @param <T>
     * @return
     */
    public <T extends TagBase> Object baseNewList(T sub, String addMethod) {
        TagList tagList = (TagList) sub;
        List<TagBase> tagBases = tagList.getData();
        Object nbtTagList = null;
        try {
            nbtTagList = minecraftNBTTag.getNBTTagClass((byte)9).newInstance();
            Class<?> nbtTagBaseClass = minecraftNBTTag.getNBTTagClass((byte)-1);
            Method method = nbtTagList.getClass().getDeclaredMethod(addMethod, nbtTagBaseClass);
            for (TagBase tagBase : tagBases){
                Object value = newBase(tagBase);
                method.invoke(nbtTagList, value);
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return nbtTagList;
    }


    /**
     *
     * @param sub
     * @param method
     * @param <T>
     * @return
     */
    public <T extends TagBase> Object baseNewCompound(T sub, String method) {
        TagCompound tagCompound = (TagCompound)sub;
        Map<String, TagBase> tagBaseMap = tagCompound.getMap();
        Iterator<String> itr = tagBaseMap.keySet().iterator();
        Object nbtCompoundObj = null;
        try {
            nbtCompoundObj = minecraftNBTTag.getNBTTagClass((byte)10).newInstance();
            Field field =  nbtCompoundObj.getClass().getDeclaredField(method);
            field.setAccessible(true);
            Map<String,Object> nbtCompoundMap = (Map<String, Object>)field.get(nbtCompoundObj);
            while (itr.hasNext()){
                String next = itr.next();
                nbtCompoundMap.put(next,newBase(tagBaseMap.get(next)));
            }
            field.set(nbtCompoundObj,nbtCompoundMap);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException | NoSuchFieldException e) {
            e.printStackTrace();
        }

        return nbtCompoundObj;
    }


    /**
     * 交换NBT
     * @param nbtTagCompound  TagCompound
     * @return net.minecraft.server.NBTTagCompound
     */
    public Object deserializeNBTTagCompound(TagCompound nbtTagCompound) {
        if (nbtTagCompound != null){
            Map<String, TagBase> compoundMap = nbtTagCompound.getMap();
            if (compoundMap != null){
                Iterator<String> iterator = compoundMap.keySet().iterator();
                try {
                    Object nmsNBTTagCompound = newCompound();
                    while (iterator.hasNext()){
                        String next = iterator.next();
                        TagBase tagBase = compoundMap.get(next);
                        if (tagBase == null) continue;
                        String setMethodType = setMethodType(tagBase.getClass());
                        Object tagValue = NBTSettingEnum.getInstance(tagBase.getClass()).getFiledData(tagBase, this);
                        Class<?> clazz = nmsFactory.get(tagBase.getClass().getSimpleName());
                        if (clazz != null){
                            Method method = nmsNBTTagCompound.getClass().getDeclaredMethod(setMethodType, String.class, clazz);
                            method.invoke(nmsNBTTagCompound, next, tagValue);
                        }
                    }
                    return nmsNBTTagCompound;
                } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     *
     * @param list
     * @param addMethod
     * @return
     */
    public Object baseAssemblingTagList(List<TagBase> list, String addMethod) {
        try {
            Object tagList = minecraftNBTTag.getNBTTagClass((byte)9).newInstance();
            Method add = tagList.getClass().getDeclaredMethod(addMethod, minecraftNBTTag.getNBTTagClass((byte)-1));
            for (TagBase nbtTagBase : list) {
                Object value = newInstance_NBTBase(nbtTagBase);
                add.invoke(tagList,value);
            }
            return tagList;
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     *
     * @param tagBase
     * @return
     */
    protected Object newInstance_NBTBase(TagBase tagBase) {
        switch (tagBase.getClass().getSimpleName()) {
            case "TagByte":
            case "TagByteArray":
            case "TagDouble":
            case "TagFloat":
            case "TagInt":
            case "TagIntArray":
            case "TagLong":
            case "TagShort":
            case "TagString":
            case "TagLongArray":
                String type = tagBase.getClass().getSimpleName();
                Class<?> tagClass = minecraftNBTTag.getNBTTagClass(tagBase.getTypeId());
                try {
                    Constructor<?> constructor = tagClass.getConstructor(nmsFactory.get(type));
                    constructor.setAccessible(true);
                    return minecraftNBTTag.getNBTTagClass((byte)-1).cast(constructor.newInstance(tagBase.data()));
                } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                return null;
            case "TagCompound":
                return deserializeNBTTagCompound((TagCompound) tagBase);
            case "TagList":
                return assemblingTagList(((TagList)tagBase).getData());
        }
        return null;
    }

    /**
     * 反序列化
     * @param paramString 值
     * @return org.bukkit.invetory.ItemStack
     */
    @Override
    public ItemStack deserialize(String paramString) {
        SerializableFormat serializableFormat = SerializableStream.deserializeObj(paramString);
        TagCompound tagCompound = serializableFormat.getTagCompound();
        Object tag = deserializeNBTTagCompound(tagCompound);
        ItemStack itemStack = newItemStack(serializableFormat.getId().split(":"));
        MinecraftItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);
        if (tag != null){
            nmsItem.setMinecraftItemStackTag(tag);
        }
        return CraftItemStack.asBukkitCopy(nmsItem);
    }

    /**
     * 序列化物品
     * @param item  org.bukkit.invetory.ItemStack
     * @return 由二进制转String
     */
    @Override
    public String serialize(ItemStack item) {
        Object obj = CraftItemStack.asNMSCopy(item).getMinecraftItemStackTag().getNMSCompound();
        TagCompound tagCompound = compoundCrossoverValue(obj);
        String id = combination(item);
        return SerializableStream.getByteStream(new SerializableFormat(id, tagCompound));
    }


    /**
     *
     * @param t
     * @param <T>
     * @return
     */
    @Override
    public <T extends TagBase> String getTagBaseByteStream(T t) {
        return SerializableStream.getTagBaseByteStream(t);
    }

    /**
     *
     * @param data 源数据
     * @param <T>
     * @return
     */
    @Override
    public <T extends TagBase> T deserializeTagBase(String data) {
        return SerializableStream.deserializeTagBase(data);
    }



    /**
     * 创建ItemStack实例
     * @param id
     * @return
     */
    public ItemStack newItemStack(String... id){
        if (id.length == 1){
            return new ItemStack(Material.valueOf(id[0]),1);
        }
        return new ItemStack(Material.valueOf(id[0]), 1, Short.parseShort(id[1]));
    }

    /**
     *
     * @param itemStack
     * @return
     */
    protected static String combination(ItemStack itemStack){
        return itemStack.getDurability() == 0 ? itemStack.getType().name() : itemStack.getType().name()+":"+itemStack.getDurability();
    }

    public abstract TagList listCrossoverValue(Object nbtTagList);

    public abstract Map<String,Object> fieldMapConversion(Object nbtTagCompound);

    public abstract TagBase fieldValueConversion(String type, Object obj);

    public abstract String getString(String key);

    public abstract boolean getBoolean(String key);

    public abstract byte getByte(String key);

    public abstract byte[] getByteArray(String key);

    public abstract double getDouble(String key);

    public abstract float getFloat(String key);

    public abstract int getInt(String key);

    public abstract int[] getIntArray(String key);

    public abstract NBTTagCompoundApi getCompound(String key);

    public abstract <T extends TagBase> T get(String key);

    public abstract boolean hasKey(String key);

    public abstract void setString(String key, String value);

    public abstract void setBoolean(String key, boolean value);

    public abstract void setByte(String key, byte value);

    public abstract void setByteArray(String key, byte[] value);

    public abstract void setDouble(String key, double value);

    public abstract void setFloat(String key, float value);

    public abstract void setInt(String key, int value);

    public abstract void setIntArray(String key, int[] value);

    public abstract void setLong(String key, long value);

    public abstract void setShort(String key, short value);

    public abstract NBTTagCompoundApi newCompoundApi();

    public abstract <T extends TagBase> void set(String key, T base);

    public abstract Object getNMSCompound();

    public abstract void newNBTTagCompound();

    public abstract TagCompound getNBTTagCompoundApi();

    public abstract void remove(String key);

    public abstract void setCompoundMap(TagCompound tagCompound);

    public abstract <T extends TagBase> Object newNBTTagList(T tagBase);

    public abstract <T extends TagBase> Object newNBTTagCompound(T tagBase);

    public abstract String setMethodType(Class<?> nbtClass);

    public abstract Object assemblingTagList(List<TagBase> list);

    protected enum NBTSettingEnum implements NBTSetting {
        BYTE(TagByte.class){
            @Override
            public <T extends TagBase> void setValue(String key, T base, String method, AbstractNBTTagCompound baseCompound) {
                baseCompound.setByte(key,((TagByte) base).getData());
            }

            @Override
            public <T extends TagBase> Object getFiledData(T tabBase, AbstractNBTTagCompound baseCompound) {
                return ((TagByte) tabBase).getData();
            }
        },
        BYTE_ARRAY(TagByteArray.class){
            @Override
            public <T extends TagBase> void setValue(String key, T base, String method, AbstractNBTTagCompound baseCompound) {
                baseCompound.setByteArray(key,((TagByteArray) base).getData());
            }

            @Override
            public <T extends TagBase> Object getFiledData(T tabBase, AbstractNBTTagCompound baseCompound) {
                return ((TagByteArray)tabBase).getData();
            }
        },
        STRING(TagString.class){
            @Override
            public <T extends TagBase> void setValue(String key, T base, String method, AbstractNBTTagCompound baseCompound) {
                baseCompound.setString(key,((TagString) base).getData());
            }

            @Override
            public <T extends TagBase> Object getFiledData(T tabBase, AbstractNBTTagCompound baseCompound) {
                return ((TagString)tabBase).getData();
            }
        },
        DOUBLE(TagDouble.class){
            @Override
            public <T extends TagBase> void setValue(String key, T base, String method, AbstractNBTTagCompound baseCompound) {
                baseCompound.setDouble(key,((TagDouble) base).getData());
            }

            @Override
            public <T extends TagBase> Object getFiledData(T tabBase, AbstractNBTTagCompound baseCompound) {
                return ((TagDouble)tabBase).getData();
            }
        },
        FLOAT(TagFloat.class){
            @Override
            public <T extends TagBase> void setValue(String key, T base, String method, AbstractNBTTagCompound baseCompound) {
                baseCompound.setFloat(key,((TagFloat) base).getData());
            }

            @Override
            public <T extends TagBase> Object getFiledData(T tabBase, AbstractNBTTagCompound baseCompound) {
                return ((TagFloat)tabBase).getData();
            }
        },
        INT(TagInt.class){
            @Override
            public <T extends TagBase> void setValue(String key, T base, String method, AbstractNBTTagCompound baseCompound) {
                baseCompound.setInt(key,((TagInt) base).getData());
            }

            @Override
            public <T extends TagBase> Object getFiledData(T tabBase, AbstractNBTTagCompound baseCompound) {
                return ((TagInt)tabBase).getData();
            }
        },
        INT_ARRAY(TagIntArray.class){
            @Override
            public <T extends TagBase> void setValue(String key, T base, String method, AbstractNBTTagCompound baseCompound) {
                baseCompound.setIntArray(key,((TagIntArray) base).getData());
            }

            @Override
            public <T extends TagBase> Object getFiledData(T tabBase, AbstractNBTTagCompound baseCompound) {
                return ((TagIntArray)tabBase).getData();
            }
        },
        LONG(TagLong.class){
            @Override
            public <T extends TagBase> void setValue(String key, T base, String method, AbstractNBTTagCompound baseCompound) {
                baseCompound.setLong(key,((TagLong) base).getData());
            }

            @Override
            public <T extends TagBase> Object getFiledData(T tabBase, AbstractNBTTagCompound baseCompound) {
                return ((TagLong)tabBase).getData();
            }
        },
        SHORT(TagShort.class) {
            @Override
            public <T extends TagBase> void setValue(String key, T base, String method, AbstractNBTTagCompound baseCompound) {
                baseCompound.setShort(key,((TagShort) base).getData());
            }

            @Override
            public <T extends TagBase> Object getFiledData(T tabBase, AbstractNBTTagCompound baseCompound) {
                return ((TagShort)tabBase).getData();
            }
        },
        LIST(TagList.class) {
            @Override
            public <T extends TagBase> void setValue(String key, T base, String method, AbstractNBTTagCompound baseCompound) {
                try {
                    baseCompound.setBase(key, baseCompound.newBase(base), method, minecraftNBTTag.getNBTTagClass((byte)-1));
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public <T extends TagBase> Object getFiledData(T tabBase, AbstractNBTTagCompound baseCompound) {
                return baseCompound.assemblingTagList(((TagList)tabBase).getData());
            }
        },
        COMPOUND(TagCompound.class){
            @Override
            public <T extends TagBase> void setValue(String key, T base, String method, AbstractNBTTagCompound baseCompound) {
                try {
                    baseCompound.setBase(key, baseCompound.newBase(base), method, minecraftNBTTag.getNBTTagClass((byte)-1));
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public <T extends TagBase> Object getFiledData(T tabBase, AbstractNBTTagCompound abstractNBTTagCompound) {
                return abstractNBTTagCompound.deserializeNBTTagCompound((TagCompound) tabBase);
            }
        };

        Class<?> tagClass;

        NBTSettingEnum(Class<?> tagClass) {
            this.tagClass = tagClass;
        }

        public static NBTSettingEnum getInstance(Class<?> tagClass) {
            NBTSettingEnum[] nbtSettingEnums = NBTSettingEnum.values();
            for (NBTSettingEnum nbtSettingEnum : nbtSettingEnums) {
                if (nbtSettingEnum.tagClass.getSimpleName().equalsIgnoreCase(tagClass.getSimpleName())){
                    return nbtSettingEnum;
                }
            }
            return null;
        }


    }

    protected enum NBTTypeEnum implements NBTType {
        NBT_END("NBTTagEnd") {              @Override public String getType() { return "end"; }},
        NBT_SHORT("NBTTagShort"){           @Override public String getType() { return "short"; }},
        NBT_BYTE("NBTTagByte") {            @Override public String getType() { return "byte"; }},
        NBT_INT("NBTTagInt") {              @Override public String getType() { return "int"; }},
        NBT_LONG("NBTTagLong") {            @Override public String getType() { return "long"; }},
        NBT_DOUBLE("NBTTagDouble"){         @Override public String getType() { return "double"; }},
        NBT_FLOAT("NBTTagFloat") {          @Override public String getType() { return "float"; }},
        NBT_BYTE_ARRAY("NBTTagByteArray") { @Override public String getType() { return "byteArray"; }},
        NBT_STRING("NBTTagString") {        @Override public String getType() { return "string"; }},
        NBT_LIST("NBTTagList") {            @Override public String getType() { return "list"; }},
        NBT_COMPOUND("NBTTagCompound") {    @Override public String getType() { return "nbtTagCompound"; }},
        NBT_INT_ARRAY("NBTTagIntArray") {   @Override public String getType() { return "intArray"; }},
        NBT_LONG_ARRAY("NBTTagLongArray") { @Override public String getType() { return "longArray"; }};

        private String nbtType;

        NBTTypeEnum(String nbtType) {
            this.nbtType = nbtType;
        }

        /**
         * Class 为 NMS NBT类型
         * 根据Class的Simple名称获取枚举
         * @param clazzType
         * @return
         */
        public static NBTTypeEnum getInstance(Class<?> clazzType) {
            NBTTypeEnum[] nbtTypeEnums = NBTTypeEnum.values();
            for (NBTTypeEnum nbtTypeEnum : nbtTypeEnums) {
                if (nbtTypeEnum.nbtType.equalsIgnoreCase(clazzType.getSimpleName())){
                    return nbtTypeEnum;
                }
            }
            return NBTTypeEnum.NBT_END;
        }

    }

    protected interface NBTSetting {
        <T extends TagBase> void setValue(String key, T base, String method, AbstractNBTTagCompound abstractNBTTagCompound);

        <T extends TagBase> Object getFiledData(T tabBase, AbstractNBTTagCompound abstractNBTTagCompound);
    }

    protected interface NBTType {
        String getType();
    }

}


enum DifferenceCompoundEnum implements DifferenceCompound {

    v1_6_R3("v1_6_R3"){
        @Override public AbstractNBTTagCompound getAbstractNBTTagCompound(Object nbtTagCompound) { return NBTImp_v1_6_R3.getInstance(nbtTagCompound); }
        @Override public SerializableItemApi getSerializableItemApi() {
            return NBTImp_v1_6_R3.serializableItemApi();
        }
    },
    V1_7_R1("v1_7_R1"){
        @Override public AbstractNBTTagCompound getAbstractNBTTagCompound(Object nbtTagCompound) { return NBTImp_v1_7_R1_R2_R3.getInstance(nbtTagCompound); }
        @Override public SerializableItemApi getSerializableItemApi() { return NBTImp_v1_7_R1_R2_R3.serializableItemApi(); }
    },
    V1_7_R2("v1_7_R2"){
        @Override public AbstractNBTTagCompound getAbstractNBTTagCompound(Object nbtTagCompound) { return NBTImp_v1_7_R1_R2_R3.getInstance(nbtTagCompound); }
        @Override public SerializableItemApi getSerializableItemApi() { return NBTImp_v1_7_R1_R2_R3.serializableItemApi(); }
    },
    V1_7_R3("v1_7_R3"){
        @Override public AbstractNBTTagCompound getAbstractNBTTagCompound(Object nbtTagCompound) { return NBTImp_v1_7_R1_R2_R3.getInstance(nbtTagCompound); }
        @Override public SerializableItemApi getSerializableItemApi() { return NBTImp_v1_7_R1_R2_R3.serializableItemApi(); }
    },
    V1_7_R4("v1_7_R4"){
        @Override public AbstractNBTTagCompound getAbstractNBTTagCompound(Object nbtTagCompound) { return NBTImp_v1_7_R4.getInstance(nbtTagCompound); }
        @Override public SerializableItemApi getSerializableItemApi() {
            return NBTImp_v1_7_R4.serializableItemApi();
        }
    },
    V1_8_R1("v1_8_R1"){
        @Override public NBTTagCompoundApi getAbstractNBTTagCompound(Object nbtTagCompound) { return NBTImp_v1_8_R1_R2_R3.getInstance(nbtTagCompound); }
        @Override public SerializableItemApi getSerializableItemApi() { return NBTImp_v1_8_R1_R2_R3.serializableItemApi(); }
    },
    V1_8_R2("v1_8_R2"){
        @Override public NBTTagCompoundApi getAbstractNBTTagCompound(Object nbtTagCompound) { return NBTImp_v1_8_R1_R2_R3.getInstance(nbtTagCompound); }
        @Override public SerializableItemApi getSerializableItemApi() { return NBTImp_v1_8_R1_R2_R3.serializableItemApi(); }
    },
    V1_8_R3("v1_8_R3"){
        @Override public NBTTagCompoundApi getAbstractNBTTagCompound(Object nbtTagCompound) { return NBTImp_v1_8_R1_R2_R3.getInstance(nbtTagCompound); }
        @Override public SerializableItemApi getSerializableItemApi() { return NBTImp_v1_8_R1_R2_R3.serializableItemApi(); }
    },
    V1_9_R1("v1_9_R1"){
        @Override public NBTTagCompoundApi getAbstractNBTTagCompound(Object nbtTagCompound) { return NBTImp_v1_9_R1_R2.getInstance(nbtTagCompound); }
        @Override public SerializableItemApi getSerializableItemApi() { return NBTImp_v1_9_R1_R2.serializableItemApi(); }
    },
    V1_9_R2("v1_9_R2"){
        @Override public NBTTagCompoundApi getAbstractNBTTagCompound(Object nbtTagCompound) { return NBTImp_v1_9_R1_R2.getInstance(nbtTagCompound); }
        @Override public SerializableItemApi getSerializableItemApi() { return NBTImp_v1_9_R1_R2.serializableItemApi(); }
    },
    V1_10_R1("v1_10_R1") {
        @Override public AbstractNBTTagCompound getAbstractNBTTagCompound(Object nbtTagCompound) { return NBTImp_v1_10_R1.getInstance(nbtTagCompound); }
        @Override public SerializableItemApi getSerializableItemApi() { return NBTImp_v1_10_R1.serializableItemApi(); }
    },
    V1_11_R1("v1_11_R1") {
        @Override public AbstractNBTTagCompound getAbstractNBTTagCompound(Object nbtTagCompound) { return NBTImp_v1_11_R1.getInstance(nbtTagCompound); }
        @Override public SerializableItemApi getSerializableItemApi() { return NBTImp_v1_11_R1.serializableItemApi(); }
    },
    V1_12_R1("v1_12_R1") {
        @Override public AbstractNBTTagCompound getAbstractNBTTagCompound(Object nbtTagCompound) { return NBTImp_v1_12_R1.getInstance(nbtTagCompound); }
        @Override public SerializableItemApi getSerializableItemApi() { return NBTImp_v1_12_R1.serializableItemApi(); }
    };

    String version;

    DifferenceCompoundEnum(String version) {
        this.version = version;
    }

    public static DifferenceCompoundEnum getInstance(String version){
        DifferenceCompoundEnum[] differenceCompoundEnums = DifferenceCompoundEnum.values();
        for (DifferenceCompoundEnum differenceCompoundEnum : differenceCompoundEnums) {
            if (differenceCompoundEnum.version.equalsIgnoreCase(version)){
                return differenceCompoundEnum;
            }
        }
        return null;
    }

}

interface DifferenceCompound {

    NBTTagCompoundApi getAbstractNBTTagCompound(Object nbtTagCompound);

    SerializableItemApi getSerializableItemApi();


}
