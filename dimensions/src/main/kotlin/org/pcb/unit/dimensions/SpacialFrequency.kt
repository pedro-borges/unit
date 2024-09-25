package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object SpacialFrequency : Dimension
{
    override val composite = mapOf(Distance to -1)

    operator fun div(that: Scalar) = SpacialFrequency
    operator fun times(that: Scalar) = SpacialFrequency

    operator fun div(that: SpacialFrequency) = Scalar
    operator fun times(that: Distance) = Scalar

    operator fun times(that: SpecificEnergy) = Acceleration

    operator fun times(that: Volume) = Area

    operator fun times(that: LinearDensity) = AreaDensity

    operator fun div(that: FuelEfficiency) = Distance
    operator fun times(that: Area) = Distance

    operator fun times(that: ElectricDipoleMoment) = ElectricCharge

    operator fun div(that: ElectricResistance) = ElectricConductivity
    operator fun times(that: ElectricConductance) = ElectricConductivity

    operator fun times(that: MagneticFieldStrength) = ElectricCurrentDensity

    operator fun times(that: ElectricTension) = ElectricFieldStrength

    operator fun div(that: ElectricConductivity) = ElectricResistance

    operator fun times(that: RadiantExposure) = EnergyDensity

    operator fun times(that: EnergyDensity) = EnergyDensityGradient

    operator fun times(that: Energy) = Force

    operator fun div(that: Pace) = Frequency
    operator fun times(that: Speed) = Frequency

    operator fun div(that: Distance) = FuelEfficiency
    operator fun times(that: SpacialFrequency) = FuelEfficiency

    operator fun times(that: Yank) = Intensity

    operator fun times(that: Mass) = LinearDensity

    operator fun times(that: ElectricCurrent) = MagneticFieldStrength

    operator fun times(that: AreaDensity) = MassDensity

    operator fun times(that: Momentum) = MassFlowRate

    operator fun times(that: Action) = Momentum

    operator fun div(that: Frequency) = Pace
    operator fun times(that: Time) = Pace

    operator fun times(that: Inductance) = Permeability

    operator fun times(that: Capacitance) = Permittivity

    operator fun times(that: Intensity) = PowerDensity

    operator fun times(that: Force) = RadiantExposure

    operator fun times(that: RadiantIntensity) = SpectralIntensity

    operator fun div(that: ThermalExpansionCoefficient) = TemperatureGradient
    operator fun times(that: Temperature) = TemperatureGradient

    operator fun div(that: TemperatureGradient) = ThermalExpansionCoefficient

    operator fun times(that: Absement) = Time

    operator fun times(that: MassFlowRate) = Viscosity

    operator fun times(that: Power) = Yank
}
