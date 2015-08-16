package sk.dudoslav.adventure.game.renderers;

import org.joml.Vector3f;
import sk.dudoslav.adventure.engine.BufferedRenderer;
import sk.dudoslav.adventure.game.VisibleZone;
import sk.dudoslav.adventure.game.Zone;

/**
 * Created by dusan on 12.08.2015.
 */
public class VisibleZoneRenderer {

    private Vector3f getNormal(int x, int z, VisibleZone vz){
        Vector3f e1;
        Vector3f e2;
        Vector3f n;

        e1 = new Vector3f(1f,0f,vz.getPointAt(x + 1, z) - vz.getPointAt(x - 1, z));
        e2 = new Vector3f(0f,1f,vz.getPointAt(x , z + 1)-vz.getPointAt( x, z - 1));


        n = e1.cross(e2);
        n.normalize();

        return n;
    }

    public void render(BufferedRenderer br, VisibleZone vz){
        Zone zone;
        for(int vzy = 0; vzy < vz.getWtrd(); vzy++)
            for(int vzx = 0; vzx < vz.getWtrd(); vzx++) {
                zone = vz.getZone(vzx, vzy);
                for (int z = 0; z < Zone.HEIGHT-1; z++)
                    for (int x = 0; x < Zone.WIDTH-1; x++) {
                        br.addVertex3f(x + zone.getOffsetX() * (Zone.WIDTH - 1), zone.getPointAt(x, z), z + zone.getOffsetY() * (Zone.HEIGHT - 1));
                        br.addVertex3f(x + zone.getOffsetX() * (Zone.WIDTH - 1), zone.getPointAt(x, z + 1), z + 1 + zone.getOffsetY() * (Zone.HEIGHT - 1));
                        br.addVertex3f(x + 1 + zone.getOffsetX() * (Zone.WIDTH - 1), zone.getPointAt(x + 1, z), z + zone.getOffsetY() * (Zone.HEIGHT - 1));

                        br.addVertex3f(x + zone.getOffsetX() * (Zone.WIDTH - 1), zone.getPointAt(x, z + 1), z + 1 + zone.getOffsetY() * (Zone.HEIGHT - 1));
                        br.addVertex3f(x + 1 + zone.getOffsetX() * (Zone.WIDTH - 1), zone.getPointAt(x + 1, z + 1), z + 1 + zone.getOffsetY() * (Zone.HEIGHT - 1));
                        br.addVertex3f(x + 1 + zone.getOffsetX() * (Zone.WIDTH - 1), zone.getPointAt(x + 1, z), z + zone.getOffsetY() * (Zone.HEIGHT - 1));

                        br.addTexCoord2f((float)x / Zone.WIDTH, (float)z / Zone.HEIGHT);
                        br.addTexCoord2f((float)x / Zone.WIDTH, (float)(z + 1) / Zone.HEIGHT);
                        br.addTexCoord2f((float)(x + 1) / Zone.WIDTH, (float)z / Zone.HEIGHT);

                        br.addTexCoord2f((float)x / Zone.WIDTH, (float)(z + 1)/ Zone.HEIGHT);
                        br.addTexCoord2f((float)(x + 1) / Zone.WIDTH, (float)(z + 1) / Zone.HEIGHT);
                        br.addTexCoord2f((float)(x + 1) / Zone.WIDTH, (float)z / Zone.HEIGHT);

                        Vector3f n;

                        n = getNormal(x+vzx*(Zone.WIDTH-1),z+vzy*(Zone.HEIGHT-1),vz);
                        br.addNormal3f(n.x,n.y,n.z);
                        n = getNormal(x+vzx*(Zone.WIDTH-1),z+1+vzy*(Zone.HEIGHT-1),vz);
                        br.addNormal3f(n.x,n.y,n.z);
                        n = getNormal(x+1+vzx*(Zone.WIDTH-1),z+vzy*(Zone.HEIGHT-1),vz);
                        br.addNormal3f(n.x,n.y,n.z);

                        n = getNormal(x+vzx*(Zone.WIDTH-1),z+1+vzy*(Zone.HEIGHT-1),vz);
                        br.addNormal3f(n.x,n.y,n.z);
                        n = getNormal(x+1+vzx*(Zone.WIDTH-1),z+1+vzy*(Zone.HEIGHT-1),vz);
                        br.addNormal3f(n.x,n.y,n.z);
                        n = getNormal(x+1+vzx*(Zone.WIDTH-1),z+vzy*(Zone.HEIGHT-1),vz);
                        br.addNormal3f(n.x,n.y,n.z);
                    }
            }
    }
}
