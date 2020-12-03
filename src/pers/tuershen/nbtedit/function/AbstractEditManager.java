package pers.tuershen.nbtedit.function;

import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagBase;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.nbtedit.function.annotation.TagParameter;

import java.lang.reflect.Field;

/**
 * 自定义功能
 */
public abstract class AbstractEditManager  {

    protected int index;

    protected String key;

    protected ItemStack slotItem;

    public abstract TagBase inputParams(TagBase tagBase, String... params);

    public abstract int getIndex();

    public abstract String getKey();

    public abstract ItemStack getSlotItem();

    public int getInputParamsNums(){
        int num = 0;
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields){
            if (field.isAnnotationPresent(TagParameter.class)){
                num++;
                continue;
            }
        }
        return num;
    }





}
