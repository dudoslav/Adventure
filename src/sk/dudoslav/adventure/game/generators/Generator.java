package sk.dudoslav.adventure.game.generators;

import sk.dudoslav.adventure.game.Zone;
import sk.dudoslav.adventure.game.generators.generatorcomponents.GeneratorComponent;

import java.util.ArrayList;

/**
 * Created by dusan on 10.08.2015.
 */
public class Generator {
    ArrayList<GeneratorComponent> c = new ArrayList<>();

    public void generate(Zone z){
        for(GeneratorComponent gc : c){
            gc.generate(z);
        }
    }

    public void addGeneratorComponent(GeneratorComponent gc){
        c.add(gc);
    }
}
