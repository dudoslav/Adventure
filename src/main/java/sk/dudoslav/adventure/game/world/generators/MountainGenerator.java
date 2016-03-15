package sk.dudoslav.adventure.game.world.generators;

import sk.dudoslav.adventure.game.world.generators.generatorcomponents.*;

/**
 * Created by dusan on 10.08.2015.
 */
public class MountainGenerator extends Generator{

    public MountainGenerator(){
        //addGeneratorComponent(new FlatTerrainGeneratorComponent());
        //addGeneratorComponent(new HeightGeneratorComponent(600));
        addGeneratorComponent(new HeightGeneratorComponent(400));
        addGeneratorComponent(new SimplexTerrainGeneratorComponent(1.5f));
        //addGeneratorComponent(new FractalTerrainGeneratorComponent(1f,9));
        //addGeneratorComponent(new SmoothTerrainGeneratorComponent());
    }

}
