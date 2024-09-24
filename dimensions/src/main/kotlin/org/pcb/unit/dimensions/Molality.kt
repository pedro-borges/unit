package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object Molality : Dimension
{
    override val composite = mapOf(Substance to 1,
                                   Mass to -1)

    operator fun div(that: Scalar) = Molality
    operator fun times(that: Scalar) = Molality

    operator fun div(that: Molality) = Scalar
    operator fun times(that: MolarMass) = Scalar

    operator fun div(that: SpecificVolume) = Molarity
    operator fun times(that: MassDensity) = Molarity

    operator fun times(that: MolarEnergy) = SpecificEnergy

    operator fun times(that: MolarHeatCapacity) = SpecificHeatCapacity

    operator fun div(that: Molarity) = SpecificVolume
    operator fun times(that: MolarVolume) = SpecificVolume

    operator fun times(that: Mass) = Substance
}
