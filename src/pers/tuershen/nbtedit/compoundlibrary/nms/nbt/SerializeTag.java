package pers.tuershen.nbtedit.compoundlibrary.nms.nbt;

import pers.tuershen.nbtedit.compoundlibrary.annotation.TagAnnotation;
import pers.tuershen.nbtedit.compoundlibrary.api.SerializableItemApi;
import pers.tuershen.nbtedit.compoundlibrary.nms.CraftItemStack;
import pers.tuershen.nbtedit.compoundlibrary.nms.MinecraftItemStack;
import pers.tuershen.nbtedit.compoundlibrary.nms.io.SerializableFormat;
import pers.tuershen.nbtedit.compoundlibrary.nms.io.SerializableStream;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class SerializeTag implements SerializableItemApi {

    /**
     * 反序列化
     * @param paramString 值
     * @return org.bukkit.invetory.ItemStack
     */
    @Override
    public ItemStack deserialize(String paramString) {
        //反序列得到SerializableFormat，包含id和nbt
        SerializableFormat serializableFormat = SerializableStream.deserializeObj(paramString);
        //获取nbt接口
        TagCompound tagCompound = serializableFormat.getTagCompound();
        //与TagCompound交换nbt得到nbtNBTTTagCompound
        Object tag = deserializeNBTTagCompound(tagCompound);
        //获取id是否包含子id
        ItemStack itemStack = newItemStack(serializableFormat.getId().split(":"));
        //获取nmsItemStack实例
        MinecraftItemStack nmsItem = CraftItemStack.asNMSCopy(itemStack);
        //如果该物品的nbt不为空
        if (tag != null){
            //设置该物品的nbt
            nmsItem.setMinecraftItemStackTag(tag);
        }
        //没有nbt则直接返回org.bukkit.invetory.ItemStack
        return CraftItemStack.asBukkitCopy(nmsItem);
    }

    /**
     * 序列化物品
     * @param paramItemStack  org.bukkit.invetory.ItemStack
     * @return 由二进制转String
     */
    @Override
    public String serialize(ItemStack paramItemStack) {
        //获取nmsNBTTagCompound
        Object obj = CraftItemStack.asNMSCopy(paramItemStack).getMinecraftItemStackTag().getNMSCompound();
        //new对象来保存itemStack的数据
        SerializableFormat serializableFormat = new SerializableFormat();
        //交换nbt得到TagCompound实例，该TagCompound的数据结构与nbtNBTTagCompound结构一样
        TagCompound tagCompound = getTagCompound(obj);
        //获取id
        String id = paramItemStack.getDurability() == 0
                ? paramItemStack.getType().name()
                : paramItemStack.getType().name()+":"+paramItemStack.getDurability();
        //保存id
        serializableFormat.setId(id);
        //保存nbt
        serializableFormat.setTagCompound(tagCompound);
        //序列化
        return SerializableStream.getByteStream(serializableFormat);
    }

    @Override
    public <T extends TagBase> String getTagBaseByteStream(T t) {
        return SerializableStream.getTagBaseByteStream(t);
    }

    @Override
    public <T extends TagBase> T deserializeTagBase(String data) {
        return SerializableStream.deserializeTagBase(data);
    }


    /**
     * 交换nmsNBTTagCompound
     * @param obj nmsNBTTagCompound
     * @return      * @return
     */
   protected TagCompound getTagCompound(Object obj){
        if (obj != null){
            //获取nms_NBTTagCompound的map字段，该字段包含物品的所有nbt
            Map<String,Object> map = getNBTTagCompoundMap(obj);
            //我们需要new一个同样的结构来保存nms_NBTTagCompound的map数据
            Map<String, TagBase> baseMap = new HashMap<>();
            //TagCompound
            TagCompound tagCompound = new TagCompound();
            //如果nms_NBTTagCompound.map的数据如果是大于0说明是有数据的
            if (map.size() > 0){
                //迭代所有key
                for (String next : map.keySet()) {
                    //map.get(next) 获取nms_NBTTagCompound_map的节点数据
                    Object objNBTTag = map.get(next);
                    //获取nms_NBTTagCompound_map.get(next).value所对应的
                    String nbtTagType = TagBase.getNBTTagType(objNBTTag.getClass());
                    TagBase tagBase = getFieldValue(nbtTagType, objNBTTag);
                    baseMap.put(next, tagBase);
                }
                tagCompound.setMap(baseMap);
                return tagCompound;
            }
        }
        return new TagCompound();
    }


    Map<String,Object> getNBTTagCompoundMap(Object obj){
        try {
            Field map = obj.getClass().getDeclaredField("map");
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

    public TagBase getFieldValue(String type, Object obj) {
        switch (type) {
            case "byte":
            case "short":
            case "int":
            case "long":
            case "float":
            case "double":
            case "byteArray":
            case "string":
            case "intArray":
                return getNBTTagFieldData("data", obj);
            case "longArray":
                return getNBTTagFieldData("b", obj);
            case "list":
                return getBastList(obj);
            case "nbtTagCompound":
                return getTagCompound(obj);
        }
        return null;
    }

    /**
     * 强制获取NMS_NBTTag字段值
     * @param field 字段名称
     * @param obj 字段对象
     * @param <T> TagBase子类
     * @return TagBase对应的nms对象
     */
    protected  <T extends TagBase> T getNBTTagFieldData(String field, Object obj) {
        if (obj != null) {
            try {
                //获取字段
                Field data = obj.getClass().getDeclaredField(field);
                if (data != null) {
                    //强制注入
                    data.setAccessible(true);
                    //获取数据类型
                    String type = TagBase.getNBTTagType(obj.getClass());
                    //TagBases数据类型
                    Class<?> subClazz = TagBase.tagFactory.get(type);
                    if (subClazz != null) {
                        //插件TagBase实例
                        Object subObj = subClazz.newInstance();
                        //获取TagBase对象中的所有方法
                        Method[] subMethods = subObj.getClass().getMethods();
                        //遍历它
                        for (Method subMethod : subMethods) {
                            //方法是否含有TagAnnotation注解
                            if (subMethod.isAnnotationPresent(TagAnnotation.class)) {
                                //执行该方法，其作用是获取nms对象字段值赋值给TagBase对象
                                subMethod.invoke(subObj, data.get(obj));
                                return (T) subObj;
                            }
                        }
                    }
                }
            } catch (NoSuchFieldException
                    | IllegalAccessException
                    | InstantiationException
                    | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    TagList getBastList(Object obj) {
        try {
            Field list = obj.getClass().getDeclaredField("list");
            if (list != null) {
                list.setAccessible(true);
                List<Object> bastList = (List<Object>) list.get(obj);
                TagList tagList = new TagList();
                if ((bastList != null) && (bastList.size() > 0)) {
                    for (Object o : bastList) {
                        String bastNBTTagType =TagBase.getNBTTagType(o.getClass());
                        TagBase value = getFieldValue(bastNBTTagType, o);
                        if (value == null)continue;
                        tagList.add(value);
                    }
                    return tagList;
                }
            }
        } catch (NoSuchFieldException
                | IllegalAccessException e) {
            e.printStackTrace();
        }
        return new TagList();
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
                    Object nmsNBTTagCompound = TagBase.newNBTTagCompound().newInstance();
                    while (iterator.hasNext()){
                        String next = iterator.next();
                        TagBase tagBase = compoundMap.get(next);
                        if (tagBase == null)continue;
                        String tagType = TagBase.getNmsNBTTagType(tagBase.getClass());
                        Object tagValue = getNBTTagBastValue(tagBase);
                        Class<?> clazz = TagBase.nmsFactory.get(tagBase.getClass().getTypeName());
                        if (clazz != null){
                            Method method = nmsNBTTagCompound.getClass().getDeclaredMethod(tagType, String.class, clazz);
                            method.invoke(nmsNBTTagCompound, next, tagValue);
                        }
                    }
                    return nmsNBTTagCompound;
                } catch (IllegalAccessException
                        | NoSuchMethodException
                        | InvocationTargetException
                        | InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }



    /**
     *
     * @param tagBase
     * @return
     */
    public <T> Object getNBTTagBastValue(T tagBase) {

        if ((tagBase instanceof TagByte)) {
            return ((TagByte) tagBase).getData();
        }
        if ((tagBase instanceof TagByteArray)) {
            return ((TagByteArray)tagBase).getData();
        }
        if ((tagBase instanceof TagDouble)) {
            return ((TagDouble) tagBase).getData();
        }
        if ((tagBase instanceof TagFloat)) {
            return ((TagFloat) tagBase).getData();
        }
        if ((tagBase instanceof TagInt)) {
            return ((TagInt) tagBase).getData();
        }
        if ((tagBase instanceof TagIntArray)) {
            return ((TagIntArray)tagBase).getData();
        }
        if ((tagBase instanceof TagLong)) {
            return ((TagLong) tagBase).getData();
        }
        if ((tagBase instanceof TagLongArray)) {
            return ((TagLongArray)tagBase).getData();
        }
        if ((tagBase instanceof TagShort)) {
            return ((TagShort) tagBase).getData();
        }
        if ((tagBase instanceof TagString)) {
            return ((TagString)tagBase).getData();
        }
        if ((tagBase instanceof TagList)) {
            return getList(((TagList)tagBase).getData());
        }
        if ((tagBase instanceof TagCompound)) {
            return deserializeNBTTagCompound((TagCompound)tagBase);
        }


        return null;
    }

    protected <T> Object newNMSNBTTag(TagBase tagBase) {
        switch (tagBase.getClass().getTypeName()) {
            case "pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagByte":
            case "pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagByteArray":
            case "pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagDouble":
            case "pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagFloat":
            case "pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagInt":
            case "pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagIntArray":
            case "pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagLong":
            case "pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagShort":
            case "pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagString":
            case "pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagLongArray":
                String type = tagBase.getClass().getTypeName();
                Class<?> tagClass = TagBase.getNBTTag(tagBase.getTypeId());
                try {
                    Constructor<?> constructor = tagClass.getConstructor(TagBase.nmsFactory.get(type));
                    constructor.setAccessible(true);
                    return TagBase.newNBTTagBase().cast(constructor.newInstance(tagBase.data()));
                } catch (NoSuchMethodException
                        | IllegalAccessException
                        | InstantiationException
                        | InvocationTargetException e) {
                    e.printStackTrace();
                }
                return null;
            case "pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagCompound":
                return deserializeNBTTagCompound((TagCompound) tagBase);
            case "pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagList":
                return getList(((TagList)tagBase).getData());
        }
        return null;
    }

    public Object getList(List<TagBase> list) {
        try {
            Object tagList = TagBase.newNBTTagList().newInstance();
            Method method = tagList.getClass().getDeclaredMethod("add", TagBase.newNBTTagBase());
            for (TagBase nbtTagBase : list) {
                Object value = newNMSNBTTag(nbtTagBase);
                method.invoke(tagList,value);
            }
            return tagList;
        } catch (IllegalAccessException
                | InstantiationException
                | NoSuchMethodException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
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

    public Class<?> getNBTTagBaseClass(){
      return TagBase.newNBTTagBase();
    }

    public Class<?> getNBTTagClass(byte typeByte){
        return TagBase.getNBTTag(typeByte);
    }

    public <T> String getNBTTagType(Class<T> nmsClass){
        return TagBase.getNBTTagType(nmsClass);
    }





}

