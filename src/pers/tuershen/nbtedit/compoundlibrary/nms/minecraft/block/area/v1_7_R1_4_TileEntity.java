package pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.block.area;

import org.bukkit.block.Block;
import pers.tuershen.nbtedit.compoundlibrary.api.NBTTagCompoundApi;
import pers.tuershen.nbtedit.compoundlibrary.nms.Compound;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.block.MinecraftEntityTile;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagCompound;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class v1_7_R1_4_TileEntity extends MinecraftEntityTile {

    private static Class<?> CraftWorld;

    private static Class<?> TileEntity;

    private static Method getTileEntityAt;

    private static Method save;

    private static Method load;

    private Object craftWorld;

    private Object tileEntity;

    public v1_7_R1_4_TileEntity(Block block) {
        super(block);
    }

    public static void init(String version){
        try {
            TileEntity = Class.forName("net.minecraft.server." +version+ ".TileEntity");
            CraftWorld = Class.forName("org.bukkit.craftbukkit."+ version +".CraftWorld");
            getTileEntityAt = CraftWorld.getDeclaredMethod("getTileEntityAt",int.class,int.class,int.class);
            load = TileEntity.getDeclaredMethod("a",NBTTagCompound);
            save = TileEntity.getDeclaredMethod("b",NBTTagCompound);
        } catch (ClassNotFoundException
                | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public NBTTagCompoundApi getNBTTagCompound() {

        try {
            this.nbtTagCompound = NBTTagCompound.newInstance();
            craftWorld = CraftWorld.cast(this.block.getWorld());
            tileEntity = getTileEntityAt.invoke(craftWorld,
                    this.block.getX(),
                    this.block.getY(),
                    this.block.getZ());
            if (tileEntity == null) return new Compound(this.nbtTagCompound);
            save.invoke(tileEntity,this.nbtTagCompound);
            return new Compound(this.nbtTagCompound);
        } catch (InstantiationException
                | IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
        return new Compound(this.nbtTagCompound);
    }

    @Override
    public void saveNBTTag(NBTTagCompoundApi tagCompoundApi) {
        TagCompound nbtTagCompoundApi = tagCompoundApi.getNBTTagCompoundApi();
        if (nbtTagCompoundApi.getMap().size() <= 0) return;
        this.nbtTagCompound = tagCompoundApi.getNMSCompound();
        if (this.nbtTagCompound == null) return;
        try {
            load.invoke(this.tileEntity,this.nbtTagCompound);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
