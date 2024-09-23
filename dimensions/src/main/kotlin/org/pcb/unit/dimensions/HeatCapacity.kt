package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(distance = 2,
           mass = 1,
           temperature = -1,
           time = -2)
object HeatCapacity : Dimension
{
    operator fun div(that: Scalar) = HeatCapacity
    operator fun times(that: Scalar) = HeatCapacity

    operator fun div(that: HeatCapacity) = Scalar

    operator fun div(that: MolarHeatCapacity) = AmountOfSubstance

    operator fun div(that: ThermalExpansionCoefficient) = Energy
    operator fun times(that: Temperature) = Energy

    operator fun times(that: TemperatureGradient) = Force

    operator fun div(that: SpecificHeatCapacity) = Mass

    operator fun div(that: AmountOfSubstance) = MolarHeatCapacity

    operator fun div(that: Mass) = SpecificHeatCapacity

    operator fun div(that: Energy) = ThermalExpansionCoefficient
}
