package sk.dudoslav.adventure;

import sk.dudoslav.adventure.engine.Adventure;

/**
 * Created by dusan on 09.08.2015.
 */
public class Main {
    public Main() {
        SharedLibraryLoader.load();
        Adventure adventure = new Adventure();
        adventure.run();
    }

    public static void main(String[] args) {
        new Main();
    }
}