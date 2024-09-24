package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object MolarVolume : Dimension
{
    override val composite = mapOf(Distance to 3,
                                   Substance to -1)

    operator fun div(that: Scalar) = MolarVolume
    operator fun times(that: Scalar) = MolarVolume

    operator fun div(that: MolarVolume) = Scalar
    operator fun times(that: Molarity) = Scalar

    operator fun div(that: Time) = CatalyticEfficiency
    operator fun times(that: Frequency) = CatalyticEfficiency

    operator fun times(that: ReactionRate) = Frequency

    operator fun times(that: EnergyDensity) = MolarEnergy

    operator fun div(that: SpecificVolume) = MolarMass
    operator fun times(that: MassDensity) = MolarMass

    operator fun div(that: MolarMass) = SpecificVolume
    operator fun times(that: Molality) = SpecificVolume

    operator fun div(that: CatalyticEfficiency) = Time

    operator fun times(that: Substance) = Volume
}
