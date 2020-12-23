package pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.v1122above;

import net.minecraft.server.v1_12_R1.NBTBase;

import java.io.Serializable;

/**
 * @auther Tuershen Create Date on 2020/12/23
 */
public class TagIntArray extends AbstractTagList<TagIntArray> implements Serializable {


    private static final long serialVersionUID = 8975729706067381155L;

    private int[] data;

    public TagIntArray(int[] data){
        this.data = data;
    }

    @Override
    public TagIntArray get(int index) {
        return null;
    }

    @Override
    public TagIntArray set(int paramInt, TagIntArray paramT) {
        return null;
    }

    @Override
    public void add(int paramInt, TagIntArray paramT) {

    }

    @Override
    public TagIntArray remove(int paramInt) {
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
        StringBuilder builder = new StringBuilder("[I;");
        for (int i = 0; i < this.data.length; i++) {
            if (i != 0) {
                builder.append(',');
            }
            builder.append(this.data[i]);
        }
        return builder.append(']').toString();
    }
}
