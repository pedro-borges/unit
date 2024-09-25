package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object Intensity : Dimension
{
    override val composite = mapOf(Mass to 1,
                                   Time to -3)

    operator fun div(that: Scalar) = Intensity
    operator fun times(that: Scalar) = Intensity

    operator fun div(that: Intensity) = Scalar

    operator fun div(that: PowerDensity) = Distance

    operator fun div(that: Speed) = EnergyDensity
    operator fun times(that: Pace) = EnergyDensity

    operator fun times(that: Absement) = Force

    operator fun div(that: RadiantExposure) = Frequency

    operator fun div(that: Power) = FuelEfficiency

    operator fun div(that: LinearDensity) = Jerk

    operator fun div(that: Jerk) = LinearDensity

    operator fun div(that: FuelEfficiency) = Power
    operator fun times(that: Area) = Power

    operator fun div(that: Distance) = PowerDensity
    operator fun times(that: SpacialFrequency) = PowerDensity

    operator fun div(that: Frequency) = RadiantExposure
    operator fun times(that: Time) = RadiantExposure

    operator fun div(that: Yank) = SpacialFrequency

    operator fun div(that: EnergyDensity) = Speed

    operator fun div(that: ThermalConductivity) = TemperatureGradient

    operator fun div(that: TemperatureGradient) = ThermalConductivity

    operator fun div(that: SpacialFrequency) = Yank
    operator fun times(that: Distance) = Yank
}
