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

    operator fun div(that: Frequency) = Action
    operator fun times(that: Time) = Action

    operator fun div(that: RadiantExposure) = Area

    operator fun div(that: Force) = Distance

    operator fun div(that: ElectricTension) = ElectricCharge

    operator fun div(that: MagneticFlux) = ElectricCurrent

    operator fun div(that: ElectricCharge) = ElectricTension

    operator fun div(that: Volume) = EnergyDensity

    operator fun div(that: Distance) = Force
    operator fun times(that: SpacialFrequency) = Force

    operator fun div(that: Action) = Frequency

    operator fun div(that: ElectricCurrent) = MagneticFlux

    operator fun div(that: SpecificEnergy) = MassDensity

    operator fun div(that: Speed) = Momentum
    operator fun times(that: Pace) = Momentum

    operator fun div(that: Time) = Power
    operator fun times(that: Frequency) = Power

    operator fun div(that: Area) = RadiantExposure
    operator fun times(that: FuelEfficiency) = RadiantExposure

    operator fun div(that: MassDensity) = SpecificEnergy

    operator fun div(that: Momentum) = Speed

    operator fun div(that: Power) = Time

    operator fun div(that: EnergyDensity) = Volume
}
