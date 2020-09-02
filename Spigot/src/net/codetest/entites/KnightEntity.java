package net.codetest.entites;

import net.codetest.utils.NMSUtils;
import net.minecraft.server.v1_8_R3.EntityVillager;
import net.minecraft.server.v1_8_R3.PathfinderGoalSelector;
import net.minecraft.server.v1_8_R3.World;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class KnightEntity extends EntityVillager implements ITroopEntity{


    public KnightEntity(World world, org.bukkit.Location location) {
        super(world);
        this.init();
        this.initName(location);
        this.initClothing();
        this.clearPathfinder();
        this.initPathfinder();

    }

    @Override
    public void init() {

    }

    @Override
    public void initName(Location location) {

    }

    @Override
    public void initClothing() {
        this.setEquipment(3, CraftItemStack.asNMSCopy(new ItemStack(Material.DIAMOND_CHESTPLATE)));
    }

    @Override
    public void clearPathfinder() {
        ((List) NMSUtils.getPrivateField("b", PathfinderGoalSelector.class, goalSelector)).clear();
        ((List) NMSUtils.getPrivateField("c", PathfinderGoalSelector.class, goalSelector)).clear();
        ((List) NMSUtils.getPrivateField("b", PathfinderGoalSelector.class, targetSelector)).clear();
        ((List) NMSUtils.getPrivateField("c", PathfinderGoalSelector.class, targetSelector)).clear();
    }

    @Override
    public void initPathfinder() {

    }

    @Override
    public void spawn(Location location) {

    }

    @Override
    public void despawn() {

    }

}
