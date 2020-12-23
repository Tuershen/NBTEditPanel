package pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.v1122above;

import java.io.Serializable;

/**
 * @auther Tuershen Create Date on 2020/12/23
 */
public class TagLong extends TagNumber implements Serializable {

    private static final long serialVersionUID = -8762344488431424792L;

    private long data;

    public TagLong(long data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TagLong{" +
                "data=" + data +
                '}';
    }
}
