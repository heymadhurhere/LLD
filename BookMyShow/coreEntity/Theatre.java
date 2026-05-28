package BookMyShow.coreEntity;

import BookMyShow.coreEntity.Screen;

import java.util.*;

public class Theatre {
    private final String id;
    private final String name;
    private final List<Screen> screens;

    public Theatre(String id, String name) {
        this.id = id;
        this.name = name;
        this.screens = new ArrayList<>(); 
    }

    public void addScreen(Screen screen) {
        screens.add(screen);
    }

    public String getId() {
        return id;
    }
}
