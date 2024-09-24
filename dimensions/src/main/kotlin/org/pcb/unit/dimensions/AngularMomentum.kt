package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object AngularMomentum : Dimension
{
    override val composite = mapOf(Mass to 2,
                                   Distance to 1,
                                   Time to -1)

    operator fun div(that: Scalar) = AngularMomentum
    operator fun times(that: Scalar) = AngularMomentum

    operator fun div(that: AngularMomentum) = Scalar

    operator fun div(that: LinearDensity) = Action

    operator fun div(that: Action) = LinearDensity

    operator fun div(that: Momentum) = Mass

    operator fun div(that: Mass) = Momentum
}
