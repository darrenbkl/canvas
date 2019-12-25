package canvasapp.handler;

import canvasapp.AbstractBaseTest;
import canvasapp.Canvas;
import canvasapp.context.DrawingContext;
import canvasapp.exception.IllegalCanvasStateException;
import canvasapp.exception.InvalidInputFormat;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CanvasHandlerDrawTest extends AbstractBaseTest {

    @Test
    public void givenValidDrawLineInput_whenDraw_shouldReturnCanvasString() {

        String[] input = {"L", "3", "1", "3", "5"};

        Canvas canvas = new Canvas(5, 5);
        Handler handler = new Handler(new DrawingContext(canvas));
        String actual = handler.draw(input);

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

    @Test
    public void givenValidDrawRectInput_whenDraw_shouldReturnCanvasString() {

        String[] input = {"R", "1", "1", "3", "3"};

        Canvas canvas = new Canvas(5, 5);
        Handler handler = new Handler(new DrawingContext(canvas));
        String actual = handler.draw(input);

        StringBuilder sb = new StringBuilder();
        sb.append("-------\n");
        sb.append("|xxx  |\n");
        sb.append("|x x  |\n");
        sb.append("|xxx  |\n");
        sb.append("|     |\n");
        sb.append("|     |\n");
        sb.append("-------\n");
        String expected = sb.toString();

        assertThat(actual, is(expected));
    }

    @Test
    public void givenInvalidDrawCommand_whenDraw_shouldThrowException() {

        exceptionRule.expect(InvalidInputFormat.class);
        exceptionRule.expectMessage("Invalid command");

        String[] input = {"M", "1", "1", "5", "1"};

        Canvas canvas = new Canvas(5, 5);
        Handler handler = new Handler(new DrawingContext(canvas));
        handler.draw(input);
    }

    @Test
    public void givenEmptyCanvas_whenDraw_shouldThrowException() {

        exceptionRule.expect(IllegalCanvasStateException.class);
        exceptionRule.expectMessage("Canvas must be created");

        String[] input = {"L", "1", "1", "5", "1"};

        Handler handler = new Handler(new DrawingContext(null));
        handler.draw(input);
    }

    @Test
    public void givenInvalidInputLength_whenDraw_shouldThrowException() {

        exceptionRule.expect(InvalidInputFormat.class);
        exceptionRule.expectMessage("Input length should be 5");

        String[] input = {"L", "1", "1", "5", "1", "1"};

        Canvas canvas = new Canvas(5, 5);
        Handler handler = new Handler(new DrawingContext(canvas));

        handler.draw(input);
    }

    @Test
    public void givenInvalidInputType_whenDraw_shouldThrowException() {

        exceptionRule.expect(InvalidInputFormat.class);
        exceptionRule.expectMessage("Invalid integer");

        String[] input = {"L", "1", "1", "5", "0.0"};

        Canvas canvas = new Canvas(5, 5);
        Handler handler = new Handler(new DrawingContext(canvas));

        handler.draw(input);
    }
}