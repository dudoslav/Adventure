package sk.dudoslav.adventure.game.player;


/**
 * Created by dusan on 16.08.2015.
 */
public abstract class PlayerController {

    protected Player player;

    public PlayerController(Player player){
        this.player = player;
    }

    public abstract void update();
}
