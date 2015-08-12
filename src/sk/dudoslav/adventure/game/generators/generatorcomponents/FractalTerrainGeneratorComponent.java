package sk.dudoslav.adventure.game.generators.generatorcomponents;

import sk.dudoslav.adventure.game.Zone;

import java.util.Random;

/**
 * Created by dusan on 12.08.2015.
 */
public class FractalTerrainGeneratorComponent extends GeneratorComponent {
    private final float roug;
    private int iter;

    private final Random r = new Random();

    public FractalTerrainGeneratorComponent(float roug, int iter){
        this.roug = roug;
        this.iter = iter;
    }

    @Override
    public void generate(Zone z) {
        diamondStep(2,2,Zone.WIDTH-3,2,Zone.WIDTH-3,Zone.HEIGHT-3,2,Zone.HEIGHT-3,80f,z, iter);
    }

    private void diamondStep(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, float d, Zone z, int i){
        float avg = (z.getPointAt(x1,y1)+z.getPointAt(x2, y2)+z.getPointAt(x3,y3)+z.getPointAt(x4,y4))/4+r.nextFloat()*d*2-d;
        z.setPointAt((x1 + x3) / 2,(y1 + y3) / 2,avg);
        squareStep(x1, y1, x2, y2, x3, y3, x4, y4, d, z, i);
    }

    private void squareStep(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, float d, Zone z, int i){
        int mx = (x1 + x3) / 2;
        int my = (y1 + y3) / 2;
        float avg;

        avg = (z.getPointAt(x1,y1)+z.getPointAt(x2,y2)+z.getPointAt(mx,my)*2) / 4;
        z.setPointAt(mx,y1,avg+r.nextFloat()*d*2-d);

        avg = (z.getPointAt(x4,y4)+z.getPointAt(x3,y3)+z.getPointAt(mx,my)*2) / 4;
        z.setPointAt(mx,y3,avg+r.nextFloat()*d*2-d);

        avg = (z.getPointAt(x1,y1)+z.getPointAt(x4,y4)+z.getPointAt(mx,my)*2) / 4;
        z.setPointAt(x1,my,avg+r.nextFloat()*d*2-d);

        avg = (z.getPointAt(x2,y2)+z.getPointAt(x3,y3)+z.getPointAt(mx,my)*2) / 4;
        z.setPointAt(x3,my,avg+r.nextFloat()*d*2-d);

        d *= Math.pow(2,-roug);
        i--;

        if (i > 0) {
            diamondStep(x1, y1, mx, y1, mx, my, x1, my, d, z, i);
            diamondStep(mx, y1, x2, y2, x3, my, mx, my, d, z, i);
            diamondStep(mx, my, x3, my, x3, y3, mx, y3, d, z, i);
            diamondStep(x1, my, mx, my, mx, y3, x4, y4, d, z, i);
        }
    }

}
