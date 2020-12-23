package pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.v1122above;

import java.io.Serializable;

/**
 * @auther Tuershen Create Date on 2020/12/23
 */
public class TagInt extends TagNumber implements Serializable {

    private static final long serialVersionUID = -5945355837174018434L;

    private int data;

    public TagInt(int data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "TagInt{" +
                "data=" + data +
                '}';
    }
}
