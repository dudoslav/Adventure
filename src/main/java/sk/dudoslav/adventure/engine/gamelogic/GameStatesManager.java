package sk.dudoslav.adventure.engine.gamelogic;

import sk.dudoslav.adventure.engine.AdventureContainer;
import sk.dudoslav.adventure.engine.input.Input;
import sk.dudoslav.adventure.game.gamestates.InGameState;

import java.util.ArrayList;

/**
 * Created by dusan on 09.08.2015.
 */
public class GameStatesManager {
    private final AdventureContainer ac;

    private ArrayList<GameState> gss = new ArrayList<>();
    private GameState cgs;

    public GameStatesManager(AdventureContainer ac){
        this.ac = ac;
        addState(new InGameState());
        enterState(InGameState.ID);
    }

    public void tick(Input i){
        cgs.update(this, ac);
        cgs.render();
    }

    public void enterState(int ID){
        cgs = gss.get(ID);
        cgs.init(this, ac);
    }

    public void addState(GameState gs){
        gss.add(gs);
    }

    public void dispose(){
        for (GameState gs : gss){
            gs.dispose();
        }
    }

}
