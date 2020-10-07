package pers.tuershen.nbtedit.compoundlibrary;

import pers.tuershen.nbtedit.compoundlibrary.api.CompoundLibraryApi;
import pers.tuershen.nbtedit.compoundlibrary.nms.Compound;
import pers.tuershen.nbtedit.compoundlibrary.nms.CraftItemStack;
import pers.tuershen.nbtedit.compoundlibrary.nms.MinecraftItemStack;
import pers.tuershen.nbtedit.compoundlibrary.nms.PluginManager;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.block.MinecraftEntityTile;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.entity.MinecraftEntity;
import pers.tuershen.nbtedit.compoundlibrary.nms.minecraft.item.MinecraftItem;
import pers.tuershen.nbtedit.compoundlibrary.nms.nbt.TagBase;
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
        MinecraftItem.init(version);
        CraftItemStack.initCraftItemStackClass(version);
        MinecraftItemStack.initMinecraftItemStackClass();
        Compound.initCompoundClass();
        MinecraftEntity.init(version);
        MinecraftEntityTile.init(version);
        TagBase.intiFactoryClass(version);
    }

    protected static String paraphrase(Server server){
        return server
                .getClass()
                .getPackage()
                .getName()
                .replace(".", ",")
                .split(",")[3];
    }

}
