package pers.tuershen.nbtedit;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pers.tuershen.nbtedit.bstats.Metrics;
import pers.tuershen.nbtedit.command.BaseCommand;
import pers.tuershen.nbtedit.compoundlibrary.CompoundLibraryManager;
import pers.tuershen.nbtedit.compoundlibrary.api.CompoundLibraryApi;
import pers.tuershen.nbtedit.configuration.DynamicLoadingFunction;
import pers.tuershen.nbtedit.configuration.EditFunctionSetting;
import pers.tuershen.nbtedit.event.listener.PushBukkitChatMessages;
import pers.tuershen.nbtedit.listener.*;
import pers.tuershen.nbtedit.panel.AbstractEdit;

import java.io.File;

/**
 * @auther Tuershen update Date on 2020/11/29
 */
public class NBTEditPanel extends JavaPlugin {

    public static NBTEditPanel plugin;

    @Override
    public void onEnable() {
        //插件实例
        plugin = this;
        //NBT接口
        CompoundLibraryApi compoundLibraryApi = CompoundLibraryManager.getPluginManager(this);
        EditFunctionSetting functionSetting = new EditFunctionSetting();
        //编辑器初始化
        AbstractEdit.init(compoundLibraryApi);
        //初始化快捷功能
        DynamicLoadingFunction function = new DynamicLoadingFunction(getFunctionFile());
        function.loadJarFile();
        //指令
        BaseCommand baseCommand = new BaseCommand(compoundLibraryApi, functionSetting, function);
        //指令注册
        getCommand("edit").setExecutor(baseCommand);
        //事件注册
        Bukkit.getPluginManager().registerEvents(new PlayerEditPanelEvent(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerChatEvent(PushBukkitChatMessages.registerReceiveObject()),this);
        Bukkit.getPluginManager().registerEvents(new PlayerClickEntity(compoundLibraryApi, baseCommand),this);
        Bukkit.getPluginManager().registerEvents(new PlayerClickBlock(compoundLibraryApi, baseCommand),this);
        Bukkit.getConsoleSender().sendMessage( "§b_   _ ____ _______ ______    _ _ _   _____                 _ " );
        Bukkit.getConsoleSender().sendMessage(    "§b | \\ | |  _ \\__   __|  ____|  | (_) | |  __ \\               | |" );
        Bukkit.getConsoleSender().sendMessage(   "§b |  \\| | |_) | | |  | |__   __| |_| |_| |__) |_ _ _ __   ___| |" );
        Bukkit.getConsoleSender().sendMessage(    "§b | . ` |  _ <  | |  |  __| / _` | | __|  ___/ _` | '_ \\ / _ \\ |" );
        Bukkit.getConsoleSender().sendMessage(      "§b | |\\  | |_) | | |  | |___| (_| | | |_| |  | (_| | | | |  __/ |" );
        Bukkit.getConsoleSender().sendMessage(     "§b |_| \\_|____/  |_|  |______\\__,_|_|\\__|_|   \\__,_|_| |_|\\___|_|");
        Bukkit.getConsoleSender().sendMessage("§eNBT编辑器加载成功!");
        Bukkit.getConsoleSender().sendMessage("§e作者:兔儿神");
        Bukkit.getConsoleSender().sendMessage("§e插件已开源，发现bug可以加群反馈，感激不尽");
        Bukkit.getConsoleSender().sendMessage("§e插件交流群:978420514");
        Bukkit.getConsoleSender().sendMessage("§e最新版本: v1.35");
        //插件统计
        new Metrics(this,8933);
    }

    public void loadJarLogger(String jar, int count) {
        Bukkit.getConsoleSender().sendMessage("§b成功载入：§a"+jar+", §b功能数：§e"+count);
    }



    public static File getFunctionFile() {
        return new File(NBTEditPanel.plugin.getDataFolder(),"function");
    }


}
