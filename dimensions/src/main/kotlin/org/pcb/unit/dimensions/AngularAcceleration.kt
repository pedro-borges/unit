package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(angle = 1,
           time = -2)
object AngularAcceleration : Dimension
{
    operator fun div(that: Scalar) = AngularAcceleration
    operator fun times(that: Scalar) = AngularAcceleration

    operator fun div(that: AngularAcceleration) = Scalar

    operator fun div(that: Frequency) = AngularVelocity
    operator fun times(that: Time) = AngularVelocity

    operator fun div(that: AngularVelocity) = Frequency
}
