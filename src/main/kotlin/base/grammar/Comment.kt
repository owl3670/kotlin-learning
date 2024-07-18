package base.grammar

class Comment {

    /**
     * This is a documentation comment (KDoc)
     * @param testStr This is a test string parameter
     * @return testStr value is returned
     */
    fun learnComment(testStr: String): String{
        // This is a single line comment

        /* This is a
        multi-line comment
         */

        println("This is a test string: $testStr")

        return testStr
    }
}