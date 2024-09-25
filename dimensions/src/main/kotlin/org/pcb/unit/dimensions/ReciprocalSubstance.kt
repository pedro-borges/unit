package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object ReciprocalSubstance : Dimension
{
    override val composite = mapOf(Substance to -1)

    operator fun div(that: Scalar) = ReciprocalSubstance
    operator fun times(that: Scalar) = ReciprocalSubstance

    operator fun div(that: ReciprocalSubstance) = Scalar
    operator fun times(that: Substance) = Scalar

    operator fun times(that: VolumetricFlow) = CatalyticEfficiency

    operator fun times(that: Energy) = MolarEnergy

    operator fun times(that: HeatCapacity) = MolarHeatCapacity

    operator fun times(that: Mass) = MolarMass

    operator fun times(that: Volume) = MolarVolume
}
