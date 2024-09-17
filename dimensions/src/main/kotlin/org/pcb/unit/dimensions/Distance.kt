package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(distance = 1)
object Distance : Dimension
{
    operator fun div(that: Scalar) = Distance
    operator fun times(that: Scalar) = Distance
    operator fun div(that: Distance) = Scalar

    operator fun times(that: Distance) = Area

    operator fun times(that: Force) = Energy

    operator fun times(that: MassFlowRate) = Momentum

    operator fun div(that: Time) = Speed
    operator fun times(that: Frequency) = Speed

    operator fun div(that: Speed) = Time
    operator fun times(that: Pace) = Time

    operator fun times(that: Area) = Volume
}