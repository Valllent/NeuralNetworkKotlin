package csv

import data.Person
import utils.PersonConverter
import java.io.File

class CSVReader(path: String) {

    private val file = File(path)
    private var reader = file.bufferedReader()

    fun forEach(doForEach: (Person) -> Unit) {
        while (true) {
            val person = read() ?: break
            doForEach(person)
        }
        reader = file.bufferedReader()
    }

    private fun read(): Person? {
        val line = reader.readLine() ?: return null
        return PersonConverter.lineIntoPerson(line)
    }

}