package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object Momentum : Dimension
{
    override val composite = mapOf(Distance to 1,
                                   Mass to 1,
                                   Time to -1)

    operator fun div(that: Scalar) = Momentum
    operator fun times(that: Scalar) = Momentum

    operator fun div(that: Momentum) = Scalar

    operator fun div(that: RadiantExposure) = Absement

    operator fun div(that: SpacialFrequency) = Action
    operator fun times(that: Distance) = Action

    operator fun times(that: Mass) = AngularMomentum

    operator fun div(that: VolumetricFlow) = AreaDensity

    operator fun div(that: MassFlowRate) = Distance

    operator fun div(that: Pace) = Energy
    operator fun times(that: Speed) = Energy

    operator fun div(that: Time) = Force
    operator fun times(that: Frequency) = Force

    operator fun div(that: Speed) = Mass
    operator fun times(that: Pace) = Mass

    operator fun div(that: Distance) = MassFlowRate
    operator fun times(that: SpacialFrequency) = MassFlowRate

    operator fun times(that: Absement) = MomentOfInertia

    operator fun div(that: Energy) = Pace

    operator fun times(that: Acceleration) = Power

    operator fun div(that: Absement) = RadiantExposure

    operator fun div(that: Action) = SpacialFrequency

    operator fun div(that: Mass) = Speed

    operator fun div(that: Force) = Time

    operator fun div(that: AreaDensity) = VolumetricFlow
}
