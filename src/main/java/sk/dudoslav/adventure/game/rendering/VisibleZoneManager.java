package sk.dudoslav.adventure.game.rendering;

import sk.dudoslav.adventure.engine.AdventureProperties;
import sk.dudoslav.adventure.game.player.Player;
import sk.dudoslav.adventure.game.world.World;

/**
 * Created by dusan on 11.08.2015.
 */
public class VisibleZoneManager {
    private final int tileRenderDistance;
    private final int worldTileRenderDistance;

    private VisibleZone visibleZone;
    private VisibleZoneRendererManager visibleZoneRendererManager = new VisibleZoneRendererManager();

    private int lpx = -100,lpz = -100;
    private boolean isReady = false;

    public VisibleZoneManager(AdventureProperties ap){
        tileRenderDistance = ap.getRenderDistance();
        worldTileRenderDistance = tileRenderDistance *2+1;
        visibleZone = new VisibleZone(worldTileRenderDistance);
    }

    public void update(Player p, World w){
        if(lpx != p.getZoneX() || lpz != p.getZoneY()) {
            isReady = false;
            new Thread(() -> {
                for (int y = 0; y < worldTileRenderDistance; y++) {
                    for (int x = 0; x < worldTileRenderDistance; x++) {
                        visibleZone.addZone(x,y,w.loadOrGenerateZone(p.getZoneX() + x - tileRenderDistance - 1, p.getZoneY() + y - tileRenderDistance - 1));
                    }
                }
                isReady = true;
            }).start();
        }

        lpx = p.getZoneX();
        lpz = p.getZoneY();
    }

    public void render(){
        if(isReady) {
            visibleZoneRendererManager.updateVBO(visibleZone);
            isReady = false;
        }
        visibleZoneRendererManager.renderVBO();
    }

    public void dispose(){
        visibleZoneRendererManager.dispose();
    }

}
