package pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.v1122above;

import net.minecraft.server.v1_12_R1.NBTBase;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @auther Tuershen Create Date on 2020/12/23
 */
public class TagLongArray extends AbstractTagList<TagLongArray> implements Serializable {


    private static final long serialVersionUID = 8487753678245506623L;

    private long[] data;


    public TagLongArray(long[] data) {
        this.data = data;
    }

    @Override
    public TagLongArray get(int index) {
        return null;
    }

    @Override
    public TagLongArray set(int paramInt, TagLongArray paramT) {
        return null;
    }

    @Override
    public void add(int paramInt, TagLongArray paramT) {

    }

    @Override
    public TagLongArray remove(int paramInt) {
        return null;
    }

    @Override
    public boolean a(int paramInt, NBTBase paramNBTBase) {
        return false;
    }

    @Override
    public boolean b(int paramInt, NBTBase paramNBTBase) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public String toString() {
        return "TagLongArray{" +
                "data=" + Arrays.toString(data) +
                '}';
    }
}
