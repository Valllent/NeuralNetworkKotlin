package matrix

operator fun Double.plus(matrix: Matrix): Matrix {
    return Matrix(matrix).forEach { this + it }
}

operator fun Double.minus(matrix: Matrix): Matrix {
    return Matrix(matrix).forEach { this - it }
}

operator fun Double.times(matrix: Matrix): Matrix {
    return Matrix(matrix).forEach { this * it }
}

operator fun Double.div(matrix: Matrix): Matrix {
    return Matrix(matrix).forEach { this / it }
}