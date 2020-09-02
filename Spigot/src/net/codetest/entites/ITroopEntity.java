package net.codetest.entites;

public interface ITroopEntity {

    void init();

    void initName(org.bukkit.Location location);

    void initClothing();

    void clearPathfinder();

    void initPathfinder();

    void spawn(org.bukkit.Location location);

    void despawn();

}
