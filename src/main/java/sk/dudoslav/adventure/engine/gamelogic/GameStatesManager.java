package sk.dudoslav.adventure.engine.gamelogic;

import sk.dudoslav.adventure.engine.AdventureContainer;
import sk.dudoslav.adventure.engine.input.Input;
import sk.dudoslav.adventure.game.gamestates.InGameState;

import java.util.ArrayList;

/**
 * Created by dusan on 09.08.2015.
 */
public class GameStatesManager {
    private final AdventureContainer adventureContainer;

    private ArrayList<GameState> gameStates = new ArrayList<>();
    private GameState currentGameState;

    public GameStatesManager(AdventureContainer ac){
        this.adventureContainer = ac;
        addState(new InGameState());
        enterState(InGameState.ID);
    }

    public void tick(){
        currentGameState.update(this, adventureContainer);
        currentGameState.render();
    }

    public void enterState(int ID){
        currentGameState = gameStates.get(ID);
        currentGameState.init(this, adventureContainer);
    }

    public void addState(GameState gs){
        gameStates.add(gs);
    }

    public void dispose(){
        gameStates.forEach(GameState::dispose);
    }

}
