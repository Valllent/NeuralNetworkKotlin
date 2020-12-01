package utils

import data.Person
import matrix.Matrix
import matrix.Row

object PersonConverter {
    fun lineIntoPerson(line: String): Person? {
        val list = line.split(",")
        if (list.isEmpty()) return null
        return Person(
            list[0].toDouble(),
            list[1].toDouble(),
            list[2].toDouble(),
            list[3].toDouble(),
            list[4].toDouble(),
            list[5].toDouble(),
            list[6].toDouble(),
            list[7].toDouble(),
            list[8].toDouble(),
            list[9].toDouble(),
            list[10].toDouble(),
            list[11].toDouble(),
            list[12].toDouble(),
            list[13].toDouble()
        )
    }

    fun personIntoNormalMatrix(person: Person): ArrayList<Matrix> {
        val result = arrayListOf<Matrix>()
        person.apply {
            val input = Matrix(
                Row(
                    range((age - 29) / 48),
                    range(sexM),
                    range(painType / 3),
                    range((arterialPressure - 94) / 106),
                    range((cholesterol - 126) / 438),
                    range(normalSugarLevel),
                    range(normalElectrocardiography / 2),
                    range((biggestHeartRate - 71) / 131),
                    range(stenocardia),
                    range(depressionSTSegment / 6.2),
                    range(slopeSTSegment / 2),
                    range(numberOfLargeVessels / 4),
                    range(thalamusType / 3)
                )
            )
            val target = Matrix(Row(target))
            result.add(input)
            result.add(target)
        }
        return result
    }

    private fun range(value: Double): Double {
        return value * 0.99 + 0.01
    }
}