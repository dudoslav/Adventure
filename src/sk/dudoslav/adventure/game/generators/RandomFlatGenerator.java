package sk.dudoslav.adventure.game.generators;

import sk.dudoslav.adventure.game.generators.generatorcomponents.FlatTerrainGeneratorComponent;
import sk.dudoslav.adventure.game.generators.generatorcomponents.RandomTerrainGeneratorComponent;
import sk.dudoslav.adventure.game.generators.generatorcomponents.SmoothTerrainGeneratorComponent;

import java.util.Random;

/**
 * Created by dusan on 12.08.2015.
 */
public class RandomFlatGenerator extends Generator {

    public RandomFlatGenerator(){
        Random r = new Random();
        addGeneratorComponent(new FlatTerrainGeneratorComponent());
        addGeneratorComponent(new RandomTerrainGeneratorComponent(r.nextFloat()*2+1));
        addGeneratorComponent(new SmoothTerrainGeneratorComponent());
    }
}
