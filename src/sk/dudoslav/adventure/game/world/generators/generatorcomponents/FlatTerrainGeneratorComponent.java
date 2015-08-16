package sk.dudoslav.adventure.game.world.generators.generatorcomponents;

import sk.dudoslav.adventure.game.world.Zone;

/**
 * Created by dusan on 12.08.2015.
 */
public class FlatTerrainGeneratorComponent extends GeneratorComponent {

    @Override
    public void generate(Zone z) {
        for (int y = 0; y < Zone.HEIGHT; y++){
            for (int x = 0; x < Zone.WIDTH; x++){
                z.setPointAt(x,y,3);
            }
        }
    }
}
