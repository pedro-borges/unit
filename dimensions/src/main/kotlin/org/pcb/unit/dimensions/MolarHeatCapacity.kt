package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object MolarHeatCapacity : Dimension
{
    override val composite = mapOf(Distance to 2,
                                   Mass to 1,
                                   Substance to -1,
                                   Temperature to -1,
                                   Time to -2)

    operator fun div(that: Scalar) = MolarHeatCapacity
    operator fun times(that: Scalar) = MolarHeatCapacity

    operator fun div(that: MolarHeatCapacity) = Scalar

    operator fun times(that: Substance) = HeatCapacity

    operator fun div(that: ThermalExpansionCoefficient) = MolarEnergy
    operator fun times(that: Temperature) = MolarEnergy

    operator fun div(that: SpecificHeatCapacity) = MolarMass

    operator fun div(that: MolarMass) = SpecificHeatCapacity
    operator fun times(that: Molality) = SpecificHeatCapacity

    operator fun div(that: MolarEnergy) = ThermalExpansionCoefficient
}
