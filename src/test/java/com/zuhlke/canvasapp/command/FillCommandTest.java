package com.zuhlke.canvasapp.command;

import com.zuhlke.canvasapp.AbstractBaseTest;
import com.zuhlke.canvasapp.CanvasApplication;
import com.zuhlke.canvasapp.drawable.Canvas;
import com.zuhlke.canvasapp.exception.IllegalCanvasStateException;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FillCommandTest extends AbstractBaseTest {

    @Test
    public void givenNullCanvas_whenExecute_shouldThrowException() {

        exceptionRule.expect(IllegalCanvasStateException.class);
        exceptionRule.expectMessage("Canvas must be created");

        Command command = new FillCommand();
        command.setContext(new CanvasApplication(null, false));
        command.setParameters("1", "1", "o");
        command.execute();
    }

    @Test
    public void givenValidInput_whenExecute_shouldReturnUpdatedCanvas() {

        Command command = new FillCommand();
        Canvas currentCanvas = new Canvas(3, 3);
        command.setContext(new CanvasApplication(currentCanvas, false));
        command.setParameters("1", "1", "o");

        Canvas result = command.execute();
        String actual = result.toString();

        StringBuilder sb = new StringBuilder();
        sb.append("-----\n");
        sb.append("|ooo|\n");
        sb.append("|ooo|\n");
        sb.append("|ooo|\n");
        sb.append("-----\n");
        String expected = sb.toString();

        assertThat(actual, is(expected));
    }
}