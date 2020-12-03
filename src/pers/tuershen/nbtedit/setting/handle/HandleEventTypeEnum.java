package pers.tuershen.nbtedit.setting.handle;

public enum HandleEventTypeEnum {
    NULL,
    DELETE,
    ADD,
    SET,
    PREVIOUS_PAGE,
    MIDDLE_PAGE,
    NEXT_PAGE,
    NEW_PANEL,
    CANCELLED,
    FUNCTION_TABLE,
    FUNCTION_CALL,
    //连续编辑模式
    CONTINUOUS_MODE,
    //NBT复制
    NBT_COPY,
    //NBT粘贴
    NBT_PASTE
}
