package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object Jerk : Dimension
{
    override val composite = mapOf(Distance to 1,
                                   Time to -3)

    operator fun div(that: Scalar) = Jerk
    operator fun times(that: Scalar) = Jerk

    operator fun div(that: Jerk) = Scalar

    operator fun div(that: Frequency) = Acceleration
    operator fun times(that: Time) = Acceleration

    operator fun div(that: Acceleration) = Frequency

    operator fun times(that: LinearDensity) = Intensity

    operator fun times(that: AreaDensity) = PowerDensity

    operator fun div(that: Time) = Snap
    operator fun times(that: Frequency) = Snap

    operator fun times(that: Absement) = SpecificEnergy

    operator fun div(that: Snap) = Time

    operator fun times(that: Mass) = Yank
}
