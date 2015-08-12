package sk.dudoslav.adventure.game.generators.generatorcomponents;

import sk.dudoslav.adventure.game.Zone;

/**
 * Created by dusan on 12.08.2015.
 */
public class SmoothTerrainGeneratorComponent extends GeneratorComponent{

    /*private int s;

    public SmoothTerrainGeneratorComponent(int s){
        this.s = s;
    }*/

    @Override
    public void generate(Zone z) {

        float a;

        for (int y = 2; y < Zone.HEIGHT-2; y++){
            for (int x = 2; x < Zone.WIDTH-2; x++){
                a = (z.getPointAt(x,y)+z.getPointAt(x+1,y)+z.getPointAt(x,y+1)+z.getPointAt(x+1,y+1)) / 4;
                z.setPointAt(x,y,a);
            }
        }
    }
}
