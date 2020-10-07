package pers.tuershen.nbtedit.compoundlibrary.api;

import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagBase;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagCompound;

public interface NBTTagCompoundApi {


    String getString(String key);

    boolean getBoolean(String key);

    byte getByte(String key);

    byte[] getByteArray(String key);

    double getDouble(String key);

    float getFloat(String key);

    int getInt(String key);

    int[] getIntArray(String key);

    <T extends TagBase> NBTTagCompoundApi getCompound(String key);

    <T extends TagBase> T get(String key);

    boolean hasKey(String key);

    void setString(String key, String value);

    void setBoolean(String key, boolean value);

    void setByte(String key, byte value);

    void setByteArray(String key, byte[] value);

    void setDouble(String key, double value);

    void setFloat(String key, float value);

    void setInt(String key, int value);

    void setIntArray(String key, int[] value);

    void setLong(String key, long value);

    void setShort(String key, short value);

    NBTTagCompoundApi newCompoundApi();

    <T extends TagBase> void set(String key, T base);

    Object getNMSCompound();

    void newNBTTagCompound();

    TagCompound getNBTTagCompoundApi();

    void remove(String key);

    void setCompoundMap(TagCompound tagCompound);



}
