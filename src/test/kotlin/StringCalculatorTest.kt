import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class StringCalculatorTest {
    private val stringCalculator = StringCalculator()

    @Test
    fun add_NoNumbers_Return0() {
        val result = stringCalculator.add("")
        assertEquals(0,result)

        val resultSpace = stringCalculator.add(" ")
        assertEquals(0,resultSpace)
    }

    @Test
    fun add_1Number_ReturnSum() {
        val result = stringCalculator.add("1")
        assertEquals(1,result)

        val resultSpace = stringCalculator.add(" 1 ")
        assertEquals(1,resultSpace)
    }

    @Test
    fun add_Numbers_ReturnSum() {
        val result = stringCalculator.add("1,2,3")
        assertEquals(6,result)

        val resultSpaces = stringCalculator.add("1,2, 3")
        assertEquals(6,resultSpaces)
    }

    @Test
    fun add_NumbersNewLine_ReturnSum() {
        val result = stringCalculator.add("1,\n2,3")
        assertEquals(6,result)

        val resultSpaces = stringCalculator.add("1,2, 3\n")
        assertEquals(6,resultSpaces)
    }

    @Test
    fun add_CustomDelimiter_ReturnSum() {
        val resultSemiColon = stringCalculator.add("//;\n1;2;3")
        assertEquals(6,resultSemiColon)

        val resultDollar = stringCalculator.add("//$\n1$2$ 3")
        assertEquals(6,resultDollar)

        val result2Delimiter = stringCalculator.add("//$;-\n1$;-2$;- 3")
        assertEquals(6,result2Delimiter)
    }

    @Test
    fun add_CustomDelimiterNewLine_ReturnSum() {
        val result = stringCalculator.add("//;\n1;\n2;3")
        assertEquals(6,result)

        val resultSpaces = stringCalculator.add("//$\n1$2$\n 3")
        assertEquals(6,resultSpaces)
    }

    @Test
    fun add_CustomDelimiterArbitraryLength_ReturnSum() {
        val result2Delimiter = stringCalculator.add("//$;-\n1$;-2$;- 3")
        assertEquals(6,result2Delimiter)
    }

    @Test
    fun add_MultipleCustomDelimiter_ReturnSum() {
        val result2Delimiter = stringCalculator.add("//$,;-\n1$;-2$;- 3,4")
        assertEquals(10,result2Delimiter)
    }

    @Test
    fun add_MultipleCustomDelimiterArbitraryLength_ReturnSum() {
        val result2Delimiter = stringCalculator.add("//$,;-\n1$;-2;- 3,4")
        assertEquals(10,result2Delimiter)
    }
}