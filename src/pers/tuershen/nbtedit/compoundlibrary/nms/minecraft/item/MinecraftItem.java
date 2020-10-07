package pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.item;

import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.item.area.v1_12_R1_Item;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.item.area.v1_7_R1_3_Item;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.item.area.v1_7_R4_Item;

public abstract class MinecraftItem {

    protected static Class<?> itemStackClass;

    protected static Class<?> nbtTagCompoundClass;

    public static AreaItemEnum areaItemEnum;

    public static void init(String version){
        areaItemEnum = AreaItemEnum.getInstance(version);
        areaItemEnum.init(version);
    }

    public static MinecraftItem getInstance(){
        return areaItemEnum.getMinecraftItem();
    }

    public abstract Class<?> classTagCompound();

    public abstract Class<?> classItemStack();


}
interface AreaLoadItem{

    void init(String version);

    MinecraftItem getMinecraftItem();

}

enum AreaItemEnum implements AreaLoadItem {

    V1_17_R1("v1_7_R1"){
        @Override
        public void init(String version) {
            v1_7_R1_3_Item.init(version);
        }

        @Override
        public MinecraftItem getMinecraftItem() {
            return new v1_7_R1_3_Item();
        }
    },
    V1_17_R2("v1_7_R2"){
        @Override
        public void init(String version) {
            v1_7_R1_3_Item.init(version);
        }

        @Override
        public MinecraftItem getMinecraftItem() {
            return new v1_7_R1_3_Item();
        }
    },
    V1_17_R3("v1_7_R3"){
        @Override
        public void init(String version) {
            v1_7_R1_3_Item.init(version);
        }

        @Override
        public MinecraftItem getMinecraftItem() {
            return new v1_7_R1_3_Item();
        }
    },
    V1_17_R4("v1_7_R4"){
        @Override
        public void init(String version) {
            v1_7_R4_Item.init(version);
        }

        @Override
        public MinecraftItem getMinecraftItem() {
            return new v1_7_R4_Item();
        }
    },
    V1_12_R1("v1_12_R1") {
        @Override
        public void init(String version) {
            v1_12_R1_Item.init(version);
        }

        @Override
        public MinecraftItem getMinecraftItem() {
            return new v1_12_R1_Item();
        }
    };

    private String version;

    AreaItemEnum(String version) {
        this.version = version;
    }

    public static AreaItemEnum getInstance(String version){
        AreaItemEnum[] areaTileEntityEnums = AreaItemEnum.values();
        for (AreaItemEnum entityEnum : areaTileEntityEnums){
            if (entityEnum.version.contains(version)) return entityEnum;
        }
        return null;
    }

}
