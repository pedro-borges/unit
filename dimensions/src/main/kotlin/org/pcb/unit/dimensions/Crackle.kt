package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object Crackle : Dimension
{
    override val composite = mapOf(Distance to 1,
                                   Time to -5)

    operator fun div(that: Scalar) = Crackle
    operator fun times(that: Scalar) = Crackle

    operator fun div(that: Crackle) = Scalar

    operator fun div(that: Snap) = Frequency

    operator fun div(that: Time) = Pop
    operator fun times(that: Frequency) = Pop

    operator fun div(that: Frequency) = Snap
    operator fun times(that: Time) = Snap

    operator fun div(that: Pop) = Time
}
