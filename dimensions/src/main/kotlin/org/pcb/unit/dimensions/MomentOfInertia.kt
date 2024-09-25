package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object MomentOfInertia : Dimension
{
    override val composite = mapOf(Distance to 2,
                                   Mass to 1)

    operator fun div(that: Scalar) = MomentOfInertia
    operator fun times(that: Scalar) = MomentOfInertia

    operator fun div(that: MomentOfInertia) = Scalar

    operator fun div(that: Momentum) = Absement

    operator fun div(that: Time) = Action
    operator fun times(that: Frequency) = Action

    operator fun times(that: Viscosity) = AngularMomentum

    operator fun div(that: Mass) = Area

    operator fun div(that: Volume) = LinearDensity

    operator fun div(that: Area) = Mass
    operator fun times(that: FuelEfficiency) = Mass

    operator fun div(that: Absement) = Momentum

    operator fun div(that: Action) = Time

    operator fun div(that: LinearDensity) = Volume
}
