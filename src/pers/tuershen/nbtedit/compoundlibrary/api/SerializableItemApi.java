package pers.tuershen.nbtedit.compoundlibrary.api;

import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagBase;
import org.bukkit.inventory.ItemStack;

public interface SerializableItemApi  {

    /**
     * 反序列
     * @param paramString 源数据
     * @return bukkit -> ItemStack
     */
    ItemStack deserialize(String paramString);

    /**
     * 序列化
     * @param paramItemStack bukkit -> ItemStack
     * @return String二进制源数据
     */
    String serialize(ItemStack paramItemStack);

    /**
     * 序列化TagBase
     * @param t
     * @param <T> TagBase子类
     * @return String二进制源数据
     */
    <T extends TagBase> String getTagBaseByteStream(T t);

    /**
     * 反序列化
     * @param data 源数据
     * @param <T> TagBase子类
     * @return TagBase
     */
    <T extends TagBase> T deserializeTagBase(String data);



}
