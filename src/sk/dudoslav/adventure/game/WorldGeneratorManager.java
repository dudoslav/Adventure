package sk.dudoslav.adventure.game;

import sk.dudoslav.adventure.game.generators.MountainGenerator;


/**
 * Created by dusan on 10.08.2015.
 */
public class WorldGeneratorManager {

    public Zone generateZone(){
        Zone z = new Zone();
        MountainGenerator mg = new MountainGenerator();
        mg.generate(z);
        return z;
    }

}
