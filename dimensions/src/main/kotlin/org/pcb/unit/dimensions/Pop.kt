package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object Pop : Dimension
{
    override val composite = mapOf(Distance to 1,
                                   Time to -6)

    operator fun div(that: Scalar) = Pop
    operator fun times(that: Scalar) = Pop

    operator fun div(that: Pop) = Scalar

    operator fun div(that: Frequency) = Crackle
    operator fun times(that: Time) = Crackle

    operator fun div(that: Crackle) = Frequency
}
