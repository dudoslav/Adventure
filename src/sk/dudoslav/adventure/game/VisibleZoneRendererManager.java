package sk.dudoslav.adventure.game;

import sk.dudoslav.adventure.engine.BufferedRenderer;
import sk.dudoslav.adventure.game.renderers.VisibleZoneRenderer;

/**
 * Created by dusan on 16.08.2015.
 */
public class VisibleZoneRendererManager {

    private boolean fresh = true;
    private boolean dirty, updating;

    private BufferedRenderer br = new BufferedRenderer();
    private VisibleZoneRenderer vzr = new VisibleZoneRenderer();

    public void updateVBO(VisibleZone vz){
        System.out.println("updating");

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
            long t1 = System.currentTimeMillis();
            br.uploadToGPU();
            dirty = false;
            System.out.println("GPUuploadTime = " + (System.currentTimeMillis()-t1));
        }

        br.draw();
    }

    //TODO: DISPOSE!!

}
