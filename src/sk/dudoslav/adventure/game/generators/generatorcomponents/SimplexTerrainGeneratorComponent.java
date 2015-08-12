package sk.dudoslav.adventure.game.generators.generatorcomponents;

import sk.dudoslav.adventure.game.Zone;
import sk.dudoslav.adventure.game.generators.noise.SimplexOctaveGenerator;

/**
 * Created by dusan on 12.08.2015.
 */
public class SimplexTerrainGeneratorComponent extends GeneratorComponent {

    private final SimplexOctaveGenerator sog;
    private final float h;

    public SimplexTerrainGeneratorComponent(SimplexOctaveGenerator sog, float h){
        this.sog = sog;
        this.h = h;
    }

    @Override
    public void generate(Zone z) {
        float alt;
        for (int y = 0; y < Zone.HEIGHT; y++){
            for (int x = 0; x < Zone.WIDTH; x++){
                alt = (float) (sog.noise(x+z.getOffsetX()*(Zone.WIDTH),y+z.getOffsetY()*(Zone.HEIGHT),0.8d,0.5d)*h);
                z.setPointAt(x,y, alt);
                if(alt == -0f) System.out.println(alt);
            }
        }
    }
}
