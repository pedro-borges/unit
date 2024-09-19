package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(distance = 2,
           mass = 1,
           time = -2)
object Energy : Dimension
{
    operator fun div(that: Scalar) = Energy
    operator fun times(that: Scalar) = Energy

    operator fun div(that: Energy) = Scalar

    operator fun div(that: Force) = Distance

    operator fun div(that: ElectricTension) = ElectricCharge

    operator fun div(that: MagneticFlux) = ElectricCurrent

    operator fun div(that: ElectricCharge) = ElectricTension

    operator fun div(that: Volume) = EnergyDensity

    operator fun div(that: Distance) = Force

    operator fun div(that: ElectricCurrent) = MagneticFlux

    operator fun div(that: Speed) = Momentum
    operator fun times(that: Pace) = Momentum

    operator fun div(that: Time) = Power
    operator fun times(that: Frequency) = Power

    operator fun div(that: Momentum) = Speed

    operator fun div(that: Power) = Time

    operator fun div(that: EnergyDensity) = Volume
}
