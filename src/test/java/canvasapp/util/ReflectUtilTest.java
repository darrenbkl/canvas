package canvasapp.util;

import canvasapp.AbstractBaseTest;
import canvasapp.exception.ApplicationError;
import canvasapp.exception.InvalidInputFormat;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.function.BiFunction;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ReflectUtilTest extends AbstractBaseTest {

    @Test
    public void givenClassWithPublicConstructor_whenGetConstructor_shouldReturnConstructor() {
        Constructor<?> constructor = ReflectUtil.getConstructorOfClass(ClassWithPublicConstructor.class);

        boolean assignableFrom = ClassWithPublicConstructor.class.isAssignableFrom(constructor.getDeclaringClass());
        assertThat(assignableFrom, is(true));
    }

    @Test
    public void givenClassWithNoPublicConstructor_whenGetConstructor_shouldThrowException() {
        exceptionRule.expect(ApplicationError.class);
        exceptionRule.expectMessage("Class has no public constructor");

        ReflectUtil.getConstructorOfClass(ClassWithNoPublicConstructor.class);
    }

    @Test
    public void givenValidParameters_whenResolve_shouldReturnResolvedParameters() {
        Class<?>[] parameterTypes = {int.class, char.class};
        String[] parameters = {"1", "x"};
        BiFunction<String, Class<?>, Object> parseFunction = Validator::parseStringToType;

        Object[] actual = ReflectUtil.resolveParameters(parameterTypes, parameters, parseFunction);
        Object[] expected = Arrays.asList(1, 'x').toArray();
        assertThat(actual, is(expected));
    }

    @Test
    public void givenWrongParameters_whenResolve_shouldThrowException() {
        exceptionRule.expect(InvalidInputFormat.class);
        exceptionRule.expectMessage("Invalid parameters");

        Class<?>[] parameterTypes = {int.class, char.class};
        String[] parameters = {"x"};
        BiFunction<String, Class<?>, Object> parseFunction = Validator::parseStringToType;

        ReflectUtil.resolveParameters(parameterTypes, parameters, parseFunction);
    }

    @Test
    public void givenValidParameters_whenCreateNewInstance_shouldReturnNewInstance() {
        Constructor<?> constructor = ClassWithConstructorParameters.class.getConstructors()[0];
        Object[] params = Arrays.asList(1, 'c').toArray();
        Object newInstance = ReflectUtil.createNewInstance(constructor, params);

        assertThat(newInstance, instanceOf(ClassWithConstructorParameters.class));
    }

    @Test
    public void givenInvalidParameters_whenCreateNewInstance_shouldThrowException() {
        exceptionRule.expect(ApplicationError.class);
        exceptionRule.expectMessage("Error creating class instance");

        Constructor<?> constructor = ClassWithConstructorParameters.class.getConstructors()[0];
        Object[] params = Arrays.asList(1).toArray();
        ReflectUtil.createNewInstance(constructor, params);
    }


    private static class ClassWithNoPublicConstructor {
        private ClassWithNoPublicConstructor() {
        }
    }

    private static class ClassWithPublicConstructor {
        public ClassWithPublicConstructor() {
        }
    }

    private static class ClassWithConstructorParameters {
        int i;
        char c;

        public ClassWithConstructorParameters(int i, char c) {
            this.i = i;
            this.c = c;
        }
    }
}