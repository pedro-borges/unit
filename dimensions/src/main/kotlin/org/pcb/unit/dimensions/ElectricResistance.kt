package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(distance = 2,
           mass = 1,
           electricCurrent = -2,
           time = -3)
object ElectricResistance : Dimension
{
    operator fun div(that: Scalar) = ElectricResistance
    operator fun times(that: Scalar) = ElectricResistance

    operator fun div(that: ElectricResistance) = Scalar
    operator fun times(that: ElectricConductance) = Scalar

    operator fun times(that: ElectricCurrent) = ElectricTension

    operator fun div(that: Inductance) = Frequency

    operator fun div(that: Frequency) = Inductance
    operator fun times(that: Time) = Inductance

    operator fun times(that: ElectricCharge) = MagneticFlux

    operator fun times(that: Capacitance) = Time
}