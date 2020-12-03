package pers.tuershen.nbtedit.compoundlibrary;

import pers.tuershen.nbtedit.compoundlibrary.api.CompoundLibraryApi;
import pers.tuershen.nbtedit.compoundlibrary.nms.CraftItemStack;
import pers.tuershen.nbtedit.compoundlibrary.nms.MinecraftItemStack;
import pers.tuershen.nbtedit.compoundlibrary.nms.PluginManager;
import pers.tuershen.nbtedit.compoundlibrary.nms.imp.AbstractNBTTagCompound;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.block.AbstractMinecraftEntityTile;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.entity.AbstractMinecraftEntity;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.item.AbstractMinecraftItem;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.nbt.AbstractMinecraftNBTTag;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;

public class CompoundLibraryManager  {

    public static Server server;

    public static CompoundLibraryApi getPluginManager(Plugin plugin){
        server = plugin.getServer();
        initPluginManager(plugin.getServer());
        return new PluginManager();
    }

    protected static void initPluginManager(Server server){
        String version = paraphrase(server);
        AbstractMinecraftNBTTag.init(version);
        AbstractMinecraftEntity.init(version);
        AbstractMinecraftEntityTile.init(version);
        AbstractMinecraftItem.init(version);
        AbstractNBTTagCompound.init(AbstractMinecraftNBTTag.getInstance(), version);
        CraftItemStack.initCraftItemStackClass(version);
        MinecraftItemStack.initMinecraftItemStackClass();
    }

    protected static String paraphrase(Server server){ return server.getClass().getPackage().getName().replace(".", ",").split(",")[3]; }

}
