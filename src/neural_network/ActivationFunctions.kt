package neural_network

import kotlin.math.exp

object ActivationFunctions {
    fun sigmoid(x: Double) = 1.0 / (1 + exp(-x))
}