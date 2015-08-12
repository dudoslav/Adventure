package sk.dudoslav.adventure.game.generators;

import sk.dudoslav.adventure.game.generators.generatorcomponents.*;
import sk.dudoslav.adventure.game.generators.noise.SimplexOctaveGenerator;

import java.util.Random;

/**
 * Created by dusan on 10.08.2015.
 */
public class MountainGenerator extends Generator{

    public MountainGenerator(){
        setColor(0.58f,0.58f,0.58f);
        Random r = new Random();
        SimplexOctaveGenerator sog = new SimplexOctaveGenerator(r,16);
        sog.setScale(1 / 512.f);

        //addGeneratorComponent(new FlatTerrainGeneratorComponent());
        addGeneratorComponent(new SimplexTerrainGeneratorComponent(sog,100));
        /*addGeneratorComponent(new FractalTerrainGeneratorComponent(1f,9));
        //addGeneratorComponent(new SmoothTerrainGeneratorComponent());*/
    }

}
