package sk.dudoslav.adventure.game;

import sk.dudoslav.adventure.game.generators.Generator;
import sk.dudoslav.adventure.game.generators.MountainGenerator;
import sk.dudoslav.adventure.game.generators.RandomFlatGenerator;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by dusan on 10.08.2015.
 */
public class ZoneGeneratorManager {
    private ArrayList<Generator> g = new ArrayList<>();


    public ZoneGeneratorManager(){
        g.add(new MountainGenerator());
        //g.add(new RandomFlatGenerator());
    }

    public Zone generateZone(int x, int y){
        Random r = new Random();
        Zone z = new Zone(x,y);
        g.get(r.nextInt(g.size())).generate(z);
        return z;
    }

}
