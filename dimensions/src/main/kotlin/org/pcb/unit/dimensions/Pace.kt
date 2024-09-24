package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(time = 1,
           distance = -1)
object Pace : Dimension
{
    operator fun div(that: Scalar) = Pace
    operator fun times(that: Scalar) = Pace

    operator fun div(that: Pace) = Scalar
    operator fun times(that: Speed) = Scalar

    operator fun times(that: VolumetricFlow) = Area

    operator fun div(that: Permeability) = ElectricConductance

    operator fun div(that: Permittivity) = ElectricResistance

    operator fun times(that: Intensity) = EnergyDensity

    operator fun times(that: Power) = Force

    operator fun times(that: Acceleration) = Frequency

    operator fun times(that: MassFlowRate) = LinearDensity

    operator fun times(that: Momentum) = Mass

    operator fun times(that: Force) = MassFlowRate

    operator fun times(that: Energy) = Momentum

    operator fun div(that: ElectricConductance) = Permeability
    operator fun times(that: ElectricResistance) = Permeability

    operator fun div(that: ElectricResistance) = Permittivity
    operator fun times(that: ElectricConductance) = Permittivity

    operator fun times(that: Yank) = RadiantExposure

    operator fun div(that: Time) = SpacialFrequency
    operator fun times(that: Frequency) = SpacialFrequency

    operator fun times(that: SpecificEnergy) = Speed

    operator fun div(that: SpacialFrequency) = Time
    operator fun times(that: Distance) = Time
}
