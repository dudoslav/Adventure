package sk.dudoslav.adventure.game.generators.generatorcomponents;

import sk.dudoslav.adventure.game.Zone;

import java.util.Random;

/**
 * Created by dusan on 10.08.2015.
 */
public class RandomTerrainGeneratorComponent extends GeneratorComponent {

    private final float h;

    public RandomTerrainGeneratorComponent(float h){
        this.h = h;
    }

    @Override
    public void generate(Zone z) {
        Random r = new Random();
        for (int y = 1; y < Zone.HEIGHT-1; y++){
            for (int x = 1; x < Zone.WIDTH-1; x++){
                z.setPointAt(x,y,r.nextFloat()*h);
            }
        }
    }
}
