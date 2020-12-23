package pers.tuershen.nbtedit.command;

import pers.tuershen.nbtedit.NBTEditPanel;
import pers.tuershen.nbtedit.compoundlibrary.api.CompoundLibraryApi;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.nbtedit.configuration.DynamicLoadingFunction;
import pers.tuershen.nbtedit.configuration.FunctionSetting;
import pers.tuershen.nbtedit.panel.edit.item.EditCompoundItem;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BaseCommand implements CommandExecutor,ListenerValve {

    public CompoundLibraryApi compoundLibraryApi;

    private Map<UUID, Boolean> valveMap = new HashMap<>();

    private Map<UUID, Boolean> valveTileMap = new HashMap<>();

    private FunctionSetting functionSetting;

    protected Listener listener;

    private DynamicLoadingFunction dynamicLoadingFunction;

    public BaseCommand(CompoundLibraryApi compoundLibraryApi, FunctionSetting functionSetting, DynamicLoadingFunction dynamicLoadingFunction){
        this.compoundLibraryApi = compoundLibraryApi;
        this.functionSetting = functionSetting;
        this.dynamicLoadingFunction = dynamicLoadingFunction;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String alias, String[] args) {
        if ("edit".equalsIgnoreCase(command.getName()) || "e".equalsIgnoreCase(alias)) {
            if (commandSender.isOp() || commandSender.hasPermission("NBTEdit")) {
                Player player = (Player) commandSender;
                if (args.length == 1) {
                    if ("help".equalsIgnoreCase(args[0])) {
                        help(player);
                        return true;
                    } else if ("item".equalsIgnoreCase(args[0])) {
                        ItemStack itemStack = player.getItemInHand();

                        if (itemStack.getType() == Material.AIR){
                            player.sendMessage("§7[§bNBTEdit§7] §c请手持物品");
                            return true;
                        }
                        player.openInventory(new EditCompoundItem(player.getUniqueId(), itemStack).newOpenPanel());
                        return true;
                    } else if ("entity".equalsIgnoreCase(args[0])){
                        if (valveMap.containsKey(player.getUniqueId())){
                            boolean value = valveMap.get(player.getUniqueId());
                            if (value){
                                valveMap.put(player.getUniqueId(),false);
                                player.sendMessage("§7[§bNBTEdit§7] §c已关闭生物实体NBT编辑模式");
                                return true;
                            }
                        }
                        valveMap.put(player.getUniqueId(),true);
                        player.sendMessage("§7[§bNBTEdit§7] §a已开启生物实体NBT编辑模式");
                        return true;
                    }else if ("tile".equalsIgnoreCase(args[0])){
                        if (valveTileMap.containsKey(player.getUniqueId())){
                            boolean value = valveTileMap.get(player.getUniqueId());
                            if (value){
                                valveTileMap.put(player.getUniqueId(),false);
                                player.sendMessage("§7[§bNBTEdit§7] §c已关闭方块实体NBT编辑模式");
                                return true;
                            }
                        }
                        valveTileMap.put(player.getUniqueId(),true);
                        player.sendMessage("§7[§bNBTEdit§7] §a已开启方块实体NBT编辑模式");
                        return true;
                    }else if ("reload".equalsIgnoreCase(args[0])){
                        functionSetting.reload();
                        dynamicLoadingFunction.reload();
                        player.sendMessage("§7[§bNBTEdit§7] §a已重载!");
                        return true;
                    }
                }
                help(player);
                return false;
            }
            return true;
        }
        commandSender.sendMessage("§7[§bNBTEdit§7] §c你没有权限!");
        return false;
    }


    @Override
    public boolean value(UUID uuid) {
        if (this.valveMap.containsKey(uuid)){
            return this.valveMap.get(uuid);
        }
        return false;
    }

    @Override
    public boolean valueTile(UUID uuid) {
        if (this.valveTileMap.containsKey(uuid)){
            return this.valveTileMap.get(uuid);
        }
        return false;
    }

    public void help(Player player){
        player.sendMessage("§7[§bNBTEdit§7]§e /edit item 手持物品打开物品NBT编辑界面");
        player.sendMessage("§7[§bNBTEdit§7]§e /edit entity 打开生物实体编辑功能，右键生物实体打开NBT编辑界面");
        player.sendMessage("§7[§bNBTEdit§7]§e /edit tile 打开方块实体编辑功能，右键方块实体打开NBT编辑界面");
        player.sendMessage("§7[§bNBTEdit§7]§e /edit reload 重载配置文件");
    }

}
