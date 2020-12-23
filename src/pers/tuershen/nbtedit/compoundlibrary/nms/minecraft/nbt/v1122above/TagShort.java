package pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.v1122above;

import java.io.Serializable;

/**
 * @auther Tuershen Create Date on 2020/12/23
 */
public class TagShort extends TagNumber implements Serializable {

    private static final long serialVersionUID = 6246591611927800345L;

    private short data;

    public TagShort(short data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TagShort{" +
                "data=" + data +
                '}';
    }
}
