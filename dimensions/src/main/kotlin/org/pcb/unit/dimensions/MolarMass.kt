package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object MolarMass : Dimension
{
    override val composite = mapOf(Mass to 1,
                                   Substance to -1)

    operator fun div(that: Scalar) = MolarMass
    operator fun times(that: Scalar) = MolarMass

    operator fun div(that: MolarMass) = Scalar
    operator fun times(that: Molality) = Scalar

    operator fun div(that: ReciprocalSubstance) = Mass
    operator fun times(that: Substance) = Mass

    operator fun div(that: MolarVolume) = MassDensity
    operator fun times(that: Molarity) = MassDensity

    operator fun times(that: SpecificEnergy) = MolarEnergy

    operator fun times(that: SpecificHeatCapacity) = MolarHeatCapacity

    operator fun div(that: MassDensity) = MolarVolume
    operator fun times(that: SpecificVolume) = MolarVolume

    operator fun div(that: Mass) = ReciprocalSubstance
}
