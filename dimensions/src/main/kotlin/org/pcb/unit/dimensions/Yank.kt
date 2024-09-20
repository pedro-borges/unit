package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(distance = 1,
           mass = 1,
           time = -3)
object Yank : Dimension
{
    operator fun div(that: Scalar) = Yank
    operator fun times(that: Scalar) = Yank

    operator fun div(that: Yank) = Scalar

    operator fun div(that: MassFlowRate) = Acceleration

    operator fun div(that: Frequency) = Force
    operator fun times(that: Time) = Force

    operator fun div(that: Force) = Frequency

    operator fun div(that: Mass) = Jerk

    operator fun div(that: Jerk) = Mass

    operator fun div(that: Acceleration) = MassFlowRate

    operator fun times(that: Distance) = Power
}
