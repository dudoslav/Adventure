package sk.dudoslav.adventure.game.world;

import java.util.HashMap;

/**
 * Created by dusan on 10.08.2015.
 */
public class World {
    private HashMap<Long,Zone> t = new HashMap<>();

    private ZoneGeneratorManager zgm = new ZoneGeneratorManager();

    public Zone loadOrGenerateZone(int x, int y){
        long k = (long) x << 32 | y & 0xFFFFFFFFL;
        if(!t.containsKey(k)){
            t.put(k,zgm.generateZone(x,y));
        }
        return t.get(k);
    }
}
