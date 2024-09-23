package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(mass = 1,
           distance = -1,
           time = -2)
object EnergyDensity : Dimension
{
    operator fun div(that: Scalar) = EnergyDensity
    operator fun times(that: Scalar) = EnergyDensity

    operator fun div(that: EnergyDensity) = Scalar

    operator fun div(that: AreaDensity) = Acceleration

    operator fun div(that: Acceleration) = AreaDensity

    operator fun times(that: Volume) = Energy

    operator fun div(that: Pace) = EnergyFluxDensity
    operator fun times(that: Speed) = EnergyFluxDensity

    operator fun div(that: FuelEfficiency) = Force
    operator fun times(that: Area) = Force

    operator fun div(that: Force) = FuelEfficiency

    operator fun div(that: SpecificEnergy) = MassDensity

    operator fun div(that: EnergyFluxDensity) = Pace

    operator fun times(that: VolumetricFlow) = Power

    operator fun div(that: Time) = PowerDensity
    operator fun times(that: Frequency) = PowerDensity

    operator fun div(that: SpacialFrequency) = RadiantExposure
    operator fun times(that: Distance) = RadiantExposure

    operator fun div(that: RadiantExposure) = SpacialFrequency

    operator fun div(that: MassDensity) = SpecificEnergy
    operator fun times(that: SpecificVolume) = SpecificEnergy

    operator fun div(that: PowerDensity) = Time
}
