package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object Molarity : Dimension
{
    override val composite = mapOf(Substance to 1,
                                   Distance to -3)

    operator fun div(that: Scalar) = Molarity
    operator fun times(that: Scalar) = Molarity

    operator fun div(that: Molarity) = Scalar
    operator fun times(that: MolarVolume) = Scalar

    operator fun times(that: MolarEnergy) = EnergyDensity

    operator fun times(that: CatalyticEfficiency) = Frequency

    operator fun div(that: Molality) = MassDensity
    operator fun times(that: MolarMass) = MassDensity

    operator fun div(that: MassDensity) = Molality
    operator fun times(that: SpecificVolume) = Molality

    operator fun div(that: Time) = ReactionRate
    operator fun times(that: Frequency) = ReactionRate

    operator fun times(that: Volume) = Substance

    operator fun div(that: ReactionRate) = Time
}
