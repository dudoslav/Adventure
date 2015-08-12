package sk.dudoslav.adventure.game.generators.generatorcomponents;

import sk.dudoslav.adventure.game.Zone;

import java.util.Random;

/**
 * Created by dusan on 10.08.2015.
 */
public class DudHillGeneratorComponent extends GeneratorComponent {
    private final float mh;

    public DudHillGeneratorComponent(float mh){
        this.mh = mh;
    }

    @Override
    public void generate(Zone z) {
        Random r = new Random();
        float h = mh;
        float mx = Zone.WIDTH  / 2-3;
        float my = Zone.HEIGHT / 2-3;

        float a;

        for (int y = 2; y < my; y++){
            for (int x = 2; x < mx; x++){
                a = (z.getPointAt(x,y)+z.getPointAt(x-1,y)+z.getPointAt(x,y-1)+z.getPointAt(x-1,y-1)) / 4;
                z.setPointAt(x,y,(float)(a+((Math.sqrt(Math.pow(x,2)+Math.pow(y,2))*mh)/mx)));

                a = (z.getPointAt((int)(x+mx),y)+z.getPointAt((int)(x+mx-1),y)+z.getPointAt((int)(x+mx),y-1)+z.getPointAt((int)(x+mx-1),y-1)) / 4;
                z.setPointAt((int)(x+mx),y,(float)(a+((Math.sqrt(Math.pow(mx-x,2)+Math.pow(y,2))*mh)/mx)));

                a = (z.getPointAt(x,y)+z.getPointAt(x-1,y)+z.getPointAt(x,y-1)+z.getPointAt(x-1,y-1)) / 4;
                z.setPointAt((int)(x+mx),(int)(y+my),(float)(a+((Math.sqrt(Math.pow(mx-x,2)+Math.pow(my-y,2))*mh)/mx)));

                a = (z.getPointAt(x,y)+z.getPointAt(x-1,y)+z.getPointAt(x,y-1)+z.getPointAt(x-1,y-1)) / 4;
                z.setPointAt(x,(int)(y+my),(float)(a+((Math.sqrt(Math.pow(x,2)+Math.pow(my-y,2))*mh)/mx)));
            }
        }
    }
}
