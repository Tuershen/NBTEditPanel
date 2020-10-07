package pers.tuershen.nbtedit.setting.handle;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToolsFunction {

    //NBT类型
    protected static Map<Integer, String> typeMap = new HashMap<>();

    static {
        typeMap.put(36, "Byte");
        typeMap.put(37, "ByteArray");
        typeMap.put(38, "String");
        typeMap.put(39, "Double");
        typeMap.put(40, "Float");
        typeMap.put(41, "Int");
        typeMap.put(42, "IntArray");
        typeMap.put(43, "Long");
        typeMap.put(44, "Short");
        typeMap.put(45, "List");
        typeMap.put(53, "Compound");
    }



    public static ItemStack functionItem(){
        ItemStack itemStack = new ItemStack(Material.ENDER_PEARL);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§a快§b捷§d功§c能§e表");
        List<String> list = new ArrayList<>();
        list.add("§a⓪ §7shift+右键删除被点击的NBT节点");
        list.add("§a⓪ §7鼠标中间修改被点击的NBT节点");
        itemMeta.setLore(list);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    public static ItemStack previousButton() {
        ItemStack itemStack = new ItemStack(Material.ANVIL);
        itemStack.setItemMeta(setItemMeta(itemStack.getItemMeta(), "§b§l上一页"));
        return itemStack;
    }

    public static ItemStack middleButton() {
        ItemStack itemStack = new ItemStack(Material.ANVIL);
        itemStack.setItemMeta(setItemMeta(itemStack.getItemMeta(), "§c§l返回上一级"));
        return itemStack;
    }

    public static ItemStack nextButton() {
        ItemStack itemStack = new ItemStack(Material.ANVIL);
        itemStack.setItemMeta(setItemMeta(itemStack.getItemMeta(), "§b§l下一页"));
        return itemStack;
    }

    public static void nbtBaseTypeButton(Inventory inventory) {
        ItemStack itemStack = new ItemStack(Material.PAINTING);
        ItemMeta itemMeta = itemStack.getItemMeta();
        for (Integer pos : typeMap.keySet()) {
            itemMeta.setDisplayName("§3节点类型: §d" + typeMap.get(pos));
            List<String> lore = new ArrayList<>();
            lore.add(0, "§3鼠标左键添加一个: §d" + typeMap.get(pos));
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
            inventory.setItem(pos, itemStack);
        }
    }


    protected static ItemMeta setItemMeta(ItemMeta itemMeta, String data) {
        itemMeta.setDisplayName(data);
        return itemMeta;
    }


}
