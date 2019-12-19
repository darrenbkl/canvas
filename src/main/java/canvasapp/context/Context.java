package canvasapp.context;

import canvasapp.command.Command;

public interface Context {

    void execute(Command command);
}
