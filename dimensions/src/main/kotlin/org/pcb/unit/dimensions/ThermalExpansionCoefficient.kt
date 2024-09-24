package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object ThermalExpansionCoefficient : Dimension
{
    override val composite = mapOf(Temperature to -1)

    operator fun div(that: Scalar) = ThermalExpansionCoefficient
    operator fun times(that: Scalar) = ThermalExpansionCoefficient

    operator fun div(that: ThermalExpansionCoefficient) = Scalar
    operator fun times(that: Temperature) = Scalar

    operator fun times(that: Energy) = HeatCapacity

    operator fun times(that: MolarEnergy) = MolarHeatCapacity

    operator fun times(that: TemperatureGradient) = SpacialFrequency

    operator fun times(that: SpecificEnergy) = SpecificHeatCapacity

    operator fun times(that: Yank) = ThermalConductivity
}
