package pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.v1122above;


import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * @auther Tuershen Create Date on 2020/12/23
 */
public abstract class TagList extends AbstractTagList implements Serializable {

    private static final long serialVersionUID = -8796785303242229217L;

    private List<TagBase> list = new ArrayList<>();


    @Override
    public String toString() {
        return "TagList{" +
                "list=" + list +
                '}';
    }
}
