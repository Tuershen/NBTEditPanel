package pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.entity.area;

import org.bukkit.entity.Entity;
import pers.tuershen.nbtedit.compoundlibrary.api.NBTTagCompoundApi;
import pers.tuershen.nbtedit.compoundlibrary.nms.Compound;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.entity.MinecraftEntity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class v1_7_R1_4_Entity extends MinecraftEntity {

    private static Method save;

    public v1_7_R1_4_Entity(Entity entity) {
        super(entity);
    }

    public static void initClass(){
        try {
            save = EntityClass.getDeclaredMethod("e",NBTTagCompound);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public NBTTagCompoundApi getNBTTagCompound() {
        try {
            this.nbtTagCompound = NBTTagCompound.newInstance();
            this.craftEntity = CraftEntityClass.cast(this.entity);
            this.minecraftEntity = getHandle.invoke(craftEntity);
            save.invoke(minecraftEntity,nbtTagCompound);
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
        try {
            this.nbtTagCompound = tagCompoundApi.getNMSCompound();
            load.invoke(this.minecraftEntity,this.nbtTagCompound);
        } catch (IllegalAccessException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
