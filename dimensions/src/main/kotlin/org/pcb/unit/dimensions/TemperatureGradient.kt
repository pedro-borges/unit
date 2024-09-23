package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(temperature = 1,
           distance = -1)
object TemperatureGradient : Dimension
{
    operator fun div(that: Scalar) = TemperatureGradient
    operator fun times(that: Scalar) = TemperatureGradient

    operator fun div(that: TemperatureGradient) = Scalar

    operator fun times(that: SpecificHeatCapacity) = Acceleration

    operator fun times(that: ThermalConductivity) = EnergyFluxDensity

    operator fun times(that: HeatCapacity) = Force

    operator fun div(that: Temperature) = SpacialFrequency
    operator fun times(that: ThermalExpansionCoefficient) = SpacialFrequency

    operator fun div(that: SpacialFrequency) = Temperature
    operator fun times(that: Distance) = Temperature
}
