package sk.dudoslav.adventure.engine;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

/**
 * Created by dusan on 09.08.2015.
 */
public class AdventureProperties{
    private final Properties properties;
    private final File file;

    public AdventureProperties(File f){
        this.file = f;
        this.properties = new Properties();
    }

    public void load() throws Exception {
        properties.load(new FileReader(file));
    }

    private String getString(String key) {
        return properties.getProperty(key);
    }

    private String getString(String key, String def) {
        return properties.getProperty(key, def);
    }

    private Double getDouble(String key) {
        return Double.parseDouble(getString(key));
    }

    private Double getDouble(String key, double def) {
        return Double.parseDouble(getString(key, "" + def));
    }

    private Integer getInt(String key) {
        return Integer.parseInt(getString(key));
    }

    private Integer getInt(String key, int def) {
        return Integer.parseInt(getString(key, "" + def));
    }

    private Boolean getBoolean(String key) {
        return Boolean.parseBoolean(getString(key));
    }

    private Boolean getBoolean(String key, boolean def) {
        return Boolean.parseBoolean(getString(key, Boolean.toString(def)));
    }

    public int getWidth(){
        return getInt("width", 800);
    }

    public int getHeight(){
        return getInt("height",600);
    }

    public boolean isFullScreen(){
        return getBoolean("fullscreen",false);
    }

    public boolean isVSync() {return getBoolean("vsync", false);}

    public int getRenderDistance(){
        return getInt("renderdistance",3);
    }
}
