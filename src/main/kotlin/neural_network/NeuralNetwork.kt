package neural_network

import matrix.*

class NeuralNetwork(
    private vararg val nodesCountVector: Int,
    private val learningRate: Double = 0.5,
    private val activationFunction: (Double) -> Double = ActivationFunctions::sigmoid
) {
    private val weightMatricesVector: ArrayList<Matrix> = arrayListOf()
    private val layersCount = nodesCountVector.size - 1

    init {
        fillWeightMatricesArray()
    }

    fun query(inputMatrix: Matrix): Matrix {
        val input = inputMatrix.T
        val history = historyQuery(input)
//        header("Output history")
//        for (output in history) {
//            output.print()
//        }
        return history[layersCount]
    }

    private fun historyQuery(input: Matrix): ArrayList<Matrix> {
        var lastInput = input
        val history = arrayListOf(lastInput)
        for (i in 0 until layersCount) {
            lastInput = (Matrix.dot(weightMatricesVector[i], lastInput)).forEach(activationFunction)
            history.add(lastInput)
        }
        return history
    }

    fun train(inputMatrix: Matrix, targetMatrix: Matrix) {
        val target = !targetMatrix
        val input = !inputMatrix

        val outputHistory = historyQuery(input)
        var errors = Matrix.empty()
        for (layerNumber in layersCount downTo 1) {
//            header("Offset [$layerNumber]")
            errors = if (layerNumber == layersCount) {
                target - outputHistory[layerNumber]
            } else {
                (weightMatricesVector[layerNumber].T).dot(errors)
            }
            val f0 = errors * outputHistory[layerNumber] * (1.0 - outputHistory[layerNumber])
            val f1 = Matrix.dot(f0, outputHistory[layerNumber-1].T)
            val offset = learningRate * f1
            weightMatricesVector[layerNumber-1] += offset
//            offset.print()
        }
    }

    fun printMatricesWeightVector() {
        for (i in 0 until layersCount) {
            header("Weight matrix [$i]")
            weightMatricesVector[i].print()
        }
    }

    private fun header(string: String) {
        println("-------------------------$string-------------------------")
    }

    private fun fillWeightMatricesArray() {
        for (i in 0 until (nodesCountVector.size - 1)) {
            weightMatricesVector.add(
                Matrix.getNormalMatrix(nodesCountVector[i + 1], nodesCountVector[i], nodesCountVector[i])
            )
        }
    }

}