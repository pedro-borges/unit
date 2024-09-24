package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object Snap : Dimension
{
    override val composite = mapOf(Distance to 1,
                                   Time to -4)

    operator fun div(that: Scalar) = Snap
    operator fun times(that: Scalar) = Snap

    operator fun div(that: Snap) = Scalar

    operator fun div(that: Jerk) = Frequency

    operator fun div(that: Frequency) = Jerk
    operator fun times(that: Time) = Jerk
}
