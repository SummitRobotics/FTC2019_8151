package actions;

public abstract class ActionTemplate {
    public boolean isDone;

    public abstract void init();
    public abstract void run();
}
