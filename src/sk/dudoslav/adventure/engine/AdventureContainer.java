package sk.dudoslav.adventure.engine;

import sk.dudoslav.adventure.engine.input.Input;

/**
 * Created by dusan on 14.08.2015.
 */
public class AdventureContainer {
    private final AdventureProperties p;
    private final Input i;
    private final Resources r;
    private final long w;

    public AdventureContainer(AdventureProperties p, Input i, Resources r, long w){
        this.p = p;
        this.i = i;
        this.r = r;
        this.w = w;
    }

    public AdventureProperties getProperties() {
        return p;
    }

    public Input getInput() {
        return i;
    }

    public Resources getResources() {
        return r;
    }

    public long getWindow() {
        return w;
    }

}
