/**
String Calculator
1. Create a simple String calculator with a method: int Add(string numbers)
a. The numbers in the string are separated by a comma.
b. Empty strings should return 0.
c. The return type should be an integer.
d. Example input: “1,2,5” - expected result: “8”.
e. Write tests to prove your input validates.
2. Change the Add method to handle new lines in the input format
a. Example: “1\n,2,3” - Result: “6”
b. Example 2: “1,\n2,4” - Result: “7”
3. 3. Support a custom delimiter
a. The beginning of your string will now contain a small control code that lets you
set a custom delimiter.
b. Format: “//[delimiter]\n[delimiter separated numbers]”
c. Example: “//;\n1;3;4” - Result: 8
d. In the above you can see that following the double forward slash we set a
semicolon, followed by a new line. We then use that delimiter to split our
numbers.
e. e. Other examples
i. “//$\n1$2$3” - Result: 6
ii. “//@\n2@3@8” - Result: 13

4. Calling add with a negative number should throw an exception: “Negatives not allowed”.
The exception should list the number(s) that caused the exception
 **/

fun main() {
    val result = StringCalculator().add("1,3,5")
//    val result = StringCalculator().add("//$,;\n1;3$5")
    println(result)
}

class StringCalculator {

    /**
     * add numbers separated by delimiter
     * return sum of numbers
     * Throws Exception if numbers contains negative number(s)
     */
    fun add(numbers: String): Int {
        val inputString = numbers.trim()
        if (inputString.isEmpty()) return 0

        val numbersInInt = when (inputString.startsWith(DELIMITER_START)) {
            true -> {
                val value = separateDelimiterFromNumbers(inputString.substring(DELIMITER_START.length))
                val numbersString = value.second
                val delimiter = value.first
                getNumbersIntList(numbersString, delimiter)
            }
            false -> getNumbersIntList(inputString)
        }

        return addNumbers(numbersInInt)
    }

    /**Separates the number string into custom delimiter and numbers
     * return Pair of custom delimiter and numbers
     **/

    private fun separateDelimiterFromNumbers(value: String): Pair<String, String> {
        //Use new line character to split the string into 2 parts: delimiter and numbers
        val separatedDelimiterNumbers = value.split(NEW_LINE, limit = 2)
        return Pair(separatedDelimiterNumbers[0], separatedDelimiterNumbers[1])
    }

    /** Separate number string using delimiter
     * return list of valid numbers
     * Throws negative number exception, if input number list has negative numbers
    **/
    private fun getNumbersIntList(numbers: String, delimiter: String = DEFAULT_DELIMITER): List<Int> {
        val stringNumbers = removeNewLine(numbers)
        val negativeNumbers = ArrayList<Int>()
        val delimiterRegex = delimiter.toCharArray()
        val numbersInString: List<String> = stringNumbers.split(*delimiterRegex) //we might get some strings as empty. Assuming empty string to be 0.
        val numbersInInt = ArrayList<Int>(numbersInString.size)
        for (number in numbersInString) {
            val numberInString = number.trim()
            val numberInInt = if (numberInString.isEmpty()) ZERO else numberInString.toInt()
            if (numberInInt < ZERO) {
                negativeNumbers.add(numberInInt)
            }
            if (numberInInt <= MAX_NUMBER_ALLOWED) {
                numbersInInt.add(numberInInt)
            }
        }
        if (negativeNumbers.isNotEmpty()) {
            throw Exception(
                "$NEGATIVE_NUMBER_ERROR_MESSAGE ${negativeNumbers.joinToString(NEGATIVE_NUMBER_SEPARATOR)}"
            )
        }
        return numbersInInt
    }

    /**
     * Remove new line characters from number string
     */
    private fun removeNewLine(stringValue: String): String {
        return stringValue.replace(NEW_LINE, EMPTY_STRING)
    }

    /**
     * Return sum of numbers
     */
    private fun addNumbers(numbers: List<Int>): Int {
        var sum = 0
        for (number in numbers) {
            sum += number
        }
        return sum
    }

    companion object {
        private const val MAX_NUMBER_ALLOWED = 1000
        private const val ZERO = 0
        private const val DELIMITER_START = "//"
        private const val DEFAULT_DELIMITER = ","
        private const val NEW_LINE = "\n"
        private const val EMPTY_STRING = ""
        private const val NEGATIVE_NUMBER_SEPARATOR = ", "
        private const val NEGATIVE_NUMBER_ERROR_MESSAGE = "Negatives not allowed. Negative number(s) in input:"
    }
}