package pers.tuershen.nbtedit.compoundlibrary.nms;

import pers.tuershen.nbtedit.compoundlibrary.api.*;
import pers.tuershen.nbtedit.compoundlibrary.nms.imp.AbstractNBTTagCompound;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.block.AbstractMinecraftEntityTile;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.entity.AbstractMinecraftEntity;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;

public class PluginManager implements CompoundLibraryApi {

    @Override public ItemStack setCompound(ItemStack itemStack, NBTTagCompoundApi compoundTagApi) {
        MinecraftItemStack minecraftItemStack = CraftItemStack.asNMSCopy(itemStack);
        minecraftItemStack.setMinecraftItemStackTag(compoundTagApi.getNMSCompound());
        return CraftItemStack.asBukkitCopy(minecraftItemStack);
    }

    @Override public NBTTagCompoundApi getCompound(ItemStack itemStack) { return CraftItemStack.asNMSCopy(itemStack).getMinecraftItemStackTag(); }

    @Override public SerializableItemApi getSerializeItem() {
        return AbstractNBTTagCompound.getSerializableItemApi();
    }

    @Override public EntityNBTTagCompoundApi getEntityCompoundApi(LivingEntity livingEntity) { return AbstractMinecraftEntity.getInstance(livingEntity); }

    @Override public TileEntityCompoundApi getTileEntityCompoundApi(Block block) { return AbstractMinecraftEntityTile.getInstance(block); }

    @Override public Object getMinecraftItem(ItemStack itemStack) { return CraftItemStack.asNMSCopy(itemStack).getMinecraftItemStack(); }


}
