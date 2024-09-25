package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object ElectricCharge : Dimension
{
    override val composite = mapOf(ElectricCurrent to 1,
                                   Time to 1)

    operator fun div(that: Scalar) = ElectricCharge
    operator fun times(that: Scalar) = ElectricCharge

    operator fun div(that: ElectricCharge) = Scalar

    operator fun div(that: MagneticFieldStrength) = Absement

    operator fun times(that: MagneticFlux) = Action

    operator fun div(that: ElectricDisplacementField) = Area

    operator fun div(that: ElectricTension) = Capacitance

    operator fun div(that: MagneticFlux) = ElectricConductance

    operator fun div(that: Time) = ElectricCurrent
    operator fun times(that: Frequency) = ElectricCurrent

    operator fun div(that: SpacialFrequency) = ElectricDipoleMoment
    operator fun times(that: Distance) = ElectricDipoleMoment

    operator fun div(that: Area) = ElectricDisplacementField
    operator fun times(that: FuelEfficiency) = ElectricDisplacementField

    operator fun div(that: Capacitance) = ElectricTension

    operator fun times(that: ElectricTension) = Energy

    operator fun times(that: ElectricFieldStrength) = Force

    operator fun div(that: Absement) = MagneticFieldStrength

    operator fun div(that: ElectricConductance) = MagneticFlux
    operator fun times(that: ElectricResistance) = MagneticFlux

    operator fun times(that: MagneticFluxDensity) = MassFlowRate

    operator fun div(that: ElectricDipoleMoment) = SpacialFrequency

    operator fun div(that: ElectricCurrent) = Time
}
