package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(mass = 2,
           distance = 1,
           time = -1)
object AngularMomentum : Dimension
{
    operator fun div(that: Scalar) = AngularMomentum
    operator fun times(that: Scalar) = AngularMomentum

    operator fun div(that: AngularMomentum) = Scalar

    operator fun div(that: Momentum) = Mass

    operator fun div(that: Mass) = Momentum
}