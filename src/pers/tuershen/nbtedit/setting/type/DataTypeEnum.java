package pers.tuershen.nbtedit.setting.type;


public enum  DataTypeEnum implements PanelType {

    ARRAY(true){@Override public String msg(String key) { return "§a节点索引: §e"+ key; }},

    COMPOUND(false){@Override public String msg(String key) {
            return "§a节点名称: §e"+ key;
        }};

    private boolean flag;

    DataTypeEnum(boolean b) {
        this.flag = b;
    }

    public static DataTypeEnum getDataType(boolean flag){
        for (DataTypeEnum buttonEnum : DataTypeEnum.values()){
            if (buttonEnum.flag == flag)return buttonEnum;
        }
        return null;
    }


}


interface PanelType {

    String msg(String key);


}
