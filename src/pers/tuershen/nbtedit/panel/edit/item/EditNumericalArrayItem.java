package pers.tuershen.nbtedit.panel.edit.item;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagBase;
import pers.tuershen.nbtedit.function.AbstractEditManager;
import pers.tuershen.nbtedit.panel.AbstractEdit;
import pers.tuershen.nbtedit.panel.edit.AbstractPanelEditItem;

import java.util.UUID;

public class EditNumericalArrayItem extends AbstractPanelEditItem {
    /**
     * @param uuid
     * @param itemStack
     */
    public EditNumericalArrayItem(UUID uuid, ItemStack itemStack) {
        super(uuid, itemStack);
    }

    @Override
    public ItemStack remove(Object var, ItemStack itemStack, int slot) {
        return null;
    }

    @Override
    public ItemStack updateNode(Object key, int slot, TagBase tagBase, AbstractPanelEditItem panelEditItem) {
        return null;
    }

    @Override
    public Object getKey() {
        return null;
    }

    @Override
    public int getSlot() {
        return 0;
    }

    @Override
    public ItemStack addSlotPositionNBTTag(String msg, int slot, ItemStack itemStack, Player player) {
        return null;
    }

    @Override
    public ItemStack setSlotPositionNBTTag(String msg, Object key, ItemStack itemStack, Player player, byte type) {
        return null;
    }

    @Override
    public ItemStack addFunctionNBTTag(String msg, Player player, ItemStack itemStack, AbstractEditManager editManager) {
        return null;
    }

    @Override
    public void triggerAddEvent(int slot, Player player) {

    }

    @Override
    public void triggerSetEvent(Object key, int slot, TagBase tagBase, Player player) {

    }

    @Override
    public void triggerAddFunctionNBT(AbstractEditManager editManager, Player player) {

    }

    @Override
    public Inventory newOpenPanel() {
        return null;
    }

    @Override
    public AbstractEdit getEdit() {
        return null;
    }

    @Override
    public TagBase getTagBase() {
        return null;
    }

    @Override
    public Inventory getInventory() {
        return null;
    }
}
