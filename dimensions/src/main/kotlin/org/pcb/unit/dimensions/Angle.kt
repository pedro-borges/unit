package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(angle = 1)
object Angle : Dimension
{
    operator fun div(that: Scalar) = Angle
    operator fun times(that: Scalar) = Angle
    operator fun div(that: Angle) = Scalar

    operator fun div(that: Time) = AngularVelocity
    operator fun times(that: Frequency) = AngularVelocity

    operator fun times(that: Angle) = SolidAngle

    operator fun div(that: AngularVelocity) = Time
}