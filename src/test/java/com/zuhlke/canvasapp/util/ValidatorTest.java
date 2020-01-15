package com.zuhlke.canvasapp.util;

import com.zuhlke.canvasapp.AbstractBaseTest;
import com.zuhlke.canvasapp.drawable.Canvas;
import com.zuhlke.canvasapp.exception.IllegalCanvasStateException;
import com.zuhlke.canvasapp.exception.InvalidInputFormat;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ValidatorTest extends AbstractBaseTest {

    @Test
    public void givenNull_whenParseInt_shouldThrowException() {
        exceptionRule.expect(InvalidInputFormat.class);
        exceptionRule.expectMessage("Invalid integer");

        Validator.parseInt(null);
    }

    @Test
    public void givenInvalidInteger_whenParseInt_shouldThrowException() {
        exceptionRule.expect(InvalidInputFormat.class);
        exceptionRule.expectMessage("Invalid integer");

        String arg = "c";
        Validator.parseInt(arg);
    }

    @Test
    public void givenValidInteger_whenParseInt_shouldReturnResult() {
        String arg = "420";
        int expected = 420;

        int actual = Validator.parseInt(arg);

        assertThat(actual, is(expected));
    }

    @Test
    public void givenNull_whenParseChar_shouldThrowException() {
        exceptionRule.expect(InvalidInputFormat.class);
        exceptionRule.expectMessage("Invalid character");

        Validator.parseChar(null);
    }

    @Test
    public void givenMultipleChar_whenParseChar_shouldThrowException() {
        exceptionRule.expect(InvalidInputFormat.class);
        exceptionRule.expectMessage("Invalid character");

        String arg = "aa";

        Validator.parseChar(arg);
    }

    @Test
    public void givenInteger_whenParseChar_shouldThrowException() {
        exceptionRule.expect(InvalidInputFormat.class);
        exceptionRule.expectMessage("Invalid character");

        String arg = "420";

        Validator.parseChar(arg);
    }

    @Test
    public void givenValidChar_whenParseChar_shouldReturnResult() {
        String arg = "a";
        char expected = 'a';

        char actual = Validator.parseChar(arg);

        assertThat(actual, is(expected));
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

    @Test
    public void givenNull_whenValidateCommandParameters_shouldThrowException() {
        exceptionRule.expect(InvalidInputFormat.class);
        exceptionRule.expectMessage("Invalid parameters");

        String[] args = null;
        int length = 1;

        Validator.validateCommandParameters(args, length);
    }

    @Test
    public void givenMatchingInput_whenValidateCommandParameters_shouldNotThrowException() {
        String[] args = {};
        int length = 0;

        Validator.validateCommandParameters(args, length);
    }

    @Test
    public void givenMisMatchedInput_whenValidateCommandParameters_shouldNotThrowException() {
        exceptionRule.expect(InvalidInputFormat.class);
        exceptionRule.expectMessage("Invalid parameters");

        String[] args = {"1"};
        int length = 0;

        Validator.validateCommandParameters(args, length);
    }
}