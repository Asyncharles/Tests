package net.codetest.particles;


import net.codetest.utils.ParticleEffect;
import net.codetest.utils.ParticleUtils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import toxi.geom.Vec3D;
import toxi.geom.*;


import java.util.ArrayList;

public class Spline  {

   /* @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            Location pLocation = player.getLocation();
            ArrayList<Vec3D> vec3D = new ArrayList<>();
            Vec3D vec = new Vec3D((float) pLocation.getX(), (float) pLocation.getY(), (float) pLocation.getZ());
            Vec3D vec1 = new Vec3D((float) pLocation.add(3, 3, 3).getX(), (float) pLocation.add(3, 3, 3).getY(), (float) pLocation.add(3, 3, 3).getZ());
            Vec3D vec2 = new Vec3D((float) pLocation.add(6, 6, 6).getX(), (float) pLocation.add(6, 6, 6).getY(), (float) pLocation.add(6, 6, 6).getZ());
            Vec3D vec3 = new Vec3D((float)  pLocation.add(12, 18, 14).getX(), (float) pLocation.add(12, 18, 14).getY(), (float) pLocation.add(12, 18, 14).getZ());
            vec3D.add(vec);
            vec3D.add(vec1);
            vec3D.add(vec2);
            vec3D.add(vec3);
            Spline3D spline3D = new Spline3D(vec3D);
            for(int i = 0; i < vec3D.size(); i++) {
                ParticleUtils.display(ParticleEffect.FLAME, spline3D.getPointList().get(i), 1, 1f);
            }
        }

        return false;
    } */
}
