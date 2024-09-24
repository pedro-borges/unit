package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(distance = 1,
           mass = 1,
           temperature = -1,
           time = -3)
object ThermalConductivity : Dimension
{
    operator fun div(that: Scalar) = ThermalConductivity
    operator fun times(that: Scalar) = ThermalConductivity

    operator fun div(that: ThermalConductivity) = Scalar

    operator fun times(that: TemperatureGradient) = Intensity

    operator fun div(that: Yank) = ThermalExpansionCoefficient

    operator fun times(that: ThermalResistance) = Volume

    operator fun div(that: ThermalExpansionCoefficient) = Yank
    operator fun times(that: Temperature) = Yank
}
