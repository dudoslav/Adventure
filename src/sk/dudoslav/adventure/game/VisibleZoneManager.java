package sk.dudoslav.adventure.game;

import sk.dudoslav.adventure.engine.BufferedRenderer;
import sk.dudoslav.adventure.game.renderers.VisibleZoneRenderer;
import sk.dudoslav.adventure.game.renderers.ZoneRenderer;

/**
 * Created by dusan on 11.08.2015.
 */
public class VisibleZoneManager {
    private int trd = 3; //TODO: dorobit s properties --> toto je tile render distance
    private int wtrd = trd*2+1;

    private VisibleZone vz = new VisibleZone(wtrd);
    private VisibleZoneRenderer vzr = new VisibleZoneRenderer();

    private int lpx = -100,lpz = -100;
    private boolean sr = true;

    public void update(Player p, World w){

        sr = lpx != p.getZoneX() || lpz != p.getZoneY();

        lpx = p.getZoneX();
        lpz = p.getZoneY();

        if(sr) {
            for (int y = 0; y < wtrd; y++) {
                for (int x = 0; x < wtrd; x++) {
                    vz.addZone(x,y,w.loadOrGenerateZone(p.getZoneX() + x - trd - 1, p.getZoneY() + y - trd - 1));
                }
            }
        }
    }

    public void render(BufferedRenderer br){
        vzr.render(br,vz);
    }

    public boolean shouldRender(Player p){
        return sr;
    }

}
