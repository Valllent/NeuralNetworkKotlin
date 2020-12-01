import csv.CSVReader
import neural_network.NeuralNetwork
import utils.PersonConverter
import java.io.File

val fileTest = "test.csv"
val fileTrain = "heart_train.csv"
val fileCheck = "heart_check.csv"
val fileFull = "heart_full.csv"
val folder = "csv" + File.separator

fun main() {
    val trainReader = CSVReader(folder + fileTrain)
    val checkReader = CSVReader(folder + fileCheck)

    val network = NeuralNetwork(13, 100, 1)
    trainReader.forEach {
        val (input, target) = PersonConverter.personIntoNormalMatrix(it)
        network.train(input, target)
    }
    var counter = 0
    var right = 0
    checkReader.forEach {
        val (input, target) = PersonConverter.personIntoNormalMatrix(it)
        val result = network.query(input)
        val queryResult = if (result[0,0] > 0.5) 1.0 else 0.0
        if (queryResult == target[0,0]) right++
        counter++
    }
    println("Right: $right")
    println("Counter $counter")
}

