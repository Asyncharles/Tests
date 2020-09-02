package net.codetest.utils;

import net.codetest.Main;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.function.BiConsumer;

public class ParticleUtils {

    private final static int DEF_RADIUS = 128;

    public static void drawParticleLine(Location from, Location to, ParticleEffect effect, int particles, Color color) {
        drawParticleLine(from, to, effect, particles, color.getRed(), color.getGreen(), color.getBlue());
    }

    public static void drawParticleLine(Location from, Location to, ParticleEffect effect, int particles, int r, int g, int b) {
        Location location = from.clone();
        Location target = to.clone();
        Vector link = target.toVector().subtract(location.toVector());
        float length = (float) link.length();
        link.normalize();

        float ratio = length / particles;
        Vector v = link.multiply(ratio);
        Location loc = location.clone().subtract(v);
        int step = 0;
        for (int i = 0; i < particles; i++) {
            if (step >= (double) particles)
                step = 0;
            step++;
            loc.add(v);
            if (effect == ParticleEffect.REDSTONE)
                effect.display(new ParticleEffect.OrdinaryColor(r, g, b), loc, 128);
            else
                effect.display(0, 0, 0, 0, 1, loc, 128);
        }
    }

    public static void playHelix(final Location loc, final float i, final ParticleEffect effect) {
        BukkitRunnable runnable = new BukkitRunnable() {
            double radius = 0;
            double step;
            double y = loc.getY();
            Location location = loc.clone().add(0, 3, 0);

            @Override
            public void run() {
                double inc = (2 * Math.PI) / 50;
                double angle = step * inc + i;
                Vector v = new Vector();
                v.setX(Math.cos(angle) * radius);
                v.setZ(Math.sin(angle) * radius);
                if (effect == ParticleEffect.REDSTONE)
                    display(0, 0, 255, location);
                else
                    display(effect, location);
                location.subtract(v);
                location.subtract(0, 0.1d, 0);
                if (location.getY() <= y) {
                    cancel();
                }
                step += 4;
                radius += 1 / 50f;
            }
        };
        runnable.runTaskTimer(Main.getInstance(), 0, 1);
    }

    public static void playHelixSmall(final Location loc, final float i, final ParticleEffect effect) {
        BukkitRunnable runnable = new BukkitRunnable() {
            double radius = 0;
            double step;
            double y = loc.getY();
            Location location = loc.clone().add(0, 3, 0);

            @Override
            public void run() {
                double inc = (2 * Math.PI) / 50;
                double angle = step * inc + i;
                Vector v = new Vector();
                v.setX(Math.cos(angle) * radius);
                v.setZ(Math.sin(angle) * radius);
                if (effect == ParticleEffect.REDSTONE)
                    display(0, 0, 255, location);
                else
                    display(effect, location);
                location.subtract(v);
                location.subtract(0.15d, 0.1d, 0.15d);
                if (location.getY() <= y) {
                    drawParticleLine(loc, location, effect, Math.round(i), Color.RED);
                    cancel();
                }
                step += 4;
                radius += 1 / 50f;
            }
        };

    }

    public static void playCircle(final ParticleEffect effect, final Location center, final float radius, final double inc) {

        for(double angle = 0; angle < 360; angle += inc) {
            double x = Math.sin(angle) * radius;
            double z = Math.cos(angle) * radius;
            display(effect, center.clone().add(x, 0, z));
        }
    }

    public static void playVerticalCircle(final ParticleEffect effect, final Location center, final float radius, final double inc, final String axis) {
        if (axis.toLowerCase() == "x") {
            for (double angle = 0; angle < 360; angle += inc) {
                double x = Math.sin(angle) * radius;
                double y = Math.cos(angle) * radius;
                display(effect, center.clone().add(x, y, 0));
            }
        } else {
            for (double angle = 0; angle < 360; angle += inc) {
                double z = Math.sin(angle) * radius;
                double y = Math.cos(angle) * radius;
                display(effect, center.clone().add(0, y, z));
            }
        }
    }


    public static void playCircleTime(final ParticleEffect effect, final Location center, final float radius, final double inc, final int secondes) {
        final int[] index = {0};
        new BukkitRunnable() {
            @Override
            public void run() {
                if(index[0] == secondes) {
                    cancel();
                }
                for(double angle = 0; angle< 360; angle += inc) {
                    double x = Math.sin(angle) * radius;
                    double z = Math.cos(angle) * radius;
                    display(effect, center.clone().add(x, 0, z));
                }
                index[0]++;
            }
        }.runTaskTimer(Main.getInstance(), 20, 20);

    }


    public static void display(ParticleEffect effect, Location location, int amount, float speed) {
        effect.display(0, 0, 0, speed, amount, location, 128);
    }

    public static void display(ParticleEffect effect, Location location, int amount) {
        effect.display(0, 0, 0, 0, amount, location, 128);
    }

    public static void display(ParticleEffect effect, Location location) {
        display(effect, location, 1);
    }

    public static void display(ParticleEffect effect, double x, double y, double z, Location location, int amount) {
        effect.display((float) x, (float) y, (float) z, 0f, amount, location, 128);
    }

    public static void display(ParticleEffect effect, int red, int green, int blue, Location location, int amount) {
        for (int i = 0; i < amount; i++)
            effect.display(new ParticleEffect.OrdinaryColor(red, green, blue), location, DEF_RADIUS);
    }

    public static void display(int red, int green, int blue, Location location) {
        display(ParticleEffect.REDSTONE, red, green, blue, location, 1);
    }

    public static void display(ParticleEffect effect, int red, int green, int blue, Location location) {
        display(effect, red, green, blue, location, 1);
    }


    enum alpha {


    }



}
