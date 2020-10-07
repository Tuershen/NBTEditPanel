package pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.block.area;

import org.bukkit.block.Block;
import pers.tuershen.nbtedit.compoundlibrary.api.NBTTagCompoundApi;
import pers.tuershen.nbtedit.compoundlibrary.nms.Compound;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.block.MinecraftEntityTile;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagCompound;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class v1_12_R1_TileEntity extends MinecraftEntityTile{

    private static Class<?> CraftBlockEntityStateClass;

    private static Class<?> TileEntityClass;

    private static Method getTileEntity;

    private static Method save;

    private static Method load;

    private Object craftBlockEntityTile;

    private Object tileEntity;


    public v1_12_R1_TileEntity(Block block) {
        super(block);
    }

    public static void init(String version){
        try {
            CraftBlockEntityStateClass = Class.forName("org.bukkit.craftbukkit."+version+".block.CraftBlockEntityState");
            TileEntityClass = Class.forName("net.minecraft.server."+version+".TileEntity");
            getTileEntity = CraftBlockEntityStateClass.getDeclaredMethod("getTileEntity");
            save = TileEntityClass.getDeclaredMethod("save",NBTTagCompound);
            load = TileEntityClass.getDeclaredMethod("load",NBTTagCompound);
        } catch (ClassNotFoundException
                | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public NBTTagCompoundApi getNBTTagCompound() {
        try {
            this.nbtTagCompound = NBTTagCompound.newInstance();
            Constructor constructor = CraftBlockEntityStateClass.getConstructor(Block.class,Class.class);
            this.craftBlockEntityTile = constructor.newInstance(this.block,TileEntityClass);
            getTileEntity.setAccessible(true);
            this.tileEntity = getTileEntity.invoke(this.craftBlockEntityTile);
            if (tileEntity == null) return new Compound(this.nbtTagCompound);
            this.nbtTagCompound = save.invoke(this.tileEntity,this.nbtTagCompound);
            return new Compound(this.nbtTagCompound);
        } catch (InstantiationException
                | IllegalAccessException
                | NoSuchMethodException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
        return new Compound(this.nbtTagCompound);
    }

    @Override
    public void saveNBTTag(NBTTagCompoundApi tagCompoundApi) {
        try {
            TagCompound nbtTagCompoundApi = tagCompoundApi.getNBTTagCompoundApi();
            if (nbtTagCompoundApi.getMap().size() <= 0) return;
            this.nbtTagCompound = tagCompoundApi.getNMSCompound();
            if (this.nbtTagCompound == null || this.tileEntity == null) return;
            load.invoke(this.tileEntity,this.nbtTagCompound);
        } catch (IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
