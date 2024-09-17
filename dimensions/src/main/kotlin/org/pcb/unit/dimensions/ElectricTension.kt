package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(distance = 2,
           mass = 1,
           electricCurrent = -1,
           time = -3)
object ElectricTension : Dimension
{
    operator fun div(that: Scalar) = ElectricTension
    operator fun times(that: Scalar) = ElectricTension

    operator fun div(that: ElectricTension) = Scalar

    operator fun times(that: Capacitance) = ElectricCharge

    operator fun div(that: ElectricResistance) = ElectricCurrent
    operator fun times(that: ElectricConductance) = ElectricCurrent

    operator fun div(that: ElectricCurrent) = ElectricResistance

    operator fun times(that: ElectricCharge) = Energy

    operator fun div(that: MagneticFlux) = Frequency

    operator fun div(that: Frequency) = MagneticFlux
    operator fun times(that: Time) = MagneticFlux

    operator fun times(that: ElectricCurrent) = Power
}