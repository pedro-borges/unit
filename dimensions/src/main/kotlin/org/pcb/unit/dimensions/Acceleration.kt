package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(distance = 1,
           time = -2)
object Acceleration : Dimension
{
    operator fun div(that: Scalar) = Acceleration
    operator fun times(that: Scalar) = Acceleration

    operator fun div(that: Acceleration) = Scalar

    operator fun times(that: Mass) = Force

    operator fun div(that: Speed) = Frequency
    operator fun times(that: Pace) = Frequency

    operator fun times(that: Momentum) = Power

    operator fun div(that: Frequency) = Speed
    operator fun times(that: Time) = Speed
}
