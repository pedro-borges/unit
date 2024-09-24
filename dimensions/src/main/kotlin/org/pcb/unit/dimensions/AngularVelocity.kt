package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object AngularVelocity : Dimension
{
    override val composite = mapOf(Angle to 1,
                                   Time to -1)

    operator fun div(that: Scalar) = AngularVelocity
    operator fun times(that: Scalar) = AngularVelocity

    operator fun div(that: AngularVelocity) = Scalar

    operator fun div(that: Frequency) = Angle
    operator fun times(that: Time) = Angle

    operator fun div(that: Time) = AngularAcceleration
    operator fun times(that: Frequency) = AngularAcceleration

    operator fun div(that: Angle) = Frequency

    operator fun div(that: AngularAcceleration) = Time
}
