package sk.dudoslav.adventure.engine;

import sk.dudoslav.adventure.engine.input.Input;

/**
 * Created by dusan on 14.08.2015.
 */
public class AdventureContainer {
    private final AdventureProperties adventureProperties;
    private final Input input;
    private final Resources resources;
    private final long windowHandle;

    public AdventureContainer(AdventureProperties p, Input input, Resources r, long w){
        this.adventureProperties = p;
        this.input = input;
        this.resources = r;
        this.windowHandle = w;
    }

    public AdventureProperties getProperties() {
        return adventureProperties;
    }

    public Input getInput() {
        return input;
    }

    public Resources getResources() {
        return resources;
    }

    public long getWindow() {
        return windowHandle;
    }

}
