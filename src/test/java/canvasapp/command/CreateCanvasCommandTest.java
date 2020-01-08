package canvasapp.command;

import canvasapp.AbstractBaseTest;
import canvasapp.drawable.Canvas;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CreateCanvasCommandTest extends AbstractBaseTest {

    @Test
    public void givenValidInput_whenExecute_shouldReturnNewCanvas() {

        Command command = new CreateCanvasCommand(3, 4);

        Canvas currentCanvas = null;
        Canvas result = command.execute(currentCanvas);
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