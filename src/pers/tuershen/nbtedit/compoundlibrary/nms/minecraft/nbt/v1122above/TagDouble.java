package pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.v1122above;

import java.io.Serializable;

/**
 * @auther Tuershen Create Date on 2020/12/23
 */
public class TagDouble extends TagNumber implements Serializable {

    private static final long serialVersionUID = -7867394845434410856L;

    private double data;

    public TagDouble(double data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TagDouble{" +
                "data=" + data +
                '}';
    }
}
