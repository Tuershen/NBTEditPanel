package pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt;

import pers.tuershen.nbtedit.compoundlibrary.CompoundLibraryManager;
import pers.tuershen.nbtedit.compoundlibrary.annotation.TagAnnotation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TagList extends TagBase implements Serializable {

    private static final long serialVersionUID = 2185262442682894234L;

    private List<TagBase> data = new ArrayList<>();

    private byte type = 0;

    public TagList(List<TagBase> data){
        this.data = data;
    }

    public TagList(){ }

    public List<TagBase> getData() {
        return data;
    }

    @TagAnnotation( tagType = "list" )
    public void setData(List<TagBase> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TagList{" +
                "data=" + data +
                ", type=" + type +
                '}';
    }

    public void remove(int i){
        this.data.remove(i);
    }

    @Override
    public Object data() {
        return this.data;
    }

    @Override
    public byte getTypeId() {
        return 9;
    }

    public void add(TagBase tagBase){
        if (tagBase.getTypeId() == 0){
            CompoundLibraryManager.server.getLogger().info("NBTTagList不能集合内不能添加NBTTagList");
            return;
        }
        if (this.type == 0){
            this.type = tagBase.getTypeId();
        }else if (this.type != tagBase.getTypeId()){
            CompoundLibraryManager.server.getLogger().info("当前集合内只能添加类型为"+ TagBase.getNBTTag(this.type)+"的数据");
            return;
        }
        this.data.add(tagBase);
    }

    public byte getType(){
        return this.type;
    }
}
