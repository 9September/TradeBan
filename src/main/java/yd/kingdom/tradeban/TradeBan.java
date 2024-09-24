package yd.kingdom.tradeban;

import org.bukkit.plugin.java.JavaPlugin;

public final class TradeBan extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getLogger().info("TradeBan plugin has been enabled.");

    }

    @Override
    public void onDisable() {
        getLogger().info("TradeBan plugin has been disabled.");
    }
}
