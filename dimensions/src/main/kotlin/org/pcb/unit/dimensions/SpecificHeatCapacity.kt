package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(distance = 2,
           temperature = -1,
           time = -2)
object SpecificHeatCapacity : Dimension
{
    operator fun div(that: Scalar) = SpecificHeatCapacity
    operator fun times(that: Scalar) = SpecificHeatCapacity

    operator fun div(that: SpecificHeatCapacity) = Scalar

    operator fun times(that: TemperatureGradient) = Acceleration

    operator fun times(that: Mass) = HeatCapacity

    operator fun div(that: ThermalExpansionCoefficient) = SpecificEnergy
    operator fun times(that: Temperature) = SpecificEnergy

    operator fun div(that: SpecificEnergy) = ThermalExpansionCoefficient
}
