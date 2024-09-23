package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(time = 3,
           distance = 2,
           temperature = 1,
           mass = -1)
object ThermalResistance : Dimension
{
    operator fun div(that: Scalar) = ThermalResistance
    operator fun times(that: Scalar) = ThermalResistance

    operator fun div(that: ThermalResistance) = Scalar

    operator fun times(that: ThermalConductivity) = Volume
}
