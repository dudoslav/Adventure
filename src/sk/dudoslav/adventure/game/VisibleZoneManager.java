package sk.dudoslav.adventure.game;

import sk.dudoslav.adventure.engine.BufferedRenderer;
import sk.dudoslav.adventure.game.renderers.ZoneRenderer;

import java.util.HashMap;

/**
 * Created by dusan on 11.08.2015.
 */
public class VisibleZoneManager {
    private int trd = 3; //TODO: dorobit s properties --> toto je tile render distance
    private int wtrd = trd*2+1;

    private Zone zones[][] = new Zone[wtrd][wtrd];
    private ZoneRenderer zr = new ZoneRenderer();

    private int lpx,lpz;
    private boolean sr = true;

    public void update(Player p, World w){

        sr = lpx != p.getZoneX() || lpz != p.getZoneY();

        lpx = p.getZoneX();
        lpz = p.getZoneY();

        for(int y = 0; y < wtrd; y++){
            for(int x = 0; x < wtrd; x++){
                zones[x][y] = w.loadOrGenerateZone(p.getZoneX()+x-trd-1,p.getZoneY()+y-trd-1);
            }
        }
    }

    public void render(BufferedRenderer br){
        for(int y = 0; y < wtrd; y++){
            for(int x = 0; x < wtrd; x++){
                zr.render(zones[x][y],br);
            }
        }
    }

    public boolean shouldRender(Player p){
        return sr;
    }

}
