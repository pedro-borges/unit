package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object Frequency : Dimension
{
    override val composite = mapOf(Time to -1)

    operator fun div(that: Scalar) = Frequency
    operator fun times(that: Scalar) = Frequency

    operator fun div(that: Frequency) = Scalar
    operator fun times(that: Time) = Scalar

    operator fun div(that: Pace) = Acceleration
    operator fun times(that: Speed) = Acceleration

    operator fun times(that: MomentOfInertia) = Action

    operator fun times(that: AngularVelocity) = AngularAcceleration

    operator fun times(that: Angle) = AngularVelocity

    operator fun div(that: Molarity) = CatalyticEfficiency
    operator fun times(that: MolarVolume) = CatalyticEfficiency

    operator fun times(that: Debt) = Currency

    operator fun times(that: Capacitance) = ElectricConductance

    operator fun times(that: ElectricCharge) = ElectricCurrent

    operator fun times(that: Inductance) = ElectricResistance

    operator fun times(that: MagneticFlux) = ElectricTension

    operator fun times(that: Action) = Energy

    operator fun times(that: Momentum) = Force

    operator fun times(that: RadiantExposure) = Intensity

    operator fun times(that: Acceleration) = Jerk

    operator fun times(that: LuminousEnergy) = LuminousFlux

    operator fun times(that: Mass) = MassFlowRate

    operator fun div(that: ReactionRate) = MolarVolume

    operator fun div(that: CatalyticEfficiency) = Molarity

    operator fun div(that: Acceleration) = Pace

    operator fun times(that: Energy) = Power

    operator fun times(that: EnergyDensity) = PowerDensity

    operator fun times(that: MassFlowRate) = RadiantExposure

    operator fun div(that: MolarVolume) = ReactionRate
    operator fun times(that: Molarity) = ReactionRate

    operator fun times(that: Currency) = Rent

    operator fun times(that: Jerk) = Snap

    operator fun div(that: Speed) = SpacialFrequency
    operator fun times(that: Pace) = SpacialFrequency

    operator fun div(that: SpacialFrequency) = Speed
    operator fun times(that: Distance) = Speed

    operator fun times(that: Volume) = VolumetricFlow

    operator fun times(that: Force) = Yank
}
