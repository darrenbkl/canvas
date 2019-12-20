package canvasapp.context;

import canvasapp.command.Command;

public interface Context {

    String execute(Command command);

    boolean isInitialized();
}