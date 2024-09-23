package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(temperature = -1)
object ThermalExpansionCoefficient : Dimension
{
    operator fun div(that: Scalar) = ThermalExpansionCoefficient
    operator fun times(that: Scalar) = ThermalExpansionCoefficient

    operator fun div(that: ThermalExpansionCoefficient) = Scalar
    operator fun times(that: Temperature) = Scalar

    operator fun times(that: Energy) = HeatCapacity

    operator fun times(that: TemperatureGradient) = SpacialFrequency

    operator fun times(that: SpecificEnergy) = SpecificHeatCapacity

    operator fun times(that: Yank) = ThermalConductivity
}
