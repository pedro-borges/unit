package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object CentrifugalForce : Dimension
{
    override val composite = mapOf(Angle to 1,
                                   Distance to 1,
                                   Mass to 1,
                                   Time to -2)

    operator fun div(that: Scalar) = CentrifugalForce
    operator fun times(that: Scalar) = CentrifugalForce

    operator fun div(that: CentrifugalForce) = Scalar

    operator fun div(that: Force) = Angle

    operator fun div(that: Momentum) = AngularVelocity

    operator fun div(that: Angle) = Force

    operator fun div(that: AngularVelocity) = Momentum
}
