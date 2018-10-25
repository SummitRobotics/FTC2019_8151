package actions;

public abstract class ActionTemplate {
    public abstract boolean isDone;

    public abstract void init();
    public abstract void run();
}
