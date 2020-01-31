package matrix

import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.pow

class Matrix(val sizeI: Int, val sizeJ: Int, initValue: Double = 1.0) {

    companion object {
        private const val PRINT_FORMAT_LENGTH = 8
        private const val PRINT_FORMAT_AFTER_DOT = 3

        fun empty() = Matrix(0, 0)

        fun getNormalMatrix(sizeI: Int, sizeJ: Int, nodesCount: Int): Matrix {
            val result = Matrix(sizeI, sizeJ)
            result.forEach {
                Random().nextGaussian() * nodesCount.toDouble().pow(-0.5)
            }
            return result
        }

        fun dot(m1: Matrix, m2: Matrix): Matrix {
            if (m1.sizeJ == m2.sizeI) {
                val result = Matrix(m1.sizeI, m2.sizeJ)
                for (i in 0 until m1.sizeI) {
                    for (j in 0 until m2.sizeJ) {
                        var sum = 0.0
                        for (adder in 0 until m1.sizeJ) {
                            val s1 = m1[i, adder]
                            val s2 = m2[adder, j]

                            sum += s1 * s2
                        }
                        result[i, j] = sum
                    }
                }
                return result
            } else {
                throw ArithmeticException("Not appropriate matrices sizes! Check you input!")
            }
        }
    }

    constructor(vararg rows: Row) : this(rows.size, rows[0].size) {
        for (i in 0 until sizeI) {
            for (j in 0 until sizeJ) {
                this[i, j] = rows[i][j]
            }
        }
    }

    constructor(value: Matrix) : this(value.sizeI, value.sizeJ) {
        for (i in 0 until sizeI) {
            for (j in 0 until sizeJ) {
                this[i, j] = value[i, j]
            }
        }
    }

    constructor(sizeI: Int, sizeJ: Int, vararg elements: Double) : this(sizeI, sizeJ) {
        for (i in 0 until sizeI) {
            for (j in 0 until sizeJ) {
                this[i, j] = elements[i * sizeJ + j]
            }
        }
    }

    private val matrix: ArrayList<Double> = arrayListOf()
    val T: Matrix
        get() = (!this)

    init {
        for (i in 0 until sizeI * sizeJ) {
            matrix.add(initValue)
        }
    }

    fun print() {
        println(toString())
    }

    fun forEach(function: (Double) -> Double): Matrix {
        for (i in 0 until sizeI) {
            for (j in 0 until sizeJ) {
                this[i, j] = function(get(i, j))
            }
        }
        return this
    }

    fun dot(value: Matrix): Matrix {
        return Matrix.dot(this, value)
    }

    operator fun set(i: Int, j: Int, value: Double) {
        matrix[i * sizeJ + j] = value
    }

    operator fun get(i: Int, j: Int): Double {
        return matrix[i * sizeJ + j]
    }

    operator fun plus(value: Matrix): Matrix {
        val result = Matrix(sizeI, sizeJ)
        for (i in 0 until sizeI) {
            for (j in 0 until sizeJ) {
                result[i,j] = this[i,j] + value[i,j]
            }
        }
        return result
    }

    operator fun minus(value: Matrix): Matrix {
        val result = Matrix(sizeI, sizeJ)
        for (i in 0 until sizeI) {
            for (j in 0 until sizeJ) {
                result[i, j] = this[i, j] - value[i, j]
            }
        }
        return result
    }

    operator fun times(value: Matrix): Matrix {
        val result = Matrix(sizeI, sizeJ)
        for (i in 0 until sizeI) {
            for (j in 0 until sizeJ) {
                result[i,j] = this[i,j] * value[i,j]
            }
        }
        return result
    }

    operator fun div(value: Matrix): Matrix {
        val result = Matrix(sizeI, sizeJ)
        for (i in 0 until sizeI) {
            for (j in 0 until sizeJ) {
                result[i,j] = this[i,j] / value[i,j]
            }
        }
        return result
    }

    operator fun not(): Matrix {
        val result = Matrix(sizeJ, sizeI)
        for (i in 0 until sizeI) {
            for (j in 0 until sizeJ) {
                result[j, i] = this[i, j]
            }
        }
        return result
    }

    override fun toString(): String {
        val result = StringBuilder()
        for (i in 0 until sizeI) {
            for (j in 0 until sizeJ) {
                result.append(
                    String.format(
                        "%${Matrix.Companion.PRINT_FORMAT_LENGTH}.${Matrix.Companion.PRINT_FORMAT_AFTER_DOT}f ",
                        this[i, j]
                    )
                )
            }
            result.append("\n")
        }
        return result.toString()
    }

}


