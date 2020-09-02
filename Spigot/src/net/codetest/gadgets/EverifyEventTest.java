package net.codetest.gadgets;

import net.everify.api.events.AsyncMailEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class EverifyEventTest implements Listener {

    @EventHandler
    public void verifyEvent(AsyncMailEvent event) {
        event.getPlayer().sendMessage("prout");
        event.setCancelled(true);
    }
}
