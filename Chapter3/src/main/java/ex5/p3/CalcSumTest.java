package ex5.p3;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by jin on 2016. 2. 3..
 */
public class CalcSumTest {
    Calculator calculator;
    String numFilepath;

    @Before
    public void setUp() {
        this.calculator = new Calculator();
        this.numFilepath = getClass().getResource("numbers.txt").getPath();
    }

    @Test
    public void sumOfNumbers() throws IOException {
        assertThat(calculator.calcSum(this.numFilepath), is(10));
    }

    @Test
    public void multiplyOfNumbers() throws IOException {
        assertThat(calculator.calcMultiply(this.numFilepath), is(24));
    }

    public static void main(String[] args) {
        JUnitCore.main("ex5.p3.CalcSumTest"); //JUnit을 이용해 테스트를 실행해주는 main()메서드

    }
}
