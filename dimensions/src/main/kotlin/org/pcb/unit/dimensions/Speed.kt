package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(distance = 1,
           time = -1)
object Speed : Dimension
{
    operator fun div(that: Scalar) = Speed
    operator fun times(that: Scalar) = Speed

    operator fun div(that: Speed) = Scalar
    operator fun times(that: Pace) = Scalar

    operator fun div(that: Time) = Acceleration
    operator fun times(that: Frequency) = Acceleration

    operator fun div(that: Frequency) = Distance
    operator fun times(that: Time) = Distance

    operator fun times(that: Momentum) = Energy

    operator fun times(that: MassFlowRate) = Force

    operator fun div(that: Distance) = Frequency

    operator fun times(that: Mass) = Momentum

    operator fun times(that: Force) = Power

    operator fun div(that: Acceleration) = Time
}
