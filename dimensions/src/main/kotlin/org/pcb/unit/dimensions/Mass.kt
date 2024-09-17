package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(mass = 1)
object Mass : Dimension
{
    operator fun div(that: Scalar) = Mass
    operator fun times(that: Scalar) = Mass

    operator fun div(that: Mass) = Scalar

    operator fun times(that: Acceleration) = Force

    operator fun div(that: Time) = MassFlowRate
    operator fun times(that: Frequency) = MassFlowRate

    operator fun div(that: Pace) = Momentum
    operator fun times(that: Speed) = Momentum

    operator fun div(that: Momentum) = Pace

    operator fun div(that: MassFlowRate) = Time
}