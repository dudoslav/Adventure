package sk.dudoslav.adventure.game.rendering;

import sk.dudoslav.adventure.engine.graphics.BufferedRenderer;
import sk.dudoslav.adventure.game.renderers.VisibleZoneRenderer;
import sk.dudoslav.adventure.game.rendering.VisibleZone;

/**
 * Created by dusan on 16.08.2015.
 */
public class VisibleZoneRendererManager {

    private boolean fresh = true;
    private boolean dirty, updating;

    private BufferedRenderer br = new BufferedRenderer();
    private VisibleZoneRenderer vzr = new VisibleZoneRenderer();

    public void updateVBO(VisibleZone vz){
        if(!updating){
            updating = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    br.reset();
                    vzr.render(br, vz);
                    br.flipBuffers();

                    dirty = true;
                    updating = false;
                    fresh = false;
                }
            }).start();
        }
    }

    public void renderVBO(){
        if (fresh){
            return;
        }

        if (dirty) {
            br.uploadToGPU();
            dirty = false;
        }

        br.draw();
    }

    public void dispose(){
        br.dispose();
    }
}
