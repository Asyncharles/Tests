package net.codetest.gadgets;

import com.sun.xml.internal.ws.wsdl.writer.document.Part;
import net.codetest.Main;
import net.codetest.utils.ParticleEffect;
import net.codetest.utils.ParticleUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class EightGadget implements CommandExecutor {

    private Circle[] circles;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            circles = new Circle[] {
                    new Circle(player.getLocation().clone().subtract(0, 0, 1.5), 1, ParticleEffect.FLAME),
                    new Circle(player.getLocation().clone().subtract(0, 0, 1), 2, ParticleEffect.FLAME),
                    new Circle(player.getLocation().clone().subtract(0, 0, 0.5), 3, ParticleEffect.FLAME),
                    new Circle(player.getLocation().clone(), 4, ParticleEffect.FLAME),
                    new Circle(player.getLocation().clone().add(0, 0, 0.5), 3, ParticleEffect.FLAME),
                    new Circle(player.getLocation().clone().add(0, 0, 1), 2, ParticleEffect.FLAME),
                    new Circle(player.getLocation().clone().add(0, 0, 1.5), 1, ParticleEffect.FLAME)

            };

            new BukkitRunnable() {
                int follower = 0;
                boolean backwards = false;
                @Override
                public void run() {
                    if(!backwards) {
                        if(follower == circles.length - 1) {
                            backwards = true;
                            return;
                        }
                        circles[follower].playCircle();
                        follower++;

                    } else {
                        if(follower == 0) {
                            backwards = false;
                            return;
                        }
                        circles[follower].playCircle();
                        follower--;

                    }
                }
            }.runTaskTimer(Main.getInstance(), 10, 10);
            player.sendMessage(ChatColor.GREEN + "Done");
        }

        return false;
    }

}

final class Circle {

    private Location center;
    private ParticleEffect effect;
    private float radius;

    public Circle(final Location center, final float radius, final ParticleEffect effect) {
        this.center = center;
        this.effect = effect;
        this.radius = radius;
    }

    public void playCircle() {
        ParticleUtils.playVerticalCircle(effect, center, radius, 2, "x");
    }

}
