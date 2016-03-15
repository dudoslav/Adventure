package sk.dudoslav.adventure.game.renderers;

import org.joml.Vector3f;
import sk.dudoslav.adventure.engine.graphics.BufferedRenderer;
import sk.dudoslav.adventure.game.rendering.VisibleZone;
import sk.dudoslav.adventure.game.world.Zone;

/**
 * Created by dusan on 12.08.2015.
 */
public class VisibleZoneRenderer {

    private Vector3f getNormal(int x, int z, VisibleZone vz) {
        Vector3f e1;
        Vector3f e2;
        Vector3f n;

        e1 = new Vector3f(1f, 0f, vz.getPointAt(x + 1, z) - vz.getPointAt(x - 1, z));
        e2 = new Vector3f(0f, 1f, vz.getPointAt(x, z + 1) - vz.getPointAt(x, z - 1));


        n = e1.cross(e2);
        n.normalize();

        return n;
    }

    public void render(BufferedRenderer br, VisibleZone vz) {
        Zone zone;
        for (int vzy = 0; vzy < vz.getWtrd(); vzy++)
            for (int vzx = 0; vzx < vz.getWtrd(); vzx++) {
                zone = vz.getZone(vzx, vzy);
                for (int z = 0; z < Zone.HEIGHT - 1; z++)
                    for (int x = 0; x < Zone.WIDTH - 1; x++) {

                        int offsetX = zone.getOffsetX() * (Zone.WIDTH - 1);
                        int offsetZ = zone.getOffsetY() * (Zone.HEIGHT - 1);

                        br.addVertex3f(x + offsetX, zone.getPointAt(x, z), z + offsetZ);
                        br.addVertex3f(x + offsetX, zone.getPointAt(x, z + 1), z + 1 + offsetZ);
                        br.addVertex3f(x + 1 + offsetX, zone.getPointAt(x + 1, z), z + offsetZ);

                        br.addVertex3f(x + offsetX, zone.getPointAt(x, z + 1), z + 1 + offsetZ);
                        br.addVertex3f(x + 1 + offsetX, zone.getPointAt(x + 1, z + 1), z + 1 + offsetZ);
                        br.addVertex3f(x + 1 + offsetX, zone.getPointAt(x + 1, z), z + offsetZ);

                        float x1 = (float) x / Zone.WIDTH;
                        float x2 = (float) (x + 1) / Zone.WIDTH;

                        float z1 = (float) z / Zone.HEIGHT;
                        float z2 = (float) (z + 1) / Zone.HEIGHT;

                        br.addTexCoord2f(x1, z1);
                        br.addTexCoord2f(x1, z2);
                        br.addTexCoord2f(x2, z1);

                        br.addTexCoord2f(x1, z2);
                        br.addTexCoord2f(x2, z2);
                        br.addTexCoord2f(x2, z1);

                        Vector3f n;

                        offsetX = vzx * (Zone.WIDTH - 1);
                        offsetZ = vzy * (Zone.HEIGHT - 1);

                        n = getNormal(x + offsetX, z + offsetZ, vz);
                        br.addNormal3f(n.x, n.y, n.z);
                        n = getNormal(x + offsetX, z + 1 + offsetZ, vz);
                        br.addNormal3f(n.x, n.y, n.z);
                        n = getNormal(x + 1 + offsetX, z + offsetZ, vz);
                        br.addNormal3f(n.x, n.y, n.z);

                        n = getNormal(x + offsetX, z + 1 + offsetZ, vz);
                        br.addNormal3f(n.x, n.y, n.z);
                        n = getNormal(x + 1 + offsetX, z + 1 + offsetZ, vz);
                        br.addNormal3f(n.x, n.y, n.z);
                        n = getNormal(x + 1 + offsetX, z + offsetZ, vz);
                        br.addNormal3f(n.x, n.y, n.z);
                    }
            }
    }
}
