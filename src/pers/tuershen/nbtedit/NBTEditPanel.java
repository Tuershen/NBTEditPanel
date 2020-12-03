package pers.tuershen.nbtedit;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pers.tuershen.nbtedit.bstats.Metrics;
import pers.tuershen.nbtedit.command.BaseCommand;
import pers.tuershen.nbtedit.compoundlibrary.CompoundLibraryManager;
import pers.tuershen.nbtedit.compoundlibrary.api.CompoundLibraryApi;
import pers.tuershen.nbtedit.event.listener.PushBukkitChatMessages;
import pers.tuershen.nbtedit.function.EditFunctionManager;
import pers.tuershen.nbtedit.function.edit.EditTagRemove;
import pers.tuershen.nbtedit.function.edit.entity.EditEntityAttackDamage;
import pers.tuershen.nbtedit.function.edit.entity.EditEntityMaxHealth;
import pers.tuershen.nbtedit.function.edit.entity.EditEntityMoveSpeed;
import pers.tuershen.nbtedit.function.edit.item.attribute.*;
import pers.tuershen.nbtedit.function.edit.item.mate.*;
import pers.tuershen.nbtedit.function.edit.item.slashblade.*;
import pers.tuershen.nbtedit.function.edit.tile.mobspawner.*;
import pers.tuershen.nbtedit.function.edit.tile.mobspawner.entity.*;
import pers.tuershen.nbtedit.listener.PlayerChatEvent;
import pers.tuershen.nbtedit.listener.PlayerClickBlock;
import pers.tuershen.nbtedit.listener.PlayerClickEntity;
import pers.tuershen.nbtedit.listener.PlayerEditPanelEvent;
import pers.tuershen.nbtedit.panel.AbstractEdit;

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
        //编辑器初始化
        AbstractEdit.init(compoundLibraryApi);
        //指令
        BaseCommand baseCommand = new BaseCommand(compoundLibraryApi);
        //指令注册
        getCommand("edit").setExecutor(baseCommand);
        //事件注册
        Bukkit.getPluginManager().registerEvents(new PlayerEditPanelEvent(),this);
        Bukkit.getPluginManager().registerEvents(new PlayerChatEvent(PushBukkitChatMessages.registerReceiveObject()),this);
        Bukkit.getPluginManager().registerEvents(new PlayerClickEntity(compoundLibraryApi,baseCommand),this);
        Bukkit.getPluginManager().registerEvents(new PlayerClickBlock(compoundLibraryApi,baseCommand),this);
        //初始化快捷功能
        intiEditFunction();
        getLogger().info(     "§b  _   _ ____ _______ ______    _ _ _   _____                 _ " );
        getLogger().info(    "§b | \\ | |  _ \\__   __|  ____|  | (_) | |  __ \\               | |" );
        getLogger().info(   "§b |  \\| | |_) | | |  | |__   __| |_| |_| |__) |_ _ _ __   ___| |" );
        getLogger().info(    "§b | . ` |  _ <  | |  |  __| / _` | | __|  ___/ _` | '_ \\ / _ \\ |" );
        getLogger().info(      "§b | |\\  | |_) | | |  | |___| (_| | | |_| |  | (_| | | | |  __/ |" );
        getLogger().info(     "§b |_| \\_|____/  |_|  |______\\__,_|_|\\__|_|   \\__,_|_| |_|\\___|_|");
        getLogger().info("§eNBT编辑器加载成功!");
        getLogger().info("§e作者:§d兔儿神");
        getLogger().info("§e插件已开源，发现bug可以加群反馈，感激不尽");
        getLogger().info("§e插件交流群:§d978420514");
        getLogger().info("§e最新版本: §cv1.35");
        //插件统计
        new Metrics(this,8933);

    }


    /**
     * 注册快捷功能
     */
    public void intiEditFunction(){
        EditFunctionManager.registerEditFunction(new EditEnchanting());
        EditFunctionManager.registerEditFunction(new EditItemUnbreakable());
        EditFunctionManager.registerEditFunction(new EditItemDisplay());
        EditFunctionManager.registerEditFunction(new EditItemLore());
        EditFunctionManager.registerEditFunction(new EditInsertLore());
        EditFunctionManager.registerEditFunction(new EditModifyLore());
        EditFunctionManager.registerEditFunction(new EditRemoveIndexLore());
        EditFunctionManager.registerEditFunction(new EditRemoveLore());
        EditFunctionManager.registerEditFunction(new EditRemoveLoreKeyword());
        EditFunctionManager.registerEditFunction(new EditLoreKeywordSubstitution());
        EditFunctionManager.registerEditFunction(new EditBaseAttackModifier());
        EditFunctionManager.registerEditFunction(new EditKillCount());
        EditFunctionManager.registerEditFunction(new EditModelName());
        EditFunctionManager.registerEditFunction(new EditProudSoul());
        EditFunctionManager.registerEditFunction(new EditRepairCounter());
        EditFunctionManager.registerEditFunction(new EditSA());
        EditFunctionManager.registerEditFunction(new EditTextureName());
        EditFunctionManager.registerEditFunction(new EditSummonedSwordColor());
        EditFunctionManager.registerEditFunction(new EditDeleteAllEnch());
        EditFunctionManager.registerEditFunction(new EditJumpStrength());
        EditFunctionManager.registerEditFunction(new EditEntityMaxHealth());
        EditFunctionManager.registerEditFunction(new EditEntityMoveSpeed());
        EditFunctionManager.registerEditFunction(new EditEntityAttackDamage());
        EditFunctionManager.registerEditFunction(new EditTagRemove());
        EditFunctionManager.registerEditFunction(new EditRepairCount());
        EditFunctionManager.registerEditFunction(new EditAttackDamage());
        EditFunctionManager.registerEditFunction(new EditFollowRange());
        EditFunctionManager.registerEditFunction(new EditKnockBackResistance());
        EditFunctionManager.registerEditFunction(new EditMaxHealth());
        EditFunctionManager.registerEditFunction(new EditMovementSpeed());
        EditFunctionManager.registerEditFunction(new EditRemove());
        EditFunctionManager.registerEditFunction(new EditSpawnReinforcements());
        EditFunctionManager.registerEditFunction(new EditMaxNearbyEntities());
        EditFunctionManager.registerEditFunction(new EditRequiredPlayerRange());
        EditFunctionManager.registerEditFunction(new EditSpawnCount());
        EditFunctionManager.registerEditFunction(new EditSpawnDelay());
        EditFunctionManager.registerEditFunction(new EditSpawnRange());
        EditFunctionManager.registerEditFunction(new EditMobEntityMaxHealth());
        EditFunctionManager.registerEditFunction(new EditSpawnPotentials());
        EditFunctionManager.registerEditFunction(new EditMobArmor());
        EditFunctionManager.registerEditFunction(new EditMobArmorToughness());
        EditFunctionManager.registerEditFunction(new EditMobAttackDamage());
        EditFunctionManager.registerEditFunction(new EditMobFollowRange());
        EditFunctionManager.registerEditFunction(new EditMobKnockBackResistance());
        EditFunctionManager.registerEditFunction(new EditMobMoveSpeed());

    }


}
