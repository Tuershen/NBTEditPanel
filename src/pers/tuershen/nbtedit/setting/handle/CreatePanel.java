package pers.tuershen.nbtedit.setting.handle;

import pers.tuershen.nbtedit.compoundlibrary.api.EntityNBTTagCompoundApi;
import pers.tuershen.nbtedit.compoundlibrary.api.TileEntityCompoundApi;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.TagBase;
import org.bukkit.inventory.ItemStack;
import pers.tuershen.nbtedit.panel.AbstractEdit;

import java.util.UUID;

public class CreatePanel {

    private TagBase tagBase;

    private AbstractEdit edit;

    private UUID uuid;

    private String key;

    private ItemStack itemStack;

    private int slot;

    private EntityNBTTagCompoundApi entityNBTTagCompoundApi;

    private TileEntityCompoundApi tileEntityCompoundApi;


    public CreatePanel(TagBase tagBase, AbstractEdit edit, UUID uuid, String key, ItemStack itemStack, int slot) {
        this.tagBase = tagBase;
        this.edit = edit;
        this.uuid = uuid;
        this.key = key;
        this.itemStack = itemStack;
        this.slot = slot;
    }

    public CreatePanel(TagBase tagBase, AbstractEdit edit, UUID uuid, String key, int slot, EntityNBTTagCompoundApi entityNBTTagCompoundApi) {
        this.tagBase = tagBase;
        this.edit = edit;
        this.uuid = uuid;
        this.key = key;
        this.slot = slot;
        this.entityNBTTagCompoundApi = entityNBTTagCompoundApi;
    }

    public CreatePanel(TagBase tagBase, AbstractEdit edit, UUID uuid, String key, int slot, TileEntityCompoundApi entityCompoundApi) {
        this.tagBase = tagBase;
        this.edit = edit;
        this.uuid = uuid;
        this.key = key;
        this.slot = slot;
        this.tileEntityCompoundApi = entityCompoundApi;
    }


    public TagBase getTagBase() {
        return tagBase;
    }

    public AbstractEdit getEdit() {
        return edit;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getKey() {
        return key;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public int getSlot() {
        return slot;
    }

    public EntityNBTTagCompoundApi getEntityNBTTagCompoundApi() {
        return entityNBTTagCompoundApi;
    }

    public TileEntityCompoundApi getTileEntityCompoundApi() { return tileEntityCompoundApi; }
}
