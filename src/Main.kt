import matrix.Matrix
import neural_network.NeuralNetwork
import matrix.Row as R

fun main() {
    val inputCount = 784
    val hiddenCount = 200
    val outputCount = 10

    val network = NeuralNetwork(3, 5, 3)
    network.query(Matrix(1, 3, 0.35, 0.22, 0.1))
    network.printMatricesWeightVector()
    network.train(
        Matrix(R(0.35, 0.22, 0.1)),
        Matrix(R(0.6, 0.366, 0.19))
    )
    network.printMatricesWeightVector()
}

