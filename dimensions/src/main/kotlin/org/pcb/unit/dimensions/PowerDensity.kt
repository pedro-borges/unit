package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object PowerDensity : Dimension
{
    override val composite = mapOf(Mass to 1,
                                   Distance to -1,
                                   Time to -3)

    operator fun div(that: Scalar) = PowerDensity
    operator fun times(that: Scalar) = PowerDensity

    operator fun div(that: PowerDensity) = Scalar

    operator fun div(that: Jerk) = AreaDensity

    operator fun div(that: Frequency) = EnergyDensity
    operator fun times(that: Time) = EnergyDensity

    operator fun div(that: EnergyDensity) = Frequency

    operator fun div(that: Yank) = FuelEfficiency

    operator fun div(that: SpacialFrequency) = Intensity
    operator fun times(that: Distance) = Intensity

    operator fun div(that: AreaDensity) = Jerk

    operator fun div(that: ReactionRate) = MolarEnergy

    operator fun times(that: Volume) = Power

    operator fun times(that: Absement) = RadiantExposure

    operator fun div(that: MolarEnergy) = ReactionRate

    operator fun div(that: Intensity) = SpacialFrequency

    operator fun div(that: FuelEfficiency) = Yank
    operator fun times(that: Area) = Yank
}
