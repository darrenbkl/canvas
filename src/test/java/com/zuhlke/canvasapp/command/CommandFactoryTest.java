package com.zuhlke.canvasapp.command;

import com.zuhlke.canvasapp.AbstractBaseTest;
import com.zuhlke.canvasapp.CanvasApplication;
import com.zuhlke.canvasapp.DrawingContext;
import com.zuhlke.canvasapp.exception.InvalidInputFormat;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public class CommandFactoryTest extends AbstractBaseTest {

    private DrawingContext context;
    private CommandFactory commandFactory;

    @Before
    public void setUp() throws Exception {
        this.context = new CanvasApplication(null, false);
        this.commandFactory = new CommandFactory();
    }

    @Test
    public void givenNullInput_whenCreateCommand_shouldThrowException() {

        exceptionRule.expect(InvalidInputFormat.class);
        exceptionRule.expectMessage("Missing command");

        commandFactory.createCommand(null, context);
    }

    @Test
    public void givenEmptyInput_whenCreateCommand_shouldThrowException() {

        exceptionRule.expect(InvalidInputFormat.class);
        exceptionRule.expectMessage("Missing command");

        String[] args = {};
        commandFactory.createCommand(args, context);
    }

    @Test
    public void givenInvalidCommand_whenCreateCommand_shouldThrowException() {

        exceptionRule.expect(InvalidInputFormat.class);
        exceptionRule.expectMessage("Invalid command");

        String[] args = {"111"};
        commandFactory.createCommand(args, context);
    }

    @Test
    public void givenInvalidParameters_whenCreateCommand_shouldThrowException() {

        exceptionRule.expect(InvalidInputFormat.class);
        exceptionRule.expectMessage("Invalid parameters");

        String[] args = {"C"};
        commandFactory.createCommand(args, context);
    }

    @Test
    public void givenValidInput_whenCreateCommand_shouldReturnCreateCanvasCommand() {
        String[] args = {"C", "10", "10"};
        Command command = commandFactory.createCommand(args, context);
        assertThat(command, instanceOf(CreateCanvasCommand.class));
    }

    @Test
    public void givenValidInput_whenCreateCommand_shouldReturnDrawRectCommand() {
        String[] args = {"R", "1", "1", "5", "5"};
        Command command = commandFactory.createCommand(args, context);
        assertThat(command, instanceOf(DrawRectCommand.class));
    }

    @Test
    public void givenValidInput_whenCreateCommand_shouldReturnDrawLineCommand() {
        String[] args = {"L", "5", "1", "5", "10"};
        Command command = commandFactory.createCommand(args, context);
        assertThat(command, instanceOf(DrawLineCommand.class));
    }

    @Test
    public void givenValidInput_whenCreateCommand_shouldReturnFillCommand() {
        String[] args = {"B", "1", "1", "o"};
        Command command = commandFactory.createCommand(args, context);
        assertThat(command, instanceOf(FillCommand.class));
    }

    @Test
    public void givenValidInput_whenCreateCommand_shouldReturnQuitCommand() {
        String[] args = {"Q"};
        Command command = commandFactory.createCommand(args, context);
        assertThat(command, instanceOf(QuitCommand.class));
    }
}