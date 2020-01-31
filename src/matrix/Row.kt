package matrix

class Row (vararg val numbers: Double): Iterator<Double> {
    val size = numbers.size

    private var currentIndex = 0

    override fun hasNext(): Boolean {
        if (currentIndex < numbers.size) {
            return true
        }
        return false
    }

    override fun next(): Double {
        return numbers[currentIndex++]
    }

    operator fun get(i: Int): Double {
        return numbers[i]
    }
}