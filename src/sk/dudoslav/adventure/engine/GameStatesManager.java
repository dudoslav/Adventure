package sk.dudoslav.adventure.engine;

import sk.dudoslav.adventure.game.gamestates.InGameState;

import java.util.ArrayList;

/**
 * Created by dusan on 09.08.2015.
 */
public class GameStatesManager {
    private final AdventureProperties p;

    private ArrayList<GameState> gss = new ArrayList<>();
    private GameState cgs;

    public GameStatesManager(AdventureProperties p){
        this.p = p;
        addState(new InGameState());
        enterState(InGameState.ID);
    }

    public void tick(Input i){
        cgs.update(this, i);
        cgs.render();
    }

    public void enterState(int ID){
        cgs = gss.get(ID);
        cgs.init(this, p);
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
