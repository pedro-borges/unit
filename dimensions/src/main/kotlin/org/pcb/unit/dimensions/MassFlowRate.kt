package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(mass = 1,
           time = -1)
object MassFlowRate : Dimension
{
    operator fun div(that: Scalar) = MassFlowRate
    operator fun times(that: Scalar) = MassFlowRate

    operator fun div(that: MassFlowRate) = Scalar

    operator fun div(that: FuelEfficiency) = Action
    operator fun times(that: Area) = Action

    operator fun div(that: MagneticFluxDensity) = ElectricCharge

    operator fun div(that: Pace) = Force
    operator fun times(that: Speed) = Force

    operator fun div(that: Mass) = Frequency

    operator fun div(that: Action) = FuelEfficiency

    operator fun div(that: Speed) = LinearDensity
    operator fun times(that: Pace) = LinearDensity

    operator fun div(that: ElectricCharge) = MagneticFluxDensity

    operator fun div(that: Frequency) = Mass
    operator fun times(that: Time) = Mass

    operator fun div(that: VolumetricFlow) = MassDensity

    operator fun div(that: SpacialFrequency) = Momentum
    operator fun times(that: Distance) = Momentum

    operator fun div(that: Force) = Pace

    operator fun times(that: SpecificEnergy) = Power

    operator fun div(that: Time) = RadiantExposure
    operator fun times(that: Frequency) = RadiantExposure

    operator fun div(that: Momentum) = SpacialFrequency

    operator fun div(that: LinearDensity) = Speed

    operator fun div(that: RadiantExposure) = Time

    operator fun div(that: MassDensity) = VolumetricFlow
    operator fun times(that: SpecificVolume) = VolumetricFlow

    operator fun times(that: Acceleration) = Yank
}
