package sk.dudoslav.adventure.game.world.generators;

import sk.dudoslav.adventure.game.world.generators.generatorcomponents.*;

/**
 * Created by dusan on 10.08.2015.
 */
public class MountainGenerator extends Generator{

    public MountainGenerator(){
        //addGeneratorComponent(new FlatTerrainGeneratorComponent());
        addGeneratorComponent(new HeightGeneratorComponent(300));
        addGeneratorComponent(new HeightGeneratorComponent(200));
        addGeneratorComponent(new SimplexTerrainGeneratorComponent(0.8f));
        //addGeneratorComponent(new FractalTerrainGeneratorComponent(1f,9));
        //addGeneratorComponent(new SmoothTerrainGeneratorComponent());
    }

}
