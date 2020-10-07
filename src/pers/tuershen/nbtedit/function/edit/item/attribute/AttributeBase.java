package pers.tuershen.nbtedit.function.edit.item.attribute;


import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.*;
import pers.tuershen.nbtedit.function.AbstractEditManager;
import pers.tuershen.nbtedit.function.handle.NewNBTTag;


import java.util.List;
import java.util.UUID;


public abstract class AttributeBase extends AbstractEditManager {


    @Override
    public TagBase inputParams(TagBase tagBase, String... params) {
        if (!(tagBase instanceof TagCompound)) return tagBase;
        TagCompound tagCompound = (TagCompound) tagBase;
        TagList tagList = (TagList) tagCompound.getMap().get("AttributeModifiers");
        tagList = setAttribute(tagList, NewNBTTag.type((byte)6).newTag(params[0]));
        tagCompound.getMap().put("AttributeModifiers", tagList);
        return tagCompound;
    }

    public TagList setAttribute(TagList tagList, TagBase tagBase){
        if (tagList != null){
            if (tagList.getData().size() > 0) {
                List<TagBase> baseList = tagList.getData();
                for (int i = 0; i < baseList.size(); i++) {
                    TagBase containerTag = baseList.get(i);
                    if (containerTag instanceof TagCompound) {
                        TagCompound attributeCompound = (TagCompound) containerTag;
                        if (attributeCompound.getMap().containsKey("AttributeName")) {
                            TagString attributeName = (TagString) attributeCompound.getMap().get("AttributeName");
                            if (attributeName.getData().equalsIgnoreCase(this.key)) {
                                attributeCompound = setAttributeCompound(attributeCompound,  tagBase);
                                tagList.add(attributeCompound);
                                return tagList;
                            }
                        }
                    }
                }
            }
        }
        tagList = new TagList();
        tagList.add(setAttributeCompound(new TagCompound(),  tagBase));
        return tagList;
    }

    public TagCompound setAttributeCompound(TagCompound tagCompound, TagBase tagBase){
        tagCompound.getMap().put("UUIDMost", new TagLong(UUID.randomUUID().getLeastSignificantBits()));
        tagCompound.getMap().put("UUIDLeast", new TagLong(UUID.randomUUID().getLeastSignificantBits()));
        tagCompound.getMap().put("Amount",  tagBase);
        tagCompound.getMap().put("AttributeName", new TagString(key));
        tagCompound.getMap().put("Operation", new TagInt(0));
        tagCompound.getMap().put("Name", new TagString("NBTEditPanel"));
        return tagCompound;
    }





}
