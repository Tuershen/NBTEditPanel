package pers.tuershen.nbtedit.setting.handle;

import org.bukkit.Material;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.nbtedit.panel.AbstractEdit;
import pers.tuershen.nbtedit.function.EditFunctionManager;
import pers.tuershen.nbtedit.setting.Distribution;

public enum  HandleEventDistributionEnum implements Distribution {

   DISTRIBUTION(){
       @Override
       public HandleEventTypeEnum assignTasks(InventoryClickEvent event) {
           //获取点击位置
           int slot = event.getRawSlot();
           //小于0说明在界面外
           if (slot < 0) return HandleEventTypeEnum.NULL;
           //获取点击位置的物品
           ItemStack slotItemStack = event.getCurrentItem();
           //如果是开启返回null枚举
           if (slotItemStack.getType() == Material.AIR) return HandleEventTypeEnum.NULL;
           //如果不是NBT编辑界面
           if (event.getInventory().getHolder() instanceof AbstractEdit) {
               //如果点击位置小于35说明在NBT显示范围内
               if (slot <= 35) {
                   switch (event.getClick()){
                       //打开a
                       case LEFT:
                           return HandleEventTypeEnum.NEW_PANEL;
                       //如果是shift+右键物品槽位说明需要删除该位置的物品
                       case SHIFT_RIGHT:
                           return  HandleEventTypeEnum.DELETE;
                       //如果是shift+左键修改该位置的物品NBT
                       case MIDDLE:
                           return HandleEventTypeEnum.SET;
                       default:
                           return HandleEventTypeEnum.CANCELLED;
                   }
               } else if (slot >= 35 && slot <= 45 || slot == 53) {
                   //添加NBT
                   return HandleEventTypeEnum.ADD;
               } else {
                   switch (slot) {
                           //返回上一级
                       case AbstractEdit.MIDDLE_SLOT_POS:
                           return HandleEventTypeEnum.MIDDLE_PAGE;
                           //上一页
                       case AbstractEdit.PREVIOUS_SLOT_POS:
                           return HandleEventTypeEnum.PREVIOUS_PAGE;
                           //下一页
                       case AbstractEdit.NEXT_SLOT_POS:
                           return HandleEventTypeEnum.NEXT_PAGE;
                           //功能表
                       case 46:
                           return HandleEventTypeEnum.FUNCTION_TABLE;
                           //连续编辑模式
                       case 47:
                           return HandleEventTypeEnum.CONTINUOUS_MODE;
                           //复制
                       case 51:
                           return HandleEventTypeEnum.NBT_COPY;
                           //粘贴
                       case 52:
                           return HandleEventTypeEnum.NBT_PASTE;
                       default:
                           return HandleEventTypeEnum.NULL;
                   }
               }
           } else if (event.getInventory().getHolder() instanceof EditFunctionManager) {
               //功能表
               return HandleEventTypeEnum.FUNCTION_CALL;
           }
           //不触发任何功能
           return HandleEventTypeEnum.NULL;
       }
   }

}
