package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(time = 1)
object Time : Dimension
{
    operator fun div(that: Scalar) = Time
    operator fun times(that: Scalar) = Time

    operator fun div(that: Time) = Scalar
    operator fun times(that: Frequency) = Scalar

    operator fun times(that: Jerk) = Acceleration

    operator fun times(that: Energy) = Action

    operator fun times(that: AngularVelocity) = Angle

    operator fun times(that: AngularAcceleration) = AngularVelocity

    operator fun div(that: ElectricResistance) = Capacitance
    operator fun times(that: ElectricConductance) = Capacitance

    operator fun times(that: Rent) = Currency

    operator fun times(that: Currency) = Debt

    operator fun div(that: Pace) = Distance
    operator fun times(that: Speed) = Distance

    operator fun times(that: ElectricCurrent) = ElectricCharge

    operator fun div(that: Inductance) = ElectricConductance

    operator fun div(that: Capacitance) = ElectricResistance

    operator fun times(that: Power) = Energy

    operator fun times(that: PowerDensity) = EnergyDensity

    operator fun times(that: Yank) = Force

    operator fun div(that: ElectricConductance) = Inductance
    operator fun times(that: ElectricResistance) = Inductance

    operator fun times(that: Snap) = Jerk

    operator fun times(that: LuminousFlux) = LuminousEnergy

    operator fun times(that: ElectricTension) = MagneticFlux

    operator fun times(that: MassFlowRate) = Mass

    operator fun times(that: RadiantExposure) = MassFlowRate

    operator fun times(that: CatalyticEfficiency) = MolarVolume

    operator fun times(that: ReactionRate) = Molarity

    operator fun times(that: Action) = MomentOfInertia

    operator fun times(that: Force) = Momentum

    operator fun div(that: Distance) = Pace
    operator fun times(that: SpacialFrequency) = Pace

    operator fun times(that: Intensity) = RadiantExposure

    operator fun times(that: Acceleration) = Speed

    operator fun times(that: VolumetricFlow) = Volume
}
