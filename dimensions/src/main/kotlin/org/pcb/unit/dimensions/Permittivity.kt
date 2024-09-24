package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(time = 4,
           electricCurrent = 2,
           mass = -1,
           distance = -3)
object Permittivity : Dimension
{
    operator fun div(that: Scalar) = Permittivity
    operator fun times(that: Scalar) = Permittivity

    operator fun div(that: Permittivity) = Scalar

    operator fun div(that: SpacialFrequency) = Capacitance
    operator fun times(that: Distance) = Capacitance

    operator fun div(that: Pace) = ElectricConductance
    operator fun times(that: Speed) = ElectricConductance

    operator fun times(that: CatalyticEfficiency) = MolarConductivity

    operator fun div(that: ElectricConductance) = Pace
    operator fun times(that: ElectricResistance) = Pace

    operator fun div(that: Capacitance) = SpacialFrequency
}
