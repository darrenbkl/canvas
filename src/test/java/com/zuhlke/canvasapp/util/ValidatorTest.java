package com.zuhlke.canvasapp.util;

import com.zuhlke.canvasapp.AbstractBaseTest;
import com.zuhlke.canvasapp.drawable.Canvas;
import com.zuhlke.canvasapp.exception.IllegalCanvasStateException;
import com.zuhlke.canvasapp.exception.InvalidInputFormat;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ValidatorTest extends AbstractBaseTest {

    @Test
    public void givenNull_whenParseInt_shouldThrowException() {
        exceptionRule.expect(InvalidInputFormat.class);
        exceptionRule.expectMessage("Invalid integer");

        Validator.parseStringToType(null, int.class);
    }

    @Test
    public void givenInvalidInteger_whenParseInt_shouldThrowException() {
        exceptionRule.expect(InvalidInputFormat.class);
        exceptionRule.expectMessage("Invalid integer");

        String arg = "c";
        Validator.parseStringToType(arg, int.class);
    }

    @Test
    public void givenValidInteger_whenParseInt_shouldReturnResult() {
        String arg = "420";
        int actual = 420;

        Object result = Validator.parseStringToType(arg, int.class);

        assertThat(result, instanceOf(int.class));
        assertThat(result, is(actual));
    }

    @Test
    public void givenNull_whenParseChar_shouldThrowException() {
        exceptionRule.expect(InvalidInputFormat.class);
        exceptionRule.expectMessage("Invalid character");

        Validator.parseStringToType(null, char.class);
    }

    @Test
    public void givenMultipleChar_whenParseChar_shouldThrowException() {
        exceptionRule.expect(InvalidInputFormat.class);
        exceptionRule.expectMessage("Invalid character");

        String arg = "aa";

        Validator.parseStringToType(arg, char.class);
    }

    @Test
    public void givenInteger_whenParseChar_shouldThrowException() {
        exceptionRule.expect(InvalidInputFormat.class);
        exceptionRule.expectMessage("Invalid character");

        String arg = "420";

        Validator.parseStringToType(arg, char.class);
    }

    @Test
    public void givenValidChar_whenParseChar_shouldReturnResult() {
        String arg = "a";
        char actual = 'a';

        Object result = Validator.parseStringToType(arg, char.class);

        assertThat(result, instanceOf(char.class));
        assertThat(result, is(actual));
    }

    @Test
    public void givenNull_whenValidateCanvas_shouldThrowException() {
        exceptionRule.expect(IllegalCanvasStateException.class);
        exceptionRule.expectMessage("Canvas must be created");

        Validator.validateCanvas(null);
    }

    @Test
    public void givenCanvas_whenValidateCanvas_shouldNotThrowException() {
        Canvas canvas = new Canvas(1, 1);
        Validator.validateCanvas(canvas);
    }
}