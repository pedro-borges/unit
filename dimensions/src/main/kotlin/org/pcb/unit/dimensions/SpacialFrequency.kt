package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(distance = -1)
object SpacialFrequency : Dimension
{
    operator fun div(that: Scalar) = SpacialFrequency
    operator fun times(that: Scalar) = SpacialFrequency

    operator fun div(that: SpacialFrequency) = Scalar
    operator fun times(that: Distance) = Scalar

    operator fun times(that: SpecificEnergy) = Acceleration

    operator fun times(that: Volume) = Area

    operator fun div(that: FuelEfficiency) = Distance
    operator fun times(that: Area) = Distance

    operator fun times(that: RadiantExposure) = EnergyDensity

    operator fun times(that: Yank) = EnergyFluxDensity

    operator fun times(that: Energy) = Force

    operator fun div(that: Pace) = Frequency
    operator fun times(that: Speed) = Frequency

    operator fun div(that: Distance) = FuelEfficiency
    operator fun times(that: SpacialFrequency) = FuelEfficiency

    operator fun times(that: AreaDensity) = Mass

    operator fun times(that: Momentum) = MassFlowRate

    operator fun times(that: Action) = Momentum

    operator fun div(that: Frequency) = Pace
    operator fun times(that: Time) = Pace

    operator fun times(that: EnergyFluxDensity) = PowerDensity

    operator fun times(that: Force) = RadiantExposure

    operator fun times(that: RadiantIntensity) = SpectralIntensity

    operator fun times(that: Power) = Yank
}
