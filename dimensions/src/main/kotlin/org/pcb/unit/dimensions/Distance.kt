package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(distance = 1)
object Distance : Dimension
{
    operator fun div(that: Scalar) = Distance
    operator fun times(that: Scalar) = Distance

    operator fun div(that: Distance) = Scalar
    operator fun times(that: SpacialFrequency) = Scalar

    operator fun times(that: Momentum) = Action

    operator fun div(that: SpacialFrequency) = Area
    operator fun times(that: Distance) = Area

    operator fun div(that: SpecificVolume) = AreaDensity
    operator fun times(that: MassDensity) = AreaDensity

    operator fun times(that: Force) = Energy

    operator fun times(that: PowerDensity) = EnergyFluxDensity

    operator fun times(that: RadiantExposure) = Force

    operator fun div(that: Volume) = FuelEfficiency

    operator fun times(that: MassFlowRate) = Momentum

    operator fun times(that: Yank) = Power

    operator fun times(that: EnergyDensity) = RadiantExposure

    operator fun times(that: SpectralIntensity) = RadiantIntensity

    operator fun div(that: Area) = SpacialFrequency
    operator fun times(that: FuelEfficiency) = SpacialFrequency

    operator fun times(that: Acceleration) = SpecificEnergy

    operator fun div(that: AreaDensity) = SpecificVolume

    operator fun div(that: Time) = Speed
    operator fun times(that: Frequency) = Speed

    operator fun times(that: TemperatureGradient) = Temperature

    operator fun div(that: Speed) = Time
    operator fun times(that: Pace) = Time

    operator fun div(that: FuelEfficiency) = Volume
    operator fun times(that: Area) = Volume

    operator fun times(that: EnergyFluxDensity) = Yank
}
