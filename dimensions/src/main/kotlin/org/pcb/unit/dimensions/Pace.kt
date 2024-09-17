package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(time = 1,
           distance = -1)
object Pace : Dimension
{
    operator fun div(that: Scalar) = Pace
    operator fun times(that: Scalar) = Pace

    operator fun div(that: Pace) = Scalar
    operator fun times(that: Speed) = Scalar

    operator fun times(that: Power) = Force

    operator fun times(that: Acceleration) = Frequency

    operator fun times(that: Momentum) = Mass

    operator fun times(that: Force) = MassFlowRate

    operator fun times(that: Energy) = Momentum

    operator fun times(that: Distance) = Time
}