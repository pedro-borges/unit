package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object Acceleration : Dimension
{
    override val composite = mapOf(Distance to 1,
                                   Time to -2)

    operator fun div(that: Scalar) = Acceleration
    operator fun times(that: Scalar) = Acceleration

    operator fun div(that: Acceleration) = Scalar

    operator fun times(that: AreaDensity) = EnergyDensity

    operator fun times(that: Mass) = Force

    operator fun div(that: Speed) = Frequency
    operator fun times(that: Pace) = Frequency

    operator fun div(that: Time) = Jerk
    operator fun times(that: Frequency) = Jerk

    operator fun times(that: Momentum) = Power

    operator fun times(that: LinearDensity) = RadiantExposure

    operator fun div(that: SpecificEnergy) = SpacialFrequency

    operator fun div(that: SpacialFrequency) = SpecificEnergy
    operator fun times(that: Distance) = SpecificEnergy

    operator fun div(that: TemperatureGradient) = SpecificHeatCapacity

    operator fun div(that: Frequency) = Speed
    operator fun times(that: Time) = Speed

    operator fun div(that: SpecificHeatCapacity) = TemperatureGradient

    operator fun div(that: Jerk) = Time

    operator fun times(that: MassFlowRate) = Yank
}
