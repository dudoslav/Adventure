package sk.dudoslav.adventure.game.generators;

import sk.dudoslav.adventure.game.Zone;
import sk.dudoslav.adventure.game.generators.generatorcomponents.DudHillGeneratorComponent;
import sk.dudoslav.adventure.game.generators.generatorcomponents.RandomTerrainGeneratorComponent;

import java.util.Random;

/**
 * Created by dusan on 10.08.2015.
 */
public class MountainGenerator extends Generator{

    public MountainGenerator(){
        Random r = new Random();
        addGeneratorComponent(new RandomTerrainGeneratorComponent(r.nextFloat()*2+1));
        addGeneratorComponent(new DudHillGeneratorComponent(r.nextFloat()*30));
    }

}
