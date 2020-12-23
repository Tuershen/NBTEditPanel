package pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.v1122above;

import java.io.Serializable;

/**
 * @auther Tuershen Create Date on 2020/12/23
 */
public class TagString implements TagBase, Serializable {

    private static final long serialVersionUID = 5984246305347477431L;

    private String data;

    public TagString(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TagString{" +
                "data='" + data + '\'' +
                '}';
    }
}
