package canvasapp.handler;

import canvasapp.AbstractBaseTest;
import canvasapp.context.DrawingContext;
import canvasapp.exception.InvalidInputFormat;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CanvasHandlerCreateTest extends AbstractBaseTest {

    @Test
    public void givenValidInput_whenCreate_shouldReturnCanvasString() {

        String[] input = {"C", "3", "4"};

        Handler handler = new Handler(new DrawingContext());
        String actual = handler.create(input);

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

    @Test
    public void givenInvalidInputLength_whenCreate_shouldThrowException() {

        exceptionRule.expect(InvalidInputFormat.class);
        exceptionRule.expectMessage("Input length should be 3");

        String[] input = {"C", "5"};

        Handler handler = new Handler(new DrawingContext());

        handler.create(input);
    }

    @Test
    public void givenInvalidInputType_whenCreate_shouldThrowException() {

        exceptionRule.expect(InvalidInputFormat.class);
        exceptionRule.expectMessage("Invalid integer");

        String[] input = {"C", "5", "X"};

        Handler handler = new Handler(new DrawingContext());

        handler.create(input);
    }
}