package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object ReactionRate : Dimension
{
    override val composite = mapOf(Substance to 1,
                                   Time to -1,
                                   Distance to -3)

    operator fun div(that: Scalar) = ReactionRate
    operator fun times(that: Scalar) = ReactionRate

    operator fun div(that: ReactionRate) = Scalar

    operator fun div(that: Molarity) = Frequency
    operator fun times(that: MolarVolume) = Frequency

    operator fun div(that: Frequency) = Molarity
    operator fun times(that: Time) = Molarity

    operator fun times(that: MolarEnergy) = PowerDensity
}
