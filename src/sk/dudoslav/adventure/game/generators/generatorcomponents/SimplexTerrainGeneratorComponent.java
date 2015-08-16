package sk.dudoslav.adventure.game.generators.generatorcomponents;

import sk.dudoslav.adventure.game.Zone;
import sk.dudoslav.adventure.game.generators.noise.SimplexOctaveGenerator;

import java.util.Random;

/**
 * Created by dusan on 12.08.2015.
 */
public class SimplexTerrainGeneratorComponent extends GeneratorComponent {

    private final SimplexOctaveGenerator sog = new SimplexOctaveGenerator(new Random(),8);
    private final float h;

    public SimplexTerrainGeneratorComponent(float h){
        sog.setScale(1 / 512.f);
        this.h = h;
    }

    @Override
    public void generate(Zone z) {
        float alt;
        for (int y = 0; y < Zone.HEIGHT; y++){
            for (int x = 0; x < Zone.WIDTH; x++){
                alt = (float) (sog.noise(x+z.getOffsetX()*(Zone.WIDTH-1),y+z.getOffsetY()*(Zone.HEIGHT-1),1.8d,0.5d)*h)*z.getPointAt(x,y);
                z.setPointAt(x,y, alt);
            }
        }
    }
}
