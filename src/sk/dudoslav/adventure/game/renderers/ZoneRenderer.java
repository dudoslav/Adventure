package sk.dudoslav.adventure.game.renderers;

import org.joml.Vector3f;
import sk.dudoslav.adventure.engine.BufferedRenderer;
import sk.dudoslav.adventure.game.Zone;

/**
 * Created by dusan on 10.08.2015.
 */
public class ZoneRenderer {

    public void render(Zone zone, BufferedRenderer br){
        for (int z = 0; z < Zone.HEIGHT-1; z++){
            for (int x = 0; x < Zone.WIDTH-1; x++){
                br.addVertex3f(x + zone.getOffsetX()*(Zone.WIDTH-1), zone.getPointAt(x, z), z + zone.getOffsetY()*(Zone.HEIGHT-1));
                br.addVertex3f(x + zone.getOffsetX()*(Zone.WIDTH-1), zone.getPointAt(x, z + 1), z + 1 + zone.getOffsetY()*(Zone.HEIGHT-1));
                br.addVertex3f(x + 1 + zone.getOffsetX()*(Zone.WIDTH-1), zone.getPointAt(x + 1, z), z + zone.getOffsetY()*(Zone.HEIGHT-1));

                br.addVertex3f(x + zone.getOffsetX()*(Zone.WIDTH-1), zone.getPointAt(x, z + 1), z + 1 + zone.getOffsetY()*(Zone.HEIGHT-1));
                br.addVertex3f(x + 1 + zone.getOffsetX()*(Zone.WIDTH-1), zone.getPointAt(x + 1, z + 1), z + 1 + zone.getOffsetY()*(Zone.HEIGHT-1));
                br.addVertex3f(x + 1 + zone.getOffsetX()*(Zone.WIDTH-1), zone.getPointAt(x + 1, z), z + zone.getOffsetY()*(Zone.HEIGHT-1));

                Vector3f e1;
                Vector3f e2;
                Vector3f n;

                e1 = new Vector3f(0,zone.getPointAt(x, z)-zone.getPointAt(x, z + 1), -1);
                e2 = new Vector3f(-1,zone.getPointAt(x, z + 1)-zone.getPointAt(x + 1, z), 1);
                n = e1.cross(e2);

                br.addNormal3f(n.x,n.y,n.z);
                br.addNormal3f(n.x,n.y,n.z);
                br.addNormal3f(n.x,n.y,n.z);

                e1 = new Vector3f(-1,zone.getPointAt(x, z + 1)-zone.getPointAt(x + 1, z + 1),0);
                e2 = new Vector3f(1,zone.getPointAt(x + 1, z) - zone.getPointAt(x, z + 1), -1);
                n = e1.cross(e2);
                n.negate();

                br.addNormal3f(n.x,n.y,n.z);
                br.addNormal3f(n.x,n.y,n.z);
                br.addNormal3f(n.x,n.y,n.z);
            }
        }
    }

}
