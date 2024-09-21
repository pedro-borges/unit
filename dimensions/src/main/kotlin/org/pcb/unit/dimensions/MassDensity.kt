package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(mass = 1)
object MassDensity : Dimension
{
    operator fun div(that: Scalar) = MassDensity
    operator fun times(that: Scalar) = MassDensity

    operator fun div(that: MassDensity) = Scalar

    operator fun times(that: Momentum) = AngularMomentum

    operator fun div(that: AreaDensity) = Area

    operator fun div(that: Area) = AreaDensity
    operator fun times(that: FuelEfficiency) = AreaDensity

    operator fun times(that: SpecificEnergy) = Energy

    operator fun times(that: Acceleration) = Force

    operator fun div(that: MomentOfInertia) = FuelEfficiency

    operator fun div(that: Volume) = Mass

    operator fun div(that: Time) = MassFlowRate
    operator fun times(that: Frequency) = MassFlowRate

    operator fun div(that: FuelEfficiency) = MomentOfInertia
    operator fun times(that: Area) = MomentOfInertia

    operator fun div(that: Pace) = Momentum
    operator fun times(that: Speed) = Momentum

    operator fun div(that: Momentum) = Pace

    operator fun div(that: MassFlowRate) = Time

    operator fun div(that: Mass) = Volume
    operator fun times(that: SpecificVolume) = Volume

    operator fun times(that: Jerk) = Yank
}
