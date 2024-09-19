package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(distance = 1,
           time = -3)
object Jerk : Dimension
{
    operator fun div(that: Scalar) = Jerk
    operator fun times(that: Scalar) = Jerk

    operator fun div(that: Jerk) = Scalar

    operator fun div(that: Frequency) = Acceleration
    operator fun times(that: Time) = Acceleration

    operator fun div(that: Acceleration) = Frequency
}
