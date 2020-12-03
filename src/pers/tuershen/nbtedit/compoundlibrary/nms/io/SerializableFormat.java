package pers.tuershen.nbtedit.compoundlibrary.nms.io;

import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagCompound;

import java.io.Serializable;

public class SerializableFormat implements Serializable {

    private static final long serialVersionUID = 6932895816314682365L;

    private String id;

    private TagCompound tagCompound;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TagCompound getTagCompound() {
        return tagCompound;
    }

    public void setTagCompound(TagCompound tagCompound) {
        this.tagCompound = tagCompound;
    }

    public SerializableFormat(){

    }

    public SerializableFormat(String id, TagCompound tagCompound){
        this.id = id;
        this.tagCompound = tagCompound;
    }

    @Override
    public String toString() {
        return "SerializableFormat{" +
                "id='" + id + '\'' +
                ", tagCompound=" + tagCompound +
                '}';
    }
}
