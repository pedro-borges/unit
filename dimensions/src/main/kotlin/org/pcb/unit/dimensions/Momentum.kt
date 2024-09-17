package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(mass = 1,
           distance = 1,
           time = -1)
object Momentum : Dimension
{
    operator fun div(that: Scalar) = Momentum
    operator fun times(that: Scalar) = Momentum

    operator fun div(that: Momentum) = Scalar

    operator fun div(that: MassFlowRate) = Distance

    operator fun div(that: Pace) = Energy
    operator fun times(that: Speed) = Energy

    operator fun div(that: Time) = Force
    operator fun times(that: Frequency) = Force

    operator fun div(that: Speed) = Mass
    operator fun times(that: Pace) = Mass

    operator fun div(that: Distance) = MassFlowRate

    operator fun div(that: Energy) = Pace

    operator fun times(that: Acceleration) = Power

    operator fun div(that: Mass) = Speed

    operator fun div(that: Force) = Time
}