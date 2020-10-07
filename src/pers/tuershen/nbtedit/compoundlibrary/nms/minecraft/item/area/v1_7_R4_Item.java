package pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.item.area;

import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.item.MinecraftItem;

public class v1_7_R4_Item extends MinecraftItem {

    public static void init(String version){
        try {
            itemStackClass = Class.forName("net.minecraft.item.ItemStack");
            nbtTagCompoundClass = Class.forName("net.minecraft.nbt.NBTTagCompound");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Class<?> classTagCompound() {
        return nbtTagCompoundClass;
    }

    @Override
    public Class<?> classItemStack() {
        return itemStackClass;
    }
}
