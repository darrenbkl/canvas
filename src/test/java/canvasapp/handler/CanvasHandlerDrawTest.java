package canvasapp.handler;

import canvasapp.AbstractBaseTest;
import canvasapp.Canvas;
import canvasapp.context.DrawingContext;
import canvasapp.handler.Handler;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CanvasHandlerDrawTest extends AbstractBaseTest {

    @Test
    public void givenValidInput_whenDraw_shouldReturnCanvasString() {

        String[] input = {"L", "3", "1", "3", "5"};

        Canvas canvas = new Canvas(5, 5);
        Handler handler = new Handler(new DrawingContext(canvas));

        String actual = handler.draw(input);

        StringBuilder sb = new StringBuilder();
        sb.append("-------");sb.append(System.getProperty("line.separator"));
        sb.append("|  x  |");sb.append(System.getProperty("line.separator"));
        sb.append("|  x  |");sb.append(System.getProperty("line.separator"));
        sb.append("|  x  |");sb.append(System.getProperty("line.separator"));
        sb.append("|  x  |");sb.append(System.getProperty("line.separator"));
        sb.append("|  x  |");sb.append(System.getProperty("line.separator"));
        sb.append("-------");sb.append(System.getProperty("line.separator"));
        String expected = sb.toString();

        assertThat(actual, is(expected));
    }

    @Test
    public void givenEmptyCanvas_whenDraw_shouldThrowException() {

        exceptionRule.expect(IllegalStateException.class);
        exceptionRule.expectMessage("Canvas must be created");

        String[] input = {"L", "1", "1", "5", "1"};

        Canvas canvas = null;
        Handler handler = new Handler(new DrawingContext(canvas));

        handler.draw(input);
    }

    @Test
    public void givenInvalidInputLength_whenDraw_shouldThrowException() {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Input length should be 5");

        String[] input = {"L", "1", "1", "5", "1", "1"};

        Canvas canvas = new Canvas(5, 5);
        Handler handler = new Handler(new DrawingContext(canvas));

        handler.draw(input);
    }

    @Test
    public void givenInvalidInputType_whenDraw_shouldThrowException() {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Invalid integer");

        String[] input = {"L", "1", "1", "5", "0.0"};

        Canvas canvas = new Canvas(5, 5);
        Handler handler = new Handler(new DrawingContext(canvas));

        handler.draw(input);
    }
}