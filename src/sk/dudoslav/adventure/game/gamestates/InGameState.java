package sk.dudoslav.adventure.game.gamestates;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.EXTTextureFilterAnisotropic;
import sk.dudoslav.adventure.engine.*;
import sk.dudoslav.adventure.game.Player;
import sk.dudoslav.adventure.game.VisibleZoneManager;
import sk.dudoslav.adventure.game.World;
import static org.lwjgl.glfw.GLFW.*;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

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

    //private BufferedRenderer br = new BufferedRenderer();
    private Light l = new Light(0);
    private Material m = new Material();
    private TextureManager tm = new TextureManager(3);
    private ShaderManager sm = new ShaderManager();

    private Player p = new Player();

    private VisibleZoneManager vzm = new VisibleZoneManager();
    private World w = new World();

    private void initLighting(){

        l.createDirectionalLight();

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();

        glEnable(GL_NORMALIZE);
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);

        l.enable();
        m.apply();

        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
    }

    private void uploadTexturesToGPU(AdventureContainer ac){

        tm.activateTexture(0);
        tm.bindTexture(0);
        tm.loadTexture(0, ac.getResources().getGrass());

        tm.activateTexture(1);
        tm.bindTexture(1);
        tm.loadTexture(1,ac.getResources().getDirt_grass());

        tm.activateTexture(2);
        tm.bindTexture(2);
        tm.loadTexture(2,ac.getResources().getRock());
    }

    @Override
    public void init(GameStatesManager gsm, AdventureContainer ac) {
        AdventureProperties ap = ac.getProperties();
        width  = ap.getWidth();
        height = ap.getHeight();

        float maxAnti = glGetFloat(EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT);
        glTexParameterf(GL_TEXTURE_2D, EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT, maxAnti);

        initLighting();
        uploadTexturesToGPU(ac);

        try {
            sm.loadVertexShader("multiText.vert");
            sm.loadFragmentShader("multiText.frag");
            sm.createShaderProgram();
            sm.useProgram();
        } catch (Exception e){
            e.printStackTrace();
        }

        glUniform1i(glGetUniformLocation(sm.getSp(),"grass"),0);
        glUniform1i(glGetUniformLocation(sm.getSp(),"dirt") ,1);
        glUniform1i(glGetUniformLocation(sm.getSp(),"rock") ,2);

    }

    @Override
    public void update(GameStatesManager gsm, AdventureContainer ac) {
        Input i = ac.getInput();
        if(i.isKeyDown(GLFW_KEY_UP))    l.addPosDir(0f,0f,0.2f);
        if(i.isKeyDown(GLFW_KEY_DOWN))  l.addPosDir(0f,0f,-0.2f);
        if(i.isKeyDown(GLFW_KEY_LEFT))  l.addPosDir(0.2f,0f,0f);
        if(i.isKeyDown(GLFW_KEY_RIGHT)) l.addPosDir(-0.2f,0f,0f);
        p.update(i);
        vzm.update(p, w);
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

        l.updatePosDir();

        vzm.render();
    }

    @Override
    public void dispose() {
    }
}
