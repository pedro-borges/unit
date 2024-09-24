package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object SpecificHeatCapacity : Dimension
{
    override val composite = mapOf(Distance to 2,
                                   Temperature to -1,
                                   Time to -2)

    operator fun div(that: Scalar) = SpecificHeatCapacity
    operator fun times(that: Scalar) = SpecificHeatCapacity

    operator fun div(that: SpecificHeatCapacity) = Scalar

    operator fun times(that: TemperatureGradient) = Acceleration

    operator fun times(that: Mass) = HeatCapacity

    operator fun div(that: MolarHeatCapacity) = Molality

    operator fun div(that: Molality) = MolarHeatCapacity
    operator fun times(that: MolarMass) = MolarHeatCapacity

    operator fun div(that: ThermalExpansionCoefficient) = SpecificEnergy
    operator fun times(that: Temperature) = SpecificEnergy

    operator fun div(that: SpecificEnergy) = ThermalExpansionCoefficient
}
