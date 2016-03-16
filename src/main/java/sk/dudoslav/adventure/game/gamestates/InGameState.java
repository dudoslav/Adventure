package sk.dudoslav.adventure.game.gamestates;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.EXTTextureFilterAnisotropic;
import sk.dudoslav.adventure.engine.*;
import sk.dudoslav.adventure.engine.gamelogic.GameState;
import sk.dudoslav.adventure.engine.gamelogic.GameStatesManager;
import sk.dudoslav.adventure.engine.graphics.Light;
import sk.dudoslav.adventure.engine.graphics.Material;
import sk.dudoslav.adventure.engine.graphics.ShaderManager;
import sk.dudoslav.adventure.engine.graphics.TextureManager;
import sk.dudoslav.adventure.engine.input.Input;
import sk.dudoslav.adventure.game.player.LocalPlayerController;
import sk.dudoslav.adventure.game.player.Player;
import sk.dudoslav.adventure.game.rendering.VisibleZoneManager;
import sk.dudoslav.adventure.game.world.World;

import static org.lwjgl.glfw.GLFW.*;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

/**
 * Created by dusan on 09.08.2015.
 */
public class InGameState implements GameState {
    public static final int ID = 0;

    private int width;
    private int height;

    private Matrix4f projectionMatrix = new Matrix4f(); //ProjectionMatrix
    private Matrix4f viewMatrix = new Matrix4f(); //ViewMatrix

    private FloatBuffer tempMatrixBuffer = BufferUtils.createFloatBuffer(16);

    private Light light = new Light(0);
    private Material material = new Material();
    private TextureManager textureManager = new TextureManager(3);
    private ShaderManager shaderManager = new ShaderManager();

    private Player player = new Player();
    private LocalPlayerController localPlayerController;

    private VisibleZoneManager visibleZoneManager;
    private World w = new World();

    private void initLighting(){

        light.createDirectionalLight();

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();

        glEnable(GL_NORMALIZE);
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);

        light.enable();
        material.apply();

        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
    }

    private void uploadTexturesToGPU(AdventureContainer ac){

        textureManager.activateTexture(0);
        textureManager.bindTexture(0);
        textureManager.loadTexture(ac.getResources().getGrass());

        textureManager.activateTexture(1);
        textureManager.bindTexture(1);
        textureManager.loadTexture(ac.getResources().getDirt_grass());

        textureManager.activateTexture(2);
        textureManager.bindTexture(2);
        textureManager.loadTexture(ac.getResources().getRock());
    }

    @Override
    public void init(GameStatesManager gsm, AdventureContainer ac) {
        AdventureProperties ap = ac.getProperties();

        glfwSetInputMode(ac.getWindow(), GLFW_CURSOR, GLFW_CURSOR_DISABLED);

        visibleZoneManager = new VisibleZoneManager(ap);
        localPlayerController = new LocalPlayerController(player,ac.getInput());

        width  = ap.getWidth();
        height = ap.getHeight();

        float maxAnti = glGetFloat(EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT);
        glTexParameterf(GL_TEXTURE_2D, EXTTextureFilterAnisotropic.GL_TEXTURE_MAX_ANISOTROPY_EXT, maxAnti);

        initLighting();
        uploadTexturesToGPU(ac);

        try {
            shaderManager.loadVertexShader("multiText.vert");
            shaderManager.loadFragmentShader("multiText.frag");
            shaderManager.createShaderProgram();
            shaderManager.useProgram();
        } catch (Exception e){
            e.printStackTrace();
        }

        glUniform1i(glGetUniformLocation(shaderManager.getShaderProgramHandle(),"grass"),0);
        glUniform1i(glGetUniformLocation(shaderManager.getShaderProgramHandle(),"dirt") ,1);
        glUniform1i(glGetUniformLocation(shaderManager.getShaderProgramHandle(),"rock") ,2);

    }

    @Override
    public void update(GameStatesManager gsm, AdventureContainer ac) {
        Input i = ac.getInput();
        if(i.isKeyDown(GLFW_KEY_UP))    light.addPosDir(0f,0f,0.2f);
        if(i.isKeyDown(GLFW_KEY_DOWN))  light.addPosDir(0f,0f,-0.2f);
        if(i.isKeyDown(GLFW_KEY_LEFT))  light.addPosDir(0.2f,0f,0f);
        if(i.isKeyDown(GLFW_KEY_RIGHT)) light.addPosDir(-0.2f,0f,0f);
        localPlayerController.update();
        visibleZoneManager.update(player, w);
    }

    @Override
    public void render() {
        projectionMatrix.setPerspective(45.f, (float)width/height, 0.01f, 10000.0f).get(tempMatrixBuffer);
        glMatrixMode(GL_PROJECTION);
        glLoadMatrixf(tempMatrixBuffer);

        viewMatrix.setLookAt(0.0f, 1.0f, 2.0f,
                     0.0f, 0.0f, 0.0f,
                     0.0f, 1.0f, 0.0f).rotate(player.getRx(), 1f, 0f, 0f).rotate(player.getRy(), 0f, 1f, 0f).translate(player.getX(), player.getY(), player.getZ()).get(tempMatrixBuffer);
        glMatrixMode(GL_MODELVIEW);
        glLoadMatrixf(tempMatrixBuffer);

        light.updatePosDir();

        visibleZoneManager.render();
    }

    @Override
    public void dispose() {
        visibleZoneManager.dispose();
    }
}
