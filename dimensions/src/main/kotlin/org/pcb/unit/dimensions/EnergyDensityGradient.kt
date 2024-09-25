package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object EnergyDensityGradient : Dimension
{
    override val composite = mapOf(Mass to 1,
                                   Distance to -2,
                                   Time to -2)

    operator fun div(that: Scalar) = EnergyDensityGradient
    operator fun times(that: Scalar) = EnergyDensityGradient

    operator fun div(that: EnergyDensityGradient) = Scalar

    operator fun div(that: MassDensity) = Acceleration
    operator fun times(that: SpecificVolume) = Acceleration

    operator fun div(that: MagneticFluxDensity) = ElectricCurrentDensity

    operator fun div(that: SpacialFrequency) = EnergyDensity
    operator fun times(that: Distance) = EnergyDensity

    operator fun times(that: Volume) = Force

    operator fun div(that: RadiantExposure) = FuelEfficiency

    operator fun div(that: ElectricCurrentDensity) = MagneticFluxDensity

    operator fun div(that: Acceleration) = MassDensity

    operator fun div(that: PowerDensity) = Pace

    operator fun div(that: Pace) = PowerDensity
    operator fun times(that: Speed) = PowerDensity

    operator fun div(that: FuelEfficiency) = RadiantExposure
    operator fun times(that: Area) = RadiantExposure

    operator fun div(that: EnergyDensity) = SpacialFrequency

    operator fun times(that: Absement) = Viscosity

    operator fun times(that: VolumetricFlow) = Yank
}
