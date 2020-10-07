package pers.tuershen.nbtedit.compoundlibrary.nms;



import pers.tuershen.nbtedit.compoundlibrary.api.NBTTagCompoundApi;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.item.MinecraftItem;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.SerializeTag;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagBase;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagByte;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagCompound;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Compound extends SerializeTag implements NBTTagCompoundApi {

    private Object compound;

    private static Class<?> compoundClass;

    public static void initCompoundClass(){
        compoundClass = MinecraftItem.getInstance().classTagCompound();
    }

    public <T> Compound(T obj){
        this.compound = obj;
    }

    public Object getNMSCompound(){
        return this.compound;
    }

    protected static Object newCompound(){
        try {
            return compoundClass.newInstance();
        } catch (InstantiationException
                | IllegalAccessException e) {
            e.printStackTrace();
        }
        return compoundClass;
    }


    public void newNBTTagCompound(){
        try {
             this.compound = compoundClass.newInstance();
        } catch (InstantiationException
                | IllegalAccessException e) {
            e.printStackTrace();
        }
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
            Method m = this.compound.getClass().getDeclaredMethod(method,String.class);
            val = m.invoke(this.compound,key);
        } catch (NoSuchMethodException
                | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
        return val;
    }

    public <T extends TagBase> T get(String key){
        Object tagObj = getBase(key,"get");
        String clazzType = super.getNBTTagType(tagObj.getClass());
        return (T)super.getFieldValue(clazzType,tagObj);
    }

    /**
     * 获取String类型的NBT值
     * @param key 节点名称
     * @return value
     */
    public String getString(String key){
        return String.valueOf(this.getBase(key,"getString"));
    }

    /**
     * 获取Boolean类型的NBT值
     * @param key 节点名称
     * @return value
     */
    public boolean getBoolean(String key){
        return (boolean)this.getBase(key,"getBoolean");
    }

    /**
     * 获取byte类型的NBT值
     * @param key 节点名称
     * @return value
     */
    public byte getByte(String key){
        return (byte)this.getBase(key,"getByte");
    }

    /**
     * 获取byte[]类型的NBT值
     * @param key 节点名称
     * @return value
     */
    public byte[] getByteArray(String key){
        return (byte[])this.getBase(key,"getByteArray");
    }


    public pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagCompound getNBTTagCompoundApi(){
        return super.getTagCompound(this.compound);
    }

    @Override
    public void remove(String key) {
        try {
            Method method = this.compound.getClass().getDeclaredMethod("remove",String.class);
            method.invoke(this.compound,key);
        } catch (NoSuchMethodException
                | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取复合类型类型的NBT值
     * @param key 节点名称
     * @return value
     */
    public <T extends pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagBase> NBTTagCompoundApi getCompound(String key){
        try {
            Method method = this.compound.getClass().getDeclaredMethod("getCompound",String.class);
            Object obj = method.invoke(this.compound,key);
            return new Compound(obj);
        } catch (NoSuchMethodException
                | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 获取Double类型的NBT值
     * @param key 节点名称
     * @return value
     */
    public double getDouble(String key){
        return (double)this.getBase(key,"getDouble");
    }

    /**
     * 获取Float类型的NBT值
     * @param key 节点名称
     * @return value
     */
    public float getFloat(String key){
        return (float)this.getBase(key,"getFloat");
    }

    /**
     * 获取int类型的NBT值
     * @param key 节点名称
     * @return value
     */
    public int getInt(String key){
        return (int)this.getBase(key,"getInt");
    }

    /**
     * 获取int[]类型的NBT值
     * @param key 节点名称
     * @return value
     */
    public int[] getIntArray(String key){
        return (int[])this.getBase(key,"getIntArray");
    }



    public boolean hasKey(String key){
        return (boolean)this.getBase(key,"hasKey");
    }

    void setBase(String key, Object value, String methodName, Class<?> clazz){

        try {
            Method method = this.compound.getClass().getDeclaredMethod(methodName,String.class,clazz);
            method.invoke(this.compound,key,value);
        } catch (NoSuchMethodException
                | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }


    }


    public void setString(String key, String value){
        this.setBase(key,value,"setString",String.class);
    }

    public void setBoolean(String key, boolean value){
        this.setBase(key,value,"setBoolean",boolean.class);
    }

    public void setByte(String key, byte value){
        this.setBase(key,value,"setByte",byte.class);
    }

    public void setByteArray(String key, byte[] value){
        this.setBase(key,value,"setByteArray",byte[].class);
    }

    public void setDouble(String key, double value){
        this.setBase(key,value,"setDouble",double.class);
    }

    public void setFloat(String key, float value){
        this.setBase(key,value,"setFloat",float.class);
    }

    public void setInt(String key, int value){
        this.setBase(key,value,"setInt",int.class);
    }

    public void setIntArray(String key, int[] value){
        this.setBase(key,value,"intArray",int[].class);
    }

    public void setLong(String key, long value){
        this.setBase(key,value,"setLong",long.class);
    }

    public void setShort(String key, short value){
        this.setBase(key,value,"setShort",short.class);
    }

    public  NBTTagCompoundApi newCompoundApi(){
        Object obj = null;
        try {
            obj = compoundClass.newInstance();
        } catch (InstantiationException
                | IllegalAccessException e) {
            e.printStackTrace();
        }
        return new Compound(obj);
    }

    public <T extends pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagBase> void set(String key, T base){
        if (base instanceof pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagByte){
            this.setByte(key,((pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagByte) base).getData());
        }else if (base instanceof pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagByteArray){
            this.setByteArray(key,((pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagByteArray) base).getData());
        }else if (base instanceof pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagString){
            this.setString(key,((pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagString) base).getData());
        }else if (base instanceof pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagDouble){
            this.setDouble(key,((pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagDouble) base).getData());
        }else if (base instanceof pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagFloat){
            this.setFloat(key,((pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagFloat) base).getData());
        }else if (base instanceof pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagInt){
            this.setInt(key,((pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagInt) base).getData());
        }else if (base instanceof pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagIntArray){
            this.setIntArray(key,((pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagIntArray) base).getData());
        }else if (base instanceof pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagLong){
            this.setLong(key,((pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagLong) base).getData());
        }else if (base instanceof pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagShort){
            this.setShort(key,((pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagShort) base).getData());
        }else if (base instanceof pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagList || base instanceof pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagCompound){
            try {
                this.setBase(key, newNBTTag(base), "set", super.getNBTTagBaseClass());
            } catch (NoSuchMethodException
                    | InvocationTargetException
                    | IllegalAccessException
                    | InstantiationException e) {
                e.printStackTrace();
            }
        }
    }


    public <T extends pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagBase> Object newNBTTag(T sub) throws
            NoSuchMethodException,
            InvocationTargetException,
            IllegalAccessException,
            InstantiationException {
        if (sub instanceof pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagByte){
            return super.getNBTTagClass((byte) 1).getConstructor(byte.class).newInstance(((TagByte) sub).getData());
        }else if (sub instanceof pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagByteArray){
            return super.getNBTTagClass((byte) 7).getConstructor(byte[].class).newInstance(((pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagByteArray) sub).getData());
        }else if (sub instanceof pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagString){
            return super.getNBTTagClass((byte) 8).getConstructor(String.class).newInstance(((pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagString) sub).getData());
        }else if (sub instanceof pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagDouble){
            return super.getNBTTagClass((byte) 6).getConstructor(double.class).newInstance(((pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagDouble) sub).getData());
        }else if (sub instanceof pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagFloat){
            return super.getNBTTagClass((byte) 5).getConstructor(float.class).newInstance(((pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagFloat) sub).getData());
        }else if (sub instanceof pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagInt){
            return super.getNBTTagClass((byte) 3).getConstructor(int.class).newInstance(((pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagInt) sub).getData());
        }else if (sub instanceof pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagIntArray){
            return super.getNBTTagClass((byte) 11).getConstructor(int[].class).newInstance(((pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagIntArray) sub).getData());
        }else if (sub instanceof pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagLong){
            return super.getNBTTagClass((byte) 4).getConstructor(long.class).newInstance(((pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagLong) sub).getData());
        }else if (sub instanceof pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagShort){
            return super.getNBTTagClass((byte) 2).getConstructor(short.class).newInstance(((pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagShort) sub).getData());
        }else if (sub instanceof pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagList){
            return newListInstance(sub);
        }else if (sub instanceof pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagCompound){
            return newNBTTagCompoundInstance(sub);
        }
        return null;
    }

    public <T extends pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagBase> Object newListInstance(T sub) {
        pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagList tagList = (pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagList) sub;
        List<pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagBase> tagBases = tagList.getData();
        Object nbtList = null;
        try {
            nbtList = super.getNBTTagClass((byte)9).newInstance();
            Method method = nbtList.getClass().getDeclaredMethod("add",super.getNBTTagBaseClass());
            for (TagBase tagBase : tagBases){
                Object value = newNBTTag(tagBase);
                method.invoke(nbtList,value);
            }
        } catch (InstantiationException
                | IllegalAccessException
                | NoSuchMethodException
                | InvocationTargetException e) {
            e.printStackTrace();
        }

        return nbtList;
    }

    public <T extends TagBase> Object newNBTTagCompoundInstance(T sub) {
        TagCompound tagCompound = (TagCompound)sub;
        Map<String, TagBase> tagBaseMap = tagCompound.getMap();
        Iterator<String> itr = tagBaseMap.keySet().iterator();
        Object nbtCompoundObj = null;
        try {
            nbtCompoundObj = super.getNBTTagClass((byte)10).newInstance();
            Field field =  nbtCompoundObj.getClass().getDeclaredField("map");
            field.setAccessible(true);
            Map<String,Object> nbtCompoundMap = (Map<String, Object>)field.get(nbtCompoundObj);
            while (itr.hasNext()){
                String next = itr.next();
                nbtCompoundMap.put(next,newNBTTag(tagBaseMap.get(next)));
            }
            field.set(nbtCompoundObj,nbtCompoundMap);
        } catch (InstantiationException
                | IllegalAccessException
                | NoSuchMethodException
                | InvocationTargetException
                | NoSuchFieldException e) {
            e.printStackTrace();
        }

        return nbtCompoundObj;
    }

    public void setCompoundMap(TagCompound tagCompound){
        if (tagCompound != null){
            this.compound = super.deserializeNBTTagCompound(tagCompound);
        } else {
            this.compound =  newCompound();
        }
    }
























}
