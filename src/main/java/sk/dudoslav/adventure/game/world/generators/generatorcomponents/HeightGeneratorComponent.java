package sk.dudoslav.adventure.game.world.generators.generatorcomponents;

import sk.dudoslav.adventure.game.world.Zone;
import sk.dudoslav.adventure.game.world.generators.noise.SimplexOctaveGenerator;

import java.util.Random;

/**
 * Created by dusan on 16.08.2015.
 */
public class HeightGeneratorComponent extends GeneratorComponent {

    private final SimplexOctaveGenerator sog = new SimplexOctaveGenerator(new Random(),8);
    private final float h;

    public HeightGeneratorComponent(float h){
        sog.setScale(1 / 2048.f);
        this.h = h;
    }

    @Override
    public void generate(Zone z) {
        float alt;
        for (int y = 0; y < Zone.HEIGHT; y++){
            for (int x = 0; x < Zone.WIDTH; x++){
                alt = (float) (sog.noise(x+z.getOffsetX()*(Zone.WIDTH-1),y+z.getOffsetY()*(Zone.HEIGHT-1),1.0d,0.4d)*h);
                z.setPointAt(x,y, alt);
            }
        }
    }
}
