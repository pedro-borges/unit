package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object CatalyticEfficiency : Dimension
{
    override val composite = mapOf(Distance to 3,
                                   Substance to -1,
                                   Time to -1)

    operator fun div(that: Scalar) = CatalyticEfficiency
    operator fun times(that: Scalar) = CatalyticEfficiency

    operator fun div(that: CatalyticEfficiency) = Scalar

    operator fun div(that: MolarVolume) = Frequency
    operator fun times(that: Molarity) = Frequency

    operator fun times(that: Permittivity) = MolarConductivity

    operator fun times(that: Viscosity) = MolarEnergy

    operator fun div(that: Frequency) = MolarVolume
    operator fun times(that: Time) = MolarVolume

    operator fun div(that: VolumetricFlow) = ReciprocalSubstance

    operator fun div(that: ReciprocalSubstance) = VolumetricFlow
    operator fun times(that: Substance) = VolumetricFlow
}
