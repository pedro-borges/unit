package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object AngularAcceleration : Dimension
{
    override val composite = mapOf(Angle to 1,
                                   Time to -2)

    operator fun div(that: Scalar) = AngularAcceleration
    operator fun times(that: Scalar) = AngularAcceleration

    operator fun div(that: AngularAcceleration) = Scalar

    operator fun div(that: Frequency) = AngularVelocity
    operator fun times(that: Time) = AngularVelocity

    operator fun div(that: AngularVelocity) = Frequency
}
