package sk.dudoslav.adventure.game.generators;

import sk.dudoslav.adventure.game.generators.generatorcomponents.*;
import sk.dudoslav.adventure.game.generators.noise.SimplexOctaveGenerator;

import java.util.Random;

/**
 * Created by dusan on 10.08.2015.
 */
public class MountainGenerator extends Generator{

    public MountainGenerator(){
        //addGeneratorComponent(new FlatTerrainGeneratorComponent());
        addGeneratorComponent(new HeightGeneratorComponent(300));
        addGeneratorComponent(new HeightGeneratorComponent(200));
        addGeneratorComponent(new SimplexTerrainGeneratorComponent(0.5f));
        //addGeneratorComponent(new FractalTerrainGeneratorComponent(1f,9));
        //addGeneratorComponent(new SmoothTerrainGeneratorComponent());
    }

}
