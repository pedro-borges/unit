package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object RadiantExposure : Dimension
{
    override val composite = mapOf(Mass to 1,
                                   Time to -2)

    operator fun div(that: Scalar) = RadiantExposure
    operator fun times(that: Scalar) = RadiantExposure

    operator fun div(that: RadiantExposure) = Scalar

    operator fun div(that: PowerDensity) = Absement

    operator fun div(that: LinearDensity) = Acceleration

    operator fun div(that: EnergyDensityGradient) = Area

    operator fun div(that: SpecificEnergy) = AreaDensity

    operator fun div(that: EnergyDensity) = Distance

    operator fun div(that: MagneticFluxDensity) = ElectricCurrent

    operator fun div(that: MagneticFlux) = ElectricCurrentDensity

    operator fun div(that: ElectricTension) = ElectricDisplacementField

    operator fun div(that: ElectricDisplacementField) = ElectricTension

    operator fun div(that: FuelEfficiency) = Energy
    operator fun times(that: Area) = Energy

    operator fun div(that: Distance) = EnergyDensity
    operator fun times(that: SpacialFrequency) = EnergyDensity

    operator fun div(that: Area) = EnergyDensityGradient
    operator fun times(that: FuelEfficiency) = EnergyDensityGradient

    operator fun div(that: SpacialFrequency) = Force
    operator fun times(that: Distance) = Force

    operator fun div(that: MassFlowRate) = Frequency

    operator fun div(that: Energy) = FuelEfficiency

    operator fun div(that: Time) = Intensity
    operator fun times(that: Frequency) = Intensity

    operator fun div(that: Acceleration) = LinearDensity

    operator fun div(that: ElectricCurrentDensity) = MagneticFlux

    operator fun div(that: ElectricCurrent) = MagneticFluxDensity

    operator fun div(that: Frequency) = MassFlowRate
    operator fun times(that: Time) = MassFlowRate

    operator fun times(that: Absement) = Momentum

    operator fun div(that: Yank) = Pace

    operator fun div(that: Absement) = PowerDensity

    operator fun div(that: Force) = SpacialFrequency

    operator fun div(that: AreaDensity) = SpecificEnergy

    operator fun div(that: Viscosity) = Speed

    operator fun div(that: Intensity) = Time

    operator fun div(that: Speed) = Viscosity
    operator fun times(that: Pace) = Viscosity

    operator fun div(that: Pace) = Yank
    operator fun times(that: Speed) = Yank
}
