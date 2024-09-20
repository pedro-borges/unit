package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite()
object Scalar : Dimension
{
    operator fun div(that: Scalar) = Scalar
    operator fun times(that: Scalar) = Scalar

    operator fun times(that: Acceleration) = Acceleration

    operator fun times(that: Angle) = Angle

    operator fun times(that: AngularAcceleration) = AngularAcceleration

    operator fun times(that: AngularMomentum) = AngularMomentum

    operator fun times(that: AngularVelocity) = AngularVelocity

    operator fun times(that: Area) = Area

    operator fun times(that: Capacitance) = Capacitance

    operator fun times(that: Currency) = Currency

    operator fun times(that: Debt) = Debt

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

    operator fun times(that: Force) = Force

    operator fun div(that: Time) = Frequency
    operator fun times(that: Frequency) = Frequency

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

    operator fun times(that: MassFlowRate) = MassFlowRate

    operator fun times(that: Momentum) = Momentum

    operator fun div(that: Speed) = Pace
    operator fun times(that: Pace) = Pace

    operator fun times(that: Power) = Power

    operator fun times(that: Rent) = Rent

    operator fun times(that: Snap) = Snap

    operator fun times(that: SolidAngle) = SolidAngle

    operator fun times(that: SpecificVolume) = SpecificVolume

    operator fun div(that: Pace) = Speed
    operator fun times(that: Speed) = Speed

    operator fun times(that: Temperature) = Temperature

    operator fun div(that: Frequency) = Time
    operator fun times(that: Time) = Time

    operator fun times(that: Volume) = Volume

    operator fun times(that: VolumetricFlow) = VolumetricFlow

    operator fun times(that: Yank) = Yank
}
