package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite()
object Scalar : Dimension
{
    operator fun div(that: Scalar) = Scalar
    operator fun times(that: Scalar) = Scalar

    operator fun times(that: Acceleration) = Acceleration

    operator fun times(that: Action) = Action

    operator fun times(that: Angle) = Angle

    operator fun times(that: AngularAcceleration) = AngularAcceleration

    operator fun times(that: AngularMomentum) = AngularMomentum

    operator fun times(that: AngularVelocity) = AngularVelocity

    operator fun div(that: FuelEfficiency) = Area
    operator fun times(that: Area) = Area

    operator fun times(that: AreaDensity) = AreaDensity

    operator fun times(that: Capacitance) = Capacitance

    operator fun times(that: CatalyticEfficiency) = CatalyticEfficiency

    operator fun times(that: Currency) = Currency

    operator fun times(that: Debt) = Debt

    operator fun div(that: SpacialFrequency) = Distance
    operator fun times(that: Distance) = Distance

    operator fun times(that: ElectricCharge) = ElectricCharge

    operator fun div(that: ElectricResistance) = ElectricConductance
    operator fun times(that: ElectricConductance) = ElectricConductance

    operator fun times(that: ElectricCurrent) = ElectricCurrent

    operator fun div(that: ElectricConductance) = ElectricResistance
    operator fun times(that: ElectricResistance) = ElectricResistance

    operator fun times(that: ElectricTension) = ElectricTension

    operator fun times(that: Energy) = Energy

    operator fun times(that: EnergyDensity) = EnergyDensity

    operator fun times(that: EnergyFluxDensity) = EnergyFluxDensity

    operator fun times(that: Force) = Force

    operator fun div(that: Time) = Frequency
    operator fun times(that: Frequency) = Frequency

    operator fun div(that: Area) = FuelEfficiency
    operator fun times(that: FuelEfficiency) = FuelEfficiency

    operator fun times(that: HeatCapacity) = HeatCapacity

    operator fun times(that: Illuminance) = Illuminance

    operator fun times(that: Inductance) = Inductance

    operator fun times(that: Jerk) = Jerk

    operator fun times(that: Luminance) = Luminance

    operator fun times(that: LuminousEnergy) = LuminousEnergy

    operator fun times(that: LuminousFlux) = LuminousFlux

    operator fun times(that: LuminousIntensity) = LuminousIntensity

    operator fun times(that: MagneticFlux) = MagneticFlux

    operator fun times(that: MagneticFluxDensity) = MagneticFluxDensity

    operator fun times(that: Mass) = Mass

    operator fun div(that: SpecificVolume) = MassDensity
    operator fun times(that: MassDensity) = MassDensity

    operator fun times(that: MassFlowRate) = MassFlowRate

    operator fun div(that: MolarMass) = Molality
    operator fun times(that: Molality) = Molality

    operator fun times(that: MolarConductivity) = MolarConductivity

    operator fun times(that: MolarEnergy) = MolarEnergy

    operator fun times(that: MolarHeatCapacity) = MolarHeatCapacity

    operator fun div(that: Molality) = MolarMass
    operator fun times(that: MolarMass) = MolarMass

    operator fun div(that: Molarity) = MolarVolume
    operator fun times(that: MolarVolume) = MolarVolume

    operator fun div(that: MolarVolume) = Molarity
    operator fun times(that: Molarity) = Molarity

    operator fun times(that: MomentOfInertia) = MomentOfInertia

    operator fun times(that: Momentum) = Momentum

    operator fun div(that: Speed) = Pace
    operator fun times(that: Pace) = Pace

    operator fun times(that: Power) = Power

    operator fun times(that: PowerDensity) = PowerDensity

    operator fun times(that: RadiantExposure) = RadiantExposure

    operator fun times(that: RadiantIntensity) = RadiantIntensity

    operator fun times(that: Rent) = Rent

    operator fun times(that: Snap) = Snap

    operator fun times(that: SolidAngle) = SolidAngle

    operator fun div(that: Distance) = SpacialFrequency
    operator fun times(that: SpacialFrequency) = SpacialFrequency

    operator fun times(that: SpecificEnergy) = SpecificEnergy

    operator fun times(that: SpecificHeatCapacity) = SpecificHeatCapacity

    operator fun div(that: MassDensity) = SpecificVolume
    operator fun times(that: SpecificVolume) = SpecificVolume

    operator fun times(that: SpectralIntensity) = SpectralIntensity

    operator fun div(that: Pace) = Speed
    operator fun times(that: Speed) = Speed

    operator fun times(that: Substance) = Substance

    operator fun div(that: ThermalExpansionCoefficient) = Temperature
    operator fun times(that: Temperature) = Temperature

    operator fun times(that: TemperatureGradient) = TemperatureGradient

    operator fun times(that: ThermalConductivity) = ThermalConductivity

    operator fun div(that: Temperature) = ThermalExpansionCoefficient
    operator fun times(that: ThermalExpansionCoefficient) = ThermalExpansionCoefficient

    operator fun times(that: ThermalResistance) = ThermalResistance

    operator fun div(that: Frequency) = Time
    operator fun times(that: Time) = Time

    operator fun times(that: Volume) = Volume

    operator fun times(that: VolumetricFlow) = VolumetricFlow

    operator fun times(that: Yank) = Yank
}
