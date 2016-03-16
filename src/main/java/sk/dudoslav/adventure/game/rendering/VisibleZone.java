package sk.dudoslav.adventure.game.rendering;

import sk.dudoslav.adventure.game.world.Zone;

/**
 * Created by dusan on 12.08.2015.
 */
public class VisibleZone {
    private int worldTileRenderDistance;

    private Zone zones[][];

    public VisibleZone(int worldTileRenderDistance){
        this.worldTileRenderDistance = worldTileRenderDistance;
        zones = new Zone[worldTileRenderDistance][worldTileRenderDistance];
    }

    public float getPointAt(int x, int y){
        if(x >= 0 && x < worldTileRenderDistance *(Zone.WIDTH-1) && y >= 0 && y < worldTileRenderDistance *(Zone.HEIGHT-1))
            return zones[x / (Zone.WIDTH-1)][y / (Zone.HEIGHT-1)].getPointAt(x % (Zone.WIDTH-1),y % (Zone.HEIGHT-1));
        return 0;
    }

    public void addZone(int x, int y, Zone zone){
        zones[x][y] = zone;
    }

    public Zone getZone(int x, int y){
        return zones[x][y];
    }

    public int getWorldTileRenderDistance() {
        return worldTileRenderDistance;
    }

}
