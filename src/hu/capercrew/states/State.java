package hu.capercrew.states;

import hu.capercrew.DataContainer;

public abstract class State {
    static final DataContainer CONTAINER = new DataContainer();
    protected GameStateManager gsm;

    public State(GameStateManager gsm) {
        this.gsm = gsm;
    }

    protected abstract void handleInput();
    public abstract void update();
    public abstract void render();
    public abstract void dispose();
}
