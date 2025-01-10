fun findMissingNumber(array: IntArray, n: Int): Int {
    val expectedSum = (n + 1) * (n + 2) / 2  // Sum of first n+1 natural numbers
    val actualSum = array.sum()
    return expectedSum - actualSum
}

fun main() {
    val input = intArrayOf(3, 7, 1, 2, 6, 4)
    val n = input.size  // n is the size of the array
    val missingNumber = findMissingNumber(input, n)
    println("Missing number: $missingNumber")
}
