package pers.tuershen.nbtedit.compoundlibrary.nms.imp.difference;

import pers.tuershen.nbtedit.compoundlibrary.api.SerializableItemApi;

/**
 * @auther Tuershen update Date on 2020/12/3
 */
public class NBTImp_v1_8_R1_R2_R3 extends NBTImp_v1_7_R1_R2_R3 {


    private static NBTImp_v1_8_R1_R2_R3 nbtImp_1_8_r1;

    public NBTImp_v1_8_R1_R2_R3(){}

    public NBTImp_v1_8_R1_R2_R3(Object nbtTagCompound){
        super(nbtTagCompound);
        if (nbtImp_1_8_r1 == null){
            nbtImp_1_8_r1 = this;
        }
    }

    public static NBTImp_v1_8_R1_R2_R3 getInstance(Object nbtTagCompound){
        return new NBTImp_v1_8_R1_R2_R3(nbtTagCompound);
    }

    public static SerializableItemApi serializableItemApi(){
        if (nbtImp_1_8_r1 == null){
            nbtImp_1_8_r1 = new NBTImp_v1_8_R1_R2_R3();
        }
        return nbtImp_1_8_r1;
    }



}
