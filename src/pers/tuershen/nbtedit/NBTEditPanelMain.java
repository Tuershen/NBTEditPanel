package pers.tuershen.nbtedit;

import pers.tuershen.nbtedit.bstats.Metrics;
import pers.tuershen.nbtedit.compoundlibrary.CompoundLibraryManager;
import pers.tuershen.nbtedit.compoundlibrary.api.CompoundLibraryApi;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pers.tuershen.nbtedit.command.BaseCommand;
import pers.tuershen.nbtedit.function.edit.item.attribute.*;
import pers.tuershen.nbtedit.function.edit.item.mate.*;
import pers.tuershen.nbtedit.function.edit.tile.mobspawner.*;
import pers.tuershen.nbtedit.function.edit.tile.mobspawner.entity.*;
import pers.tuershen.nbtedit.listener.*;
import pers.tuershen.nbtedit.panel.AbstractEdit;
import pers.tuershen.nbtedit.function.EditFunctionManager;
import pers.tuershen.nbtedit.function.edit.EditTagRemove;
import pers.tuershen.nbtedit.function.edit.entity.EditEntityAttackDamage;
import pers.tuershen.nbtedit.function.edit.entity.EditEntityMaxHealth;
import pers.tuershen.nbtedit.function.edit.entity.EditEntityMoveSpeed;
import pers.tuershen.nbtedit.function.edit.item.slashblade.*;
import pers.tuershen.nbtedit.event.listener.PushBukkitChatMessages;

public class NBTEditPanelMain extends JavaPlugin {

    public static NBTEditPanelMain plugin;

    @Override
    public void onEnable() {




        //插件实例
        plugin = this;
        //插件统计
        new Metrics(this,8933);
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
        getLogger().info("NBT编辑器已加载!");
        getLogger().info("作者:兔儿神");
        getLogger().info("插件已开源，发现bug可以加群反馈，感激不尽");
        getLogger().info("插件交流群:978420514");
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
