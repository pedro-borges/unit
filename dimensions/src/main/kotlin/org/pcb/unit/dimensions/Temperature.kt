package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(temperature = 1)
object Temperature : Dimension
{
    operator fun div(that: Scalar) = Temperature
    operator fun times(that: Scalar) = Temperature

    operator fun div(that: Temperature) = Scalar
    operator fun times(that: ThermalExpansionCoefficient) = Scalar

    operator fun div(that: TemperatureGradient) = Distance

    operator fun times(that: HeatCapacity) = Energy

    operator fun times(that: MolarHeatCapacity) = MolarEnergy

    operator fun times(that: SpecificHeatCapacity) = SpecificEnergy

    operator fun div(that: Distance) = TemperatureGradient
    operator fun times(that: SpacialFrequency) = TemperatureGradient

    operator fun times(that: ThermalConductivity) = Yank
}
