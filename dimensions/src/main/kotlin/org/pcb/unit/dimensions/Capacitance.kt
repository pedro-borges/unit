package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(time = 4,
           electricCurrent = 2,
           mass = -1,
           distance = -2)
object Capacitance : Dimension
{
    operator fun div(that: Scalar) = Capacitance
    operator fun times(that: Scalar) = Capacitance

    operator fun div(that: Capacitance) = Scalar

    operator fun times(that: ElectricTension) = ElectricCharge

    operator fun div(that: Time) = ElectricConductance
    operator fun times(that: Frequency) = ElectricConductance

    operator fun div(that: ElectricConductance) = Time
    operator fun times(that: ElectricResistance) = Time
}
