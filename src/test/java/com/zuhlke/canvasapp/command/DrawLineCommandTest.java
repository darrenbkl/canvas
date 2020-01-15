package com.zuhlke.canvasapp.command;

import com.zuhlke.canvasapp.AbstractBaseTest;
import com.zuhlke.canvasapp.CanvasApplication;
import com.zuhlke.canvasapp.drawable.Canvas;
import com.zuhlke.canvasapp.exception.IllegalCanvasStateException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DrawLineCommandTest extends AbstractBaseTest {

    @Test
    public void givenNullCanvas_whenExecute_shouldThrowException() {

        exceptionRule.expect(IllegalCanvasStateException.class);
        exceptionRule.expectMessage("Canvas must be created");

        Command command = new DrawLineCommand();
        command.setContext(new CanvasApplication(null, false));
        command.setParameters("3", "1", "3", "5");
        command.execute();
    }

    @Test
    public void givenValidInput_whenExecuteLineDrawable_shouldReturnUpdatedCanvas() {

        Command command = new DrawLineCommand();
        Canvas currentCanvas = new Canvas(5, 5);
        command.setContext(new CanvasApplication(currentCanvas, false));
        command.setParameters("3", "1", "3", "5");

        Canvas result = command.execute();
        String actual = result.toString();

        StringBuilder sb = new StringBuilder();
        sb.append("-------\n");
        sb.append("|  x  |\n");
        sb.append("|  x  |\n");
        sb.append("|  x  |\n");
        sb.append("|  x  |\n");
        sb.append("|  x  |\n");
        sb.append("-------\n");
        String expected = sb.toString();

        assertThat(actual, is(expected));
    }
}