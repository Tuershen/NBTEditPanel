package pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.block;

import pers.tuershen.nbtedit.compoundlibrary.api.NBTTagCompoundApi;
import pers.tuershen.nbtedit.compoundlibrary.api.TileEntityCompoundApi;
import org.bukkit.block.Block;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.block.area.v1_7_R1_4_TileEntity;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.block.area.v1_12_R1_TileEntity;



public abstract class MinecraftEntityTile implements TileEntityCompoundApi {

    private static AreaTileEntityEnum areaTileEntityEnum;

    protected static Class<?> NBTTagCompound;

    protected Block block;

    protected Object nbtTagCompound;

    public MinecraftEntityTile(Block block){
        this.block = block;
    }

    public static void init(String version){
        try {
            areaTileEntityEnum = AreaTileEntityEnum.getInstance(version);
            NBTTagCompound = Class.forName("net.minecraft.server."+ version +".NBTTagCompound");
            areaTileEntityEnum.initClass(version);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static MinecraftEntityTile getInstance(Block block){
        return areaTileEntityEnum.createTileEntity(block);
    }

    public abstract NBTTagCompoundApi getNBTTagCompound();

    public abstract void saveNBTTag(NBTTagCompoundApi tagCompoundApi);


}


enum AreaTileEntityEnum implements AreaLoadTileEntity{


    V1_17_R1("v1_7_R1"){
        @Override
        public void initClass(String version) {
            v1_7_R1_4_TileEntity.init(version);
        }

        @Override
        public MinecraftEntityTile createTileEntity(Block block) {
            return new v1_7_R1_4_TileEntity(block);
        }
    },
    V1_17_R2("v1_7_R2"){
        @Override
        public void initClass(String version) {
            v1_7_R1_4_TileEntity.init(version);
        }

        @Override
        public MinecraftEntityTile createTileEntity(Block block) {
            return new v1_7_R1_4_TileEntity(block);
        }
    },
    V1_17_R3("v1_7_R3"){
        @Override
        public void initClass(String version) {
            v1_7_R1_4_TileEntity.init(version);
        }

        @Override
        public MinecraftEntityTile createTileEntity(Block block) {
            return new v1_7_R1_4_TileEntity(block);
        }
    },
    V1_17_R4("v1_7_R4"){
        @Override
        public void initClass(String version) {
            v1_7_R1_4_TileEntity.init(version);
        }

        @Override
        public MinecraftEntityTile createTileEntity(Block block) {
            return new v1_7_R1_4_TileEntity(block);
        }
    },
    V1_12_R1("v1_12_R1") {
        @Override
        public void initClass(String version) {
            v1_12_R1_TileEntity.init(version);
        }

        @Override
        public MinecraftEntityTile createTileEntity(Block block) {
            return new v1_12_R1_TileEntity(block);
        }
    };

    private String version;

    AreaTileEntityEnum(String version) {
        this.version = version;
    }

    public static AreaTileEntityEnum getInstance(String version){
        AreaTileEntityEnum[] areaTileEntityEnums = AreaTileEntityEnum.values();
        for (AreaTileEntityEnum tileEntityEnum : areaTileEntityEnums){
            if (tileEntityEnum.version.contains(version)) return tileEntityEnum;
        }
        return null;
    }


}

interface AreaLoadTileEntity{

    void initClass(String version);

    MinecraftEntityTile createTileEntity(Block block);

}

