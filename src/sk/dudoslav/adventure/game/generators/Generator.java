package sk.dudoslav.adventure.game.generators;

import sk.dudoslav.adventure.game.Zone;
import sk.dudoslav.adventure.game.generators.generatorcomponents.GeneratorComponent;

import java.util.ArrayList;

/**
 * Created by dusan on 10.08.2015.
 */
public class Generator {
    ArrayList<GeneratorComponent> c = new ArrayList<>();

    private float r = 0f,g = 0f,b = 0f;

    public void generate(Zone z){
        for(GeneratorComponent gc : c){
            gc.generate(z);
            z.setColor(r,g,b);
        }
    }

    protected void addGeneratorComponent(GeneratorComponent gc){
        c.add(gc);
    }

    protected void setColor(float r, float g, float b){
        this.r = r;
        this.g = g;
        this.b = b;
    }
}
