package canvasapp.handler;

import canvasapp.AbstractBaseTest;
import canvasapp.Canvas;
import canvasapp.context.DrawingContext;
import canvasapp.exception.IllegalCanvasStateException;
import canvasapp.exception.InvalidInputFormat;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CanvasHandlerFillTest extends AbstractBaseTest {

    @Test
    public void givenValidInput_whenPaint_shouldReturnCanvasString() {

        String[] input = {"B", "1", "1", "o"};

        Canvas canvas = prepareCanvas();
        Handler handler = new Handler(new DrawingContext(canvas));
        String actual = handler.fill(input);

        StringBuilder sb = new StringBuilder();
        sb.append("-------\n");
        sb.append("|oox  |\n");
        sb.append("|oox  |\n");
        sb.append("|oox  |\n");
        sb.append("|oox  |\n");
        sb.append("|oox  |\n");
        sb.append("-------\n");
        String expected = sb.toString();

        assertThat(actual, is(expected));
    }

    @Test
    public void givenEmptyCanvas_whenPaint_shouldThrowException() {

        exceptionRule.expect(IllegalCanvasStateException.class);
        exceptionRule.expectMessage("Canvas must be created");

        String[] input = {"B", "1", "1", "o"};

        Handler handler = new Handler(new DrawingContext(null));
        handler.fill(input);
    }

    @Test
    public void givenInvalidInputLength_whenPaint_shouldThrowException() {

        exceptionRule.expect(InvalidInputFormat.class);
        exceptionRule.expectMessage("Input length should be 4");

        String[] input = {"B", "1", "1"};

        Canvas canvas = prepareCanvas();
        Handler handler = new Handler(new DrawingContext(canvas));
        handler.fill(input);
    }

    @Test
    public void givenInvalidInputType_whenPaint_shouldThrowException() {

        exceptionRule.expect(InvalidInputFormat.class);
        exceptionRule.expectMessage("Invalid integer");

        String[] input = {"B", "1", "x", "o"};

        Canvas canvas = prepareCanvas();
        Handler handler = new Handler(new DrawingContext(canvas));
        handler.fill(input);
    }

    @Test
    public void givenInvalidCharacter_whenPaint_shouldThrowException() {

        exceptionRule.expect(InvalidInputFormat.class);
        exceptionRule.expectMessage("Color must be a single character");

        String[] input = {"B", "1", "1", "oo"};

        Canvas canvas = prepareCanvas();
        Handler handler = new Handler(new DrawingContext(canvas));

        handler.fill(input);
    }

    private Canvas prepareCanvas() {

        Canvas canvas = new Canvas(5, 5);
        return canvas.drawLine(2, 0, 2, 4, 'x');
    }
}