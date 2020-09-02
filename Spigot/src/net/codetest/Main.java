package net.codetest;

import net.codetest.gadgets.EightGadget;
import net.codetest.gadgets.EverifyEventTest;
import net.codetest.gadgets.ExplosiveSheep;
import net.codetest.gadgets.PointerGadget;

import net.codetest.particles.CircleParticle;
import net.codetest.particles.Spline;
import net.codetest.vehicule.Hypetrain;


import net.codetest.vehicule.Train;
import net.everify.EVerify;
import net.everify.api.MailManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main INSTANCE;

    private final PluginManager pluginManager = getServer().getPluginManager();
    private final EVerify api = (EVerify) Bukkit.getServer().getPluginManager().getPlugin("E-Verify");

    @Override
    public void onEnable() {
        registerCommands();
        registerEvents();
        INSTANCE = this;


    }

    @Override
    public void onDisable() {

    }

    public final void registerCommands() {
        //getCommand("hypetrain").setExecutor(new Hypetrain());
        getCommand("sheep").setExecutor(new ExplosiveSheep());
        getCommand("ptc").setExecutor(new EightGadget());
        //getCommand("spline").setExecutor(new Spline());
    }

    public final void registerEvents() {
        //pluginManager.registerEvents(new Hypetrain(), this);
        pluginManager.registerEvents(new ExplosiveSheep(), this);
        pluginManager.registerEvents(new Train(), this);
        pluginManager.registerEvents(new CircleParticle(), this);
        pluginManager.registerEvents(new PointerGadget(), this);
        pluginManager.registerEvents(new EverifyEventTest(), this);
    }

    public static final Main getInstance() {
        return INSTANCE;
    }

    private Player player;
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
}
