package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(mass = 1,
           distance = -1)
object LinearDensity : Dimension
{
    operator fun div(that: Scalar) = LinearDensity
    operator fun times(that: Scalar) = LinearDensity

    operator fun div(that: LinearDensity) = Scalar

    operator fun times(that: VolumetricFlow) = Action

    operator fun times(that: Action) = AngularMomentum

    operator fun div(that: MassDensity) = Area
    operator fun times(that: SpecificVolume) = Area

    operator fun div(that: Distance) = AreaDensity
    operator fun times(that: SpacialFrequency) = AreaDensity

    operator fun div(that: AreaDensity) = Distance

    operator fun times(that: SpecificEnergy) = Force

    operator fun times(that: Jerk) = Intensity

    operator fun div(that: SpacialFrequency) = Mass
    operator fun times(that: Distance) = Mass

    operator fun div(that: Area) = MassDensity
    operator fun times(that: FuelEfficiency) = MassDensity

    operator fun div(that: Pace) = MassFlowRate
    operator fun times(that: Speed) = MassFlowRate

    operator fun times(that: Volume) = MomentOfInertia

    operator fun div(that: MassFlowRate) = Pace

    operator fun times(that: Acceleration) = RadiantExposure

    operator fun div(that: Mass) = SpacialFrequency
}
