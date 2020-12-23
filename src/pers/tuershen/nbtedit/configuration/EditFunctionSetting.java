package pers.tuershen.nbtedit.configuration;

import org.bukkit.configuration.file.FileConfiguration;
import pers.tuershen.nbtedit.NBTEditPanel;
import pers.tuershen.nbtedit.function.EditFunctionManager;

import java.util.HashMap;
import java.util.Map;

public class EditFunctionSetting implements FunctionSetting {

    private Map<String, Integer> entityMap = new HashMap<>();

    public void init(){
        NBTEditPanel.plugin.saveDefaultConfig();
        FileConfiguration config = NBTEditPanel.plugin.getConfig();
        for (String key : config.getKeys(false)) {
            entityMap.put(key, config.getInt(key));
        }
    }


    public void reload(){
        NBTEditPanel.plugin.reloadConfig();
        entityMap = new HashMap<>();
        EditFunctionManager.reloadPlayerEditManagerMap();
        init();
    }

    @Override
    public double getHealth(String name) {
        if (this.entityMap.containsKey(name)) return this.entityMap.get(name);
        return -1;
    }






}
