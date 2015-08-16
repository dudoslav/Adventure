package sk.dudoslav.adventure.engine;

import sk.dudoslav.adventure.engine.graphics.Image;

/**
 * Created by dusan on 14.08.2015.
 */
public class Resources {
    private Image rock, grass, dirt_grass;

    public void loadResources(){
        try {
            rock = new Image("jpg","res/tex/rock.jpg");
            grass = new Image("jpg","res/tex/grass.jpg");
            dirt_grass = new Image("jpg","res/tex/dirt_grass.jpg");
        } catch (Exception e) {
            System.out.println("Failed to load resources!");
            e.printStackTrace();
        }
    }

    public Image getRock() {
        return rock;
    }

    public Image getGrass() {
        return grass;
    }

    public Image getDirt_grass() {
        return dirt_grass;
    }
}
