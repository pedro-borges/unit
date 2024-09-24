package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object Angle : Dimension
{
    override val composite = mapOf(Angle to 1)

    operator fun div(that: Scalar) = Angle
    operator fun times(that: Scalar) = Angle

    operator fun div(that: Angle) = Scalar

    operator fun div(that: Time) = AngularVelocity
    operator fun times(that: Frequency) = AngularVelocity

    operator fun times(that: Angle) = SolidAngle

    operator fun div(that: AngularVelocity) = Time
}
