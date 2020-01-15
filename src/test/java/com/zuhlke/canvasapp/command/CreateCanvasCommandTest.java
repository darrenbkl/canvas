package com.zuhlke.canvasapp.command;

import com.zuhlke.canvasapp.AbstractBaseTest;
import com.zuhlke.canvasapp.CanvasApplication;
import com.zuhlke.canvasapp.drawable.Canvas;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CreateCanvasCommandTest extends AbstractBaseTest {

    @Test
    public void givenValidInput_whenExecute_shouldReturnNewCanvas() {

        Command command = new CreateCanvasCommand();
        command.setContext(new CanvasApplication(null, false));
        command.setParameters("3", "4");

        Canvas result = command.execute();
        String actual = result.toString();

        StringBuilder sb = new StringBuilder();
        sb.append("-----\n");
        sb.append("|   |\n");
        sb.append("|   |\n");
        sb.append("|   |\n");
        sb.append("|   |\n");
        sb.append("-----\n");
        String expected = sb.toString();

        assertThat(actual, is(expected));
    }

}