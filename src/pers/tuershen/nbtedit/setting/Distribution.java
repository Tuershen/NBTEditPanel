package pers.tuershen.nbtedit.setting;

import org.bukkit.event.inventory.InventoryClickEvent;
import pers.tuershen.nbtedit.setting.handle.HandleEventTypeEnum;

public interface Distribution {

    HandleEventTypeEnum assignTasks(InventoryClickEvent event);


}
