package pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.v1122above;

import java.io.Serializable;

/**
 * @auther Tuershen Create Date on 2020/12/23
 */
public class TagByte extends TagNumber implements Serializable {


    private static final long serialVersionUID = -6598853694014881462L;

    private byte data;

    public TagByte(byte var0) {
        this.data = var0;
    }

    @Override
    public String toString() {
        return this.data + "b";
    }

    @Override
    public TagByte clone() {
        return new TagByte(this.data);
    }

    @Override
    public int hashCode() {
        return this.data;
    }


}
