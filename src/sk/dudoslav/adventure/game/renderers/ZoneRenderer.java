package sk.dudoslav.adventure.game.renderers;

import org.joml.Vector3f;
import sk.dudoslav.adventure.engine.BufferedRenderer;
import sk.dudoslav.adventure.game.Zone;

/**
 * Created by dusan on 10.08.2015.
 */
public class ZoneRenderer {

    private Vector3f getNormal(int x, int z, Zone zone){
        Vector3f e1;
        Vector3f e2;
        Vector3f n;

        e1 = new Vector3f(1f,0f,zone.getPointAt(x + 1, z) - zone.getPointAt(x - 1, z));
        e2 = new Vector3f(0f,1f,zone.getPointAt(x , z + 1)-zone.getPointAt( x, z - 1));

        n = e1.cross(e2);
        n.normalize();

        return n;
    }

    public void render(Zone zone, BufferedRenderer br){
        for (int z = 0; z < Zone.HEIGHT-1; z++){
            for (int x = 0; x < Zone.WIDTH-1; x++){
                br.addVertex3f(x + zone.getOffsetX()*(Zone.WIDTH-1), zone.getPointAt(x, z), z + zone.getOffsetY()*(Zone.HEIGHT-1));
                br.addVertex3f(x + zone.getOffsetX()*(Zone.WIDTH-1), zone.getPointAt(x, z + 1), z + 1 + zone.getOffsetY()*(Zone.HEIGHT-1));
                br.addVertex3f(x + 1 + zone.getOffsetX() * (Zone.WIDTH - 1), zone.getPointAt(x + 1, z), z + zone.getOffsetY() * (Zone.HEIGHT - 1));

                br.addVertex3f(x + zone.getOffsetX()*(Zone.WIDTH-1), zone.getPointAt(x, z + 1), z + 1 + zone.getOffsetY()*(Zone.HEIGHT-1));
                br.addVertex3f(x + 1 + zone.getOffsetX()*(Zone.WIDTH-1), zone.getPointAt(x + 1, z + 1), z + 1 + zone.getOffsetY()*(Zone.HEIGHT-1));
                br.addVertex3f(x + 1 + zone.getOffsetX() * (Zone.WIDTH - 1), zone.getPointAt(x + 1, z), z + zone.getOffsetY() * (Zone.HEIGHT - 1));

                br.addColor3f(zone.getR(),zone.getG(),zone.getB());
                br.addColor3f(zone.getR(),zone.getG(),zone.getB());
                br.addColor3f(zone.getR(),zone.getG(),zone.getB());

                br.addColor3f(zone.getR(),zone.getG(),zone.getB());
                br.addColor3f(zone.getR(),zone.getG(),zone.getB());
                br.addColor3f(zone.getR(),zone.getG(),zone.getB());

                Vector3f n;

                n = getNormal(x,z,zone);
                br.addNormal3f(n.x,n.y,n.z);
                n = getNormal(x,z+1,zone);
                br.addNormal3f(n.x,n.y,n.z);
                n = getNormal(x+1,z,zone);
                br.addNormal3f(n.x,n.y,n.z);

                n = getNormal(x,z+1,zone);
                br.addNormal3f(n.x,n.y,n.z);
                n = getNormal(x+1,z+1,zone);
                br.addNormal3f(n.x,n.y,n.z);
                n = getNormal(x+1,z,zone);
                br.addNormal3f(n.x,n.y,n.z);
            }
        }
    }

}
