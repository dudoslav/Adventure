package sk.dudoslav.adventure.game.rendering;

import sk.dudoslav.adventure.engine.graphics.BufferedRenderer;
import sk.dudoslav.adventure.game.renderers.VisibleZoneRenderer;

/**
 * Created by dusan on 16.08.2015.
 */
public class VisibleZoneRendererManager {

    private boolean fresh = true;
    private boolean dirty, updating;

    private BufferedRenderer bufferedRenderer = new BufferedRenderer();
    private VisibleZoneRenderer visibleZoneRenderer = new VisibleZoneRenderer();

    public void updateVBO(VisibleZone vz){
        if(!updating){
            updating = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    bufferedRenderer.reset();
                    visibleZoneRenderer.render(bufferedRenderer, vz);
                    bufferedRenderer.flipBuffers();

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
            bufferedRenderer.uploadToGPU();
            dirty = false;
        }

        bufferedRenderer.draw();
    }

    public void dispose(){
        bufferedRenderer.dispose();
    }
}
