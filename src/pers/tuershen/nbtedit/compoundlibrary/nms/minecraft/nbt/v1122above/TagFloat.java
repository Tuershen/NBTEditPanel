package pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.v1122above;

import java.io.Serializable;

/**
 * @auther Tuershen Create Date on 2020/12/23
 */
public class TagFloat extends TagNumber implements Serializable {

    private static final long serialVersionUID = 8001487667686024980L;

    private float data;

    public TagFloat(float data){
        this.data = data;
    }

    @Override
    public String toString() {
        return "TagFloat{" +
                "data=" + data +
                '}';
    }
}
