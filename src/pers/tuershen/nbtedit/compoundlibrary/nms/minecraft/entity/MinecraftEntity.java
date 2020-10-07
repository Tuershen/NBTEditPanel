package pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.entity;

import pers.tuershen.nbtedit.compoundlibrary.api.EntityNBTTagCompoundApi;
import pers.tuershen.nbtedit.compoundlibrary.api.NBTTagCompoundApi;
import org.bukkit.entity.Entity;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.entity.area.v1_12_R1_Entity;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.entity.area.v1_7_R1_4_Entity;


import java.lang.reflect.Method;

public abstract class MinecraftEntity implements EntityNBTTagCompoundApi {

    //Class           org.bukkit.craftBukkit.xxx.entity.CraftEntity
    protected static Class<?>        CraftEntityClass;

    //Class          org.bukkit.craftBukkit.xxx.entity.CraftEntity.getHandle()
    protected static Method          getHandle;

    //Class          net.minecraft.server.xxx.Entity
    protected static Class<?>        EntityClass;

    //Class          net.minecraft.server.xxx.NBTTagCompound
    protected static Class<?>        NBTTagCompound;

    //加载
    protected static Method          load;

    //
    private static AreaEntityEnum areaEntityEnum;

    //NBTTagCompound net.minecraft.server.xxx.NBTTagCompound
    protected Object                 nbtTagCompound;

    //CraftEntity    org.bukkit.craftBukkit.xxx.entity.CraftEntity
    protected Object                 craftEntity;

    //Entity          net.minecraft.server.xxx.Entity
    protected Object                 minecraftEntity;

    //Entity          org.bukkit.entity.Entity
    protected Entity                 entity;

    public MinecraftEntity(Entity entity){
        this.entity = entity;
    }


    /**
     * 加载类
     * @param version 服务器版本
     */
    public static void init(String version){
        try {
            CraftEntityClass = Class.forName("org.bukkit.craftbukkit."+ version +".entity.CraftEntity");
            EntityClass = Class.forName("net.minecraft.server."+ version +".Entity");
            getHandle = CraftEntityClass.getDeclaredMethod("getHandle");
            NBTTagCompound = Class.forName("net.minecraft.server."+version+".NBTTagCompound");
            load = EntityClass.getDeclaredMethod("f",NBTTagCompound);
            areaEntityEnum = AreaEntityEnum.getInstance(version);
            areaEntityEnum.init();
        } catch (ClassNotFoundException
                | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static MinecraftEntity getInstance(Entity entity){
        return areaEntityEnum.createEntity(entity);
    }

    public abstract NBTTagCompoundApi getNBTTagCompound();

    public abstract void saveNBTTag(NBTTagCompoundApi tagCompoundApi);

}



enum AreaEntityEnum implements AreaLoadEntity {

    V1_17_R1("v1_7_R1"){
        @Override
        public void init() {
            v1_7_R1_4_Entity.initClass();
        }

        @Override
        public MinecraftEntity createEntity(Entity entity) {
            return new v1_7_R1_4_Entity(entity);
        }
    },
    V1_17_R2("v1_7_R2"){
        @Override
        public void init() {
            v1_7_R1_4_Entity.initClass();
        }

        @Override
        public MinecraftEntity createEntity(Entity entity) {
            return new v1_7_R1_4_Entity(entity);
        }
    },
    V1_17_R3("v1_7_R3"){
        @Override
        public void init() {
            v1_7_R1_4_Entity.initClass();
        }

        @Override
        public MinecraftEntity createEntity(Entity entity) {
            return new v1_7_R1_4_Entity(entity);
        }
    },
    V1_17_R4("v1_7_R4"){
        @Override
        public void init() {
            v1_7_R1_4_Entity.initClass();
        }

        @Override
        public MinecraftEntity createEntity(Entity entity) {
            return new v1_7_R1_4_Entity(entity);
        }
    },
    V1_12_R1("v1_12_R1") {
        @Override
        public void init() {
            v1_12_R1_Entity.initClass();
        }

        @Override
        public MinecraftEntity createEntity(Entity entity) {
            return new v1_12_R1_Entity(entity);
        }
    };

    private String version;

    AreaEntityEnum(String version) {
        this.version = version;
    }

    public static AreaEntityEnum getInstance(String version){
        AreaEntityEnum[] areaTileEntityEnums = AreaEntityEnum.values();
        for (AreaEntityEnum entityEnum : areaTileEntityEnums){
            if (entityEnum.version.contains(version)) return entityEnum;
        }
        return null;
    }

}


interface AreaLoadEntity{

    void init();

    MinecraftEntity createEntity(Entity entity);

}


