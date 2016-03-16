package sk.dudoslav.adventure.engine.gamelogic;

import sk.dudoslav.adventure.engine.AdventureContainer;

/**
 * Created by dusan on 09.08.2015.
 */
public interface GameState {
    void init(GameStatesManager gsm, AdventureContainer ac);
    void update(GameStatesManager gsm, AdventureContainer ac);
    void render();
    void dispose();

}
