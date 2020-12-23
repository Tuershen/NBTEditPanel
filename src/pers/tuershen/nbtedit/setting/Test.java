package pers.tuershen.nbtedit.setting;

import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.v1122above.*;

/**
 * @auther Tuershen Create Date on 2020/12/23
 */
public class Test {

    public static void main(String[] args) {
        TagCompound tagCompound = new TagCompound();

        tagCompound.put("1", new TagString("DD"));
        tagCompound.put("2", new TagInt(1));
        tagCompound.put("3", new TagIntArray(new int[]{1,2}));
        tagCompound.put("4", new TagDouble(1.0));
        tagCompound.put("5", new TagFloat(1.0f));
        tagCompound.put("6", new TagLong(1l));
        tagCompound.put("7", new TagLongArray(new long[]{1l,2l}));
        tagCompound.put("6", new TagString("DD"));
        tagCompound.put("6", new TagString("DD"));
        tagCompound.put("6", new TagString("DD"));


        System.out.println(tagCompound.getMap().toString());



    }

}
