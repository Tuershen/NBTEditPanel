package pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt;

import pers.tuershen.nbtedit.compoundlibrary.annotation.TagAnnotation;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TagCompound extends TagBase implements Serializable {

    private static final long serialVersionUID = 6962586368452458832L;

    private Map<String, TagBase> map;

    public TagCompound(Map<String, TagBase> map){
        this.map = map;
    }

    public TagCompound(){
        this.map = new HashMap<>();
    }

    public TagCompound (String key, TagBase tagBase) {
        this.map.put(key, tagBase);
    }

    public Map<String, TagBase> getMap() {
        if (this.map == null || this.map.size() == 0){
            this.map = new HashMap<>();
        }
        return map;
    }

    @TagAnnotation( tagType = "Compound" )
    public void setMap(Map<String, TagBase> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return this.map.toString();
    }

    public void remove(Object var){
        this.map.remove(var);
    }

    public boolean hasKey(String key) {
        return this.map.containsKey(key);
    }

    public TagList getList(String key) {
        return (TagList) this.map.get(key);
    }

    public TagCompound getCompound(String key){
        return (TagCompound) this.map.get(key);
    }

    public void put(String key, TagBase tagBase){
        this.map.put(key, tagBase);
    }


    @Override
    public Object data() {
        return this.map;
    }

    @Override
    public byte getTypeId() {
        return 10;
    }


}
