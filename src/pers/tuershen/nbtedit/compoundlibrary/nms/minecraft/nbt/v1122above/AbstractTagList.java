package pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.v1122above;

import net.minecraft.server.v1_12_R1.NBTBase;

import java.util.AbstractList;


/**
 * @auther Tuershen Create Date on 2020/12/23
 */
public abstract class AbstractTagList<Tag extends TagBase> extends AbstractList<Tag> implements TagBase {

    public abstract Tag set(int paramInt, Tag paramT);

    public abstract void add(int paramInt, Tag paramT);

    public abstract Tag remove(int paramInt);

    public abstract boolean a(int paramInt, NBTBase paramNBTBase);

    public abstract boolean b(int paramInt, NBTBase paramNBTBase);


}
