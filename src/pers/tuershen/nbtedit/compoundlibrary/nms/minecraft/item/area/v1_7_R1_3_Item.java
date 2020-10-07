package pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.item.area;

import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.item.MinecraftItem;

public class v1_7_R1_3_Item extends MinecraftItem {

    public static void init(String version){
        try {
            nbtTagCompoundClass = Class.forName("net.minecraft.server."+version+".NBTTagCompound");
            itemStackClass = Class.forName("net.minecraft.server."+version+".ItemStack");
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