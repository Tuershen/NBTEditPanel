package pers.tuershen.nbtedit.setting.handle;

import org.bukkit.entity.Player;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagBase;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagCompound;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagList;
import pers.tuershen.nbtedit.function.AbstractEditManager;
import pers.tuershen.nbtedit.setting.EditPanelManager;

import java.util.Map;

public enum EditPanelManagerEnum implements EditPanelManager {

    COMPOUND(TagTypeEnum.COMPOUND){
        @Override
        public TagBase addTag(String msg, Player player, int slot, TagBase tagCompound) {
            String[] dataArray = msg.split(" ");
            if (dataArray.length <= 1){
                player.sendMessage("§c错误! 在Compound复合数据结构中必须输入§c两个§d参数, 一键对应一值");
                return tagCompound;
            }else {
                ButtonEnum buttonEnum = ButtonEnum.slot(slot);
                TagBase tagBase = buttonEnum.newNBTTag(analysisStringBuff(dataArray), player);
                if (tagBase != null){
                    Map<String, TagBase> map = ((TagCompound)tagCompound).getMap();
                    map.put(dataArray[0],tagBase);
                    ((TagCompound)tagCompound).setMap(map);
                    player.sendMessage("§eⒺ §a添加成功");
                }
            }
            return tagCompound;
        }

        @Override
        public TagBase addFunction(String msg, Player player, TagBase tagBase, AbstractEditManager editManager) {
            String[] params = msg.split(" ");
            if (params.length < editManager.getInputParamsNums()){
                player.sendMessage("参数不够!");
                return tagBase;
            }
            player.sendMessage("§eⒺ §a修改成功");
            return editManager.inputParams((tagBase),  params);
        }

        @Override
        public TagBase setTag(TagBase tag, Player player, Object key, TagBase tagBase) {
            Map<String, TagBase> map = ((TagCompound)tag).getMap();
            map.put(key.toString(), tagBase);
            ((TagCompound)tag).setMap(map);
            player.sendMessage("§eⒺ §a修改成功");
            return tag;
        }
    },
    LIST(TagTypeEnum.LIST){
        @Override
        public TagBase addTag(String msg, Player player, int slot, TagBase tagBase) {
            TagBase tag = ButtonEnum.slot(slot).newNBTTag(msg,player);
            if (tag != null){
                TagList list = (TagList) tagBase;
                list.add(tag);
                player.sendMessage("§eⒺ §a添加成功!");
                return list;
            }
            return tagBase;
        }

        @Override
        public TagBase addFunction(String msg, Player player, TagBase tagBase, AbstractEditManager editManager) {
            String[] params = msg.split(" ");
            if (params.length < editManager.getInputParamsNums()){
                player.sendMessage("参数不够!");
                return tagBase;
            }
            player.sendMessage("§eⒺ §a修改成功");
            return editManager.inputParams(tagBase,  params);
        }

        @Override
        public TagBase setTag(TagBase tag, Player player, Object key, TagBase tagBase) {
            TagList list = (TagList) tag;
            list.getData().set(Integer.parseInt(key.toString()), tagBase);
            player.sendMessage("§eⒺ §a修改成功!");
            return list;
        }
    },
    INT_ARRAY(TagTypeEnum.INT_ARRAY){
        @Override
        public TagBase addTag(String msg, Player player, int slot, TagBase tagBase) {
            return null;
        }

        @Override
        public TagBase addFunction(String msg, Player player, TagBase tagBase, AbstractEditManager editManager) {
            return null;
        }

        @Override
        public TagBase setTag(TagBase tag, Player player, Object key, TagBase tagBase) {
            return null;
        }
    },
    LONG_ARRAY(TagTypeEnum.LONG_ARRAY){
        @Override
        public TagBase addTag(String msg, Player player, int slot, TagBase tagBase) {
            return null;
        }

        @Override
        public TagBase addFunction(String msg, Player player, TagBase tagBase, AbstractEditManager editManager) {
            return null;
        }

        @Override
        public TagBase setTag(TagBase tag, Player player, Object key, TagBase tagBase) {
            return null;
        }
    },
    BYTE_ARRAY(TagTypeEnum.BYTE_ARRAY){
        @Override
        public TagBase addTag(String msg, Player player, int slot, TagBase tagBase) {
            return null;
        }

        @Override
        public TagBase addFunction(String msg, Player player, TagBase tagBase, AbstractEditManager editManager) {
            return null;
        }

        @Override
        public TagBase setTag(TagBase tag, Player player, Object key, TagBase tagBase) {
            return null;
        }
    };

    private TagTypeEnum typeEnum;

    EditPanelManagerEnum(TagTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    public static String analysisStringBuff(String[] arrays){
        StringBuilder buffer = new StringBuilder();
        for (int i = 1; i < arrays.length ; i++) {
            buffer.append(arrays[i]);
        }
        return buffer.toString();
    }


    public static EditPanelManagerEnum getInstance(TagTypeEnum typeEnum){
        EditPanelManagerEnum[] typeOfEnums = EditPanelManagerEnum.values();
        for (EditPanelManagerEnum managerEnum : typeOfEnums){
            if (managerEnum.typeEnum == typeEnum) return managerEnum;
        }
        return null;

    }


}
