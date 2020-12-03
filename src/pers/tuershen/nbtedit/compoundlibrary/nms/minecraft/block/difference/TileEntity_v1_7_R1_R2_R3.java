package pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.block.difference;

import org.bukkit.block.Block;

import java.lang.reflect.Method;

public class TileEntity_v1_7_R1_R2_R3 extends TileEntity_v1_6_R3 {

    protected static Class<?> CraftWorld;

    protected static Class<?> TileEntity;

    protected static Method getTileEntityAt;

    protected static Method save;

    protected static Method load;

    protected Object craftWorld;

    protected Object tileEntity;

    public TileEntity_v1_7_R1_R2_R3(Block block) {
        super(block);
    }

    public static void init(String version){
        try {
            TileEntity = Class.forName("net.minecraft.server." +version+ ".TileEntity");
            CraftWorld = Class.forName("org.bukkit.craftbukkit."+ version +".CraftWorld");
            NBTTagCompound = Class.forName("net.minecraft.server."+ version +".NBTTagCompound");
            getTileEntityAt = CraftWorld.getDeclaredMethod("getTileEntityAt",int.class,int.class,int.class);
            load = TileEntity.getDeclaredMethod("a",NBTTagCompound);
            save = TileEntity.getDeclaredMethod("b",NBTTagCompound);
        } catch (ClassNotFoundException
                | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}
