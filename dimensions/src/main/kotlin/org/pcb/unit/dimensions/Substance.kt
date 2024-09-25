package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object Substance : Dimension
{
    override val composite = mapOf(Substance to 1)

    operator fun div(that: Scalar) = Substance
    operator fun times(that: Scalar) = Substance

    operator fun div(that: Substance) = Scalar
    operator fun times(that: ReciprocalSubstance) = Scalar

    operator fun times(that: MolarEnergy) = Energy

    operator fun times(that: MolarHeatCapacity) = HeatCapacity

    operator fun div(that: Molality) = Mass
    operator fun times(that: MolarMass) = Mass

    operator fun div(that: Mass) = Molality

    operator fun div(that: Volume) = Molarity

    operator fun div(that: Molarity) = Volume
    operator fun times(that: MolarVolume) = Volume

    operator fun times(that: CatalyticEfficiency) = VolumetricFlow
}
