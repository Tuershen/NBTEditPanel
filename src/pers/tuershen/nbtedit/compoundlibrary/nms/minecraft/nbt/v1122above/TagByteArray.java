package pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.v1122above;



import net.minecraft.server.v1_12_R1.NBTBase;

import java.io.Serializable;
import java.util.List;

/**
 * @auther Tuershen Create Date on 2020/12/23
 */
public class TagByteArray extends AbstractTagList<TagByteArray> implements Serializable {

    private static final long serialVersionUID = -6528891906061723470L;

    private byte[] data;

    public TagByteArray(byte[] var0) { this.data = var0; }

    public TagByteArray(List<Byte> var0) { this(a(var0)); }


    private static byte[] a(List<Byte> var0) {
        byte[] var1 = new byte[var0.size()];
        for (int var2 = 0; var2 < var0.size(); var2++) {
            Byte var3 = var0.get(var2);
            var1[var2] = (var3 == null) ? 0 : var3.byteValue();
        }

        return var1;
    }

    public byte getTypeId() { return 7; }


    @Override
    public int size() {
        return 0;
    }

    public String toString() {
        StringBuilder var0 = new StringBuilder("[B;");
        for (int var1 = 0; var1 < this.data.length; var1++) {
            if (var1 != 0) {
                var0.append(',');
            }
            var0.append(this.data[var1]).append('B');
        }
        return var0.append(']').toString();
    }

    @Override
    public TagByteArray get(int index) {
        return null;
    }

    @Override
    public TagByteArray set(int paramInt, TagByteArray paramT) {
        return null;
    }

    @Override
    public void add(int paramInt, TagByteArray paramT) {

    }

    @Override
    public TagByteArray remove(int paramInt) {
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
}
