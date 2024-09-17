package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(time = 3,
           electricCurrent = 2,
           mass = -1,
           distance = -2)
object ElectricConductance : Dimension
{
    operator fun div(that: Scalar) = ElectricConductance
    operator fun times(that: Scalar) = ElectricConductance

    operator fun div(that: ElectricConductance) = Scalar
    operator fun times(that: ElectricResistance) = Scalar

    operator fun div(that: Frequency) = Capacitance
    operator fun times(that: Time) = Capacitance

    operator fun times(that: MagneticFlux) = ElectricCharge

    operator fun times(that: ElectricTension) = ElectricCurrent

    operator fun div(that: Capacitance) = Frequency

    operator fun times(that: Inductance) = Time
}