package sk.dudoslav.adventure.game.player;

import sk.dudoslav.adventure.engine.input.Input;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by dusan on 16.08.2015.
 */
public class LocalPlayerController extends PlayerController {

    private final Input input;

    public LocalPlayerController(Player player, Input input) {
        super(player);
        this.input = input;
    }

    @Override
    public void update() {
        Input.Mouse mouse = input.getMouse();

        player.ry += mouse.dx/100;
        player.rx += mouse.dy/100;

        if(input.isKeyDown(GLFW_KEY_W)) player.move(0, 0, -Player.SPEED);
        if(input.isKeyDown(GLFW_KEY_S)) player.move(0, 0, Player.SPEED);
        if(input.isKeyDown(GLFW_KEY_A)) player.move(-Player.SPEED, 0, 0);
        if(input.isKeyDown(GLFW_KEY_D)) player.move(Player.SPEED,0,0);
        if(input.isKeyDown(GLFW_KEY_E)) player.move(0,Player.SPEED,0);
        if(input.isKeyDown(GLFW_KEY_Q)) player.move(0,-Player.SPEED,0);
    }
}
