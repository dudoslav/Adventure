package sk.dudoslav.adventure.game.gamestates;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import sk.dudoslav.adventure.engine.*;
import sk.dudoslav.adventure.game.Player;
import sk.dudoslav.adventure.game.VisibleZoneManager;
import sk.dudoslav.adventure.game.World;
import sk.dudoslav.adventure.game.renderers.ZoneRenderer;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by dusan on 09.08.2015.
 */
public class InGameState extends GameState {
    public static final int ID = 0;

    private int width;
    private int height;

    private Matrix4f pm = new Matrix4f(); //ProjectionMatrix
    private Matrix4f vm = new Matrix4f(); //ViewMatrix

    private FloatBuffer fb = BufferUtils.createFloatBuffer(16);

    private BufferedRenderer br = new BufferedRenderer();

    private Player p = new Player();

    private VisibleZoneManager vzm = new VisibleZoneManager();
    private World w = new World();

    private void initLighting(){
        FloatBuffer ambientLightColor = BufferUtils.createFloatBuffer(4).put(new float[]{0.0f, 0.1f, 0.0f, 1.0f});
        FloatBuffer specularLight = BufferUtils.createFloatBuffer(4).put(new float[]{1.f, 1.f, 1.f, 1.f});
        FloatBuffer lightColor0 = BufferUtils.createFloatBuffer(4).put(new float[]{0.8f, 0.8f, 0.8f, 1.f});
        FloatBuffer lightPos0 = BufferUtils.createFloatBuffer(4).put(new float[]{0.f, 5.f, 0.f, 1.0f});

        ambientLightColor.flip();
        specularLight.flip();
        lightColor0.flip();
        lightPos0.flip();

        glMatrixMode(GL_PROJECTION);

        glShadeModel(GL_SMOOTH);
        glEnable(GL_NORMALIZE);
        glLightModeli(GL_LIGHT_MODEL_AMBIENT, GL_TRUE);
        glEnable(GL_LIGHTING);
        glEnable(GL_LIGHT0);
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);
        glEnable(GL_COLOR_MATERIAL);
        glLightModelfv(GL_LIGHT_MODEL_AMBIENT, ambientLightColor);
        glLightfv(GL_LIGHT0, GL_AMBIENT, ambientLightColor);
        glLightfv(GL_LIGHT0, GL_SPECULAR, specularLight);
        glLightfv(GL_LIGHT0, GL_DIFFUSE, lightColor0);
        glLightfv(GL_LIGHT0, GL_POSITION, lightPos0);
        glColorMaterial(GL_FRONT, GL_DIFFUSE);

        glLoadIdentity();
        glMatrixMode(GL_MODELVIEW);
    }

    @Override
    public void init(GameStatesManager gsm, AdventureProperties p) {
        width  = p.getWidth();
        height = p.getHeight();

        initLighting();
    }

    @Override
    public void update(GameStatesManager gsm, Input i) {
        p.update(i);
        vzm.update(p,w);
    }

    @Override
    public void render() {
        pm.setPerspective(45.f, (float)width/height, 0.01f, 10000.0f).get(fb);
        glMatrixMode(GL_PROJECTION);
        glLoadMatrixf(fb);

        vm.setLookAt(0.0f, 1.0f, 2.0f,
                     0.0f, 0.0f, 0.0f,
                     0.0f, 1.0f, 0.0f).rotate(p.getRx(), 1f, 0f, 0f).rotate(p.getRy(), 0f, 1f, 0f).translate(p.getX(),p.getY(),p.getZ()).get(fb);
        glMatrixMode(GL_MODELVIEW);
        glLoadMatrixf(fb);

        if(vzm.shouldRender(p)){
            System.out.println("RENDERING!");
            glColor3f(0f, 1f, 0f);
            br.reset();
            vzm.render(br);
            br.uploadToGPU();
        }
        br.draw();
    }

    @Override
    public void dispose() {
        br.dispose();
    }
}
