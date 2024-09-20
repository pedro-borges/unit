package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(distance = 1,
           time = -4)
object Snap : Dimension
{
    operator fun div(that: Scalar) = Snap
    operator fun times(that: Scalar) = Snap

    operator fun div(that: Snap) = Scalar

    operator fun div(that: Jerk) = Frequency

    operator fun div(that: Frequency) = Jerk
    operator fun times(that: Time) = Jerk
}
