package sk.dudoslav.adventure.game;

import sk.dudoslav.adventure.game.generators.MountainGenerator;


/**
 * Created by dusan on 10.08.2015.
 */
public class ZoneGeneratorManager {

    public Zone generateZone(int x, int y){
        Zone z = new Zone(x,y);
        MountainGenerator mg = new MountainGenerator();
        mg.generate(z);
        return z;
    }

}
