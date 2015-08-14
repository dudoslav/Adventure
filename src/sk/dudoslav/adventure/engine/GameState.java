package sk.dudoslav.adventure.engine;

/**
 * Created by dusan on 09.08.2015.
 */
public abstract class GameState {
    public abstract void init(GameStatesManager gsm, AdventureContainer ac);
    public abstract void update(GameStatesManager gsm, AdventureContainer ac);
    public abstract void render();
    public abstract void dispose();

}
