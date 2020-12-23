package pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.v1122above;

import com.google.common.collect.Maps;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * @auther Tuershen Create Date on 2020/12/23
 */
public class TagCompound implements TagBase, Serializable {

    private static final long serialVersionUID = -6326620888530642417L;

    private final Map<String, TagBase> map = Maps.newHashMap();

    public void put(String key, TagBase tagBase) {
        this.map.put(key, tagBase);
    }

    public Set<String> keySet(){
        return this.map.keySet();
    }

    public Map<String, TagBase> getMap() {
        return this.map;
    }


    @Override
    public String toString() {
        return "TagCompound{" +
                "map=" + map +
                '}';
    }
}
