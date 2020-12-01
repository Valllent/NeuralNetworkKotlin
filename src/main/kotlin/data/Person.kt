package data

data class Person(
    val age: Double,
    val sexM: Double,
    val painType: Double,
    val arterialPressure: Double,
    val cholesterol: Double,
    val normalSugarLevel: Double,
    val normalElectrocardiography: Double,
    val biggestHeartRate: Double,
    val stenocardia: Double,
    val depressionSTSegment: Double,
    val slopeSTSegment: Double,
    val numberOfLargeVessels: Double,
    val thalamusType: Double,
    var target: Double = 0.0
)