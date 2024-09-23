package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(distance = 2,
           mass = 1)
object MomentOfInertia : Dimension
{
    operator fun div(that: Scalar) = MomentOfInertia
    operator fun times(that: Scalar) = MomentOfInertia

    operator fun div(that: MomentOfInertia) = Scalar

    operator fun div(that: Time) = Action
    operator fun times(that: Frequency) = Action

    operator fun div(that: Mass) = Area

    operator fun div(that: Area) = Mass
    operator fun times(that: FuelEfficiency) = Mass

    operator fun div(that: Action) = Time
}
