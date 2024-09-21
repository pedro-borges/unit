package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(mass = 1,
           time = -3)
object EnergyFluxDensity : Dimension
{
    operator fun div(that: Scalar) = EnergyFluxDensity
    operator fun times(that: Scalar) = EnergyFluxDensity

    operator fun div(that: EnergyFluxDensity) = Scalar

    operator fun div(that: PowerDensity) = Distance

    operator fun div(that: Speed) = EnergyDensity
    operator fun times(that: Pace) = EnergyDensity

    operator fun div(that: RadiantExposure) = Frequency

    operator fun div(that: Power) = FuelEfficiency

    operator fun div(that: FuelEfficiency) = Power
    operator fun times(that: Area) = Power

    operator fun div(that: Distance) = PowerDensity
    operator fun times(that: SpacialFrequency) = PowerDensity

    operator fun div(that: Frequency) = RadiantExposure
    operator fun times(that: Time) = RadiantExposure

    operator fun div(that: Yank) = SpacialFrequency

    operator fun div(that: EnergyDensity) = Speed

    operator fun div(that: SpacialFrequency) = Yank
    operator fun times(that: Distance) = Yank
}
