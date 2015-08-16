package sk.dudoslav.adventure.game.rendering;

import sk.dudoslav.adventure.engine.AdventureProperties;
import sk.dudoslav.adventure.game.player.Player;
import sk.dudoslav.adventure.game.world.World;

/**
 * Created by dusan on 11.08.2015.
 */
public class VisibleZoneManager {
    private final int trd;
    private final int wtrd;

    private VisibleZone vz;
    private VisibleZoneRendererManager vzrm = new VisibleZoneRendererManager();

    private int lpx = -100,lpz = -100;
    private boolean ir = false;

    public VisibleZoneManager(AdventureProperties ap){
        trd = ap.getRenderDistance();
        wtrd = trd*2+1;
        vz = new VisibleZone(wtrd);
    }

    public void update(Player p, World w){
        if(lpx != p.getZoneX() || lpz != p.getZoneY()) {
            ir = false;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int y = 0; y < wtrd; y++) {
                        for (int x = 0; x < wtrd; x++) {
                            vz.addZone(x,y,w.loadOrGenerateZone(p.getZoneX() + x - trd - 1, p.getZoneY() + y - trd - 1));
                        }
                    }
                    ir = true;
                }
            }).start();
        }

        lpx = p.getZoneX();
        lpz = p.getZoneY();
    }

    public void render(){
        if(ir) {
            vzrm.updateVBO(vz);
            ir = false;
        }
        vzrm.renderVBO();
    }

}
