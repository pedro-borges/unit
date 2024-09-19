package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(angle = 1,
           time = -1)
object AngularVelocity : Dimension
{
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