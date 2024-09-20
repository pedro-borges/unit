package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(distance = 2,
           mass = 1,
           time = -3)
object Power : Dimension
{
    operator fun div(that: Scalar) = Power
    operator fun times(that: Scalar) = Power

    operator fun div(that: Power) = Scalar

    operator fun div(that: Momentum) = Acceleration

    operator fun div(that: Yank) = Distance

    operator fun div(that: ElectricTension) = ElectricCurrent

    operator fun div(that: ElectricCurrent) = ElectricTension

    operator fun div(that: Frequency) = Energy
    operator fun times(that: Time) = Energy

    operator fun div(that: VolumetricFlow) = EnergyDensity

    operator fun div(that: Speed) = Force
    operator fun times(that: Pace) = Force

    operator fun div(that: Energy) = Frequency

    operator fun div(that: SpecificEnergy) = MassFlowRate

    operator fun div(that: Acceleration) = Momentum

    operator fun div(that: MassFlowRate) = SpecificEnergy

    operator fun div(that: Force) = Speed

    operator fun div(that: EnergyDensity) = VolumetricFlow

    operator fun div(that: Distance) = Yank
}
