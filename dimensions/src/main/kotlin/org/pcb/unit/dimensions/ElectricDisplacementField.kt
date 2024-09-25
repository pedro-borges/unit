package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object ElectricDisplacementField : Dimension
{
    override val composite = mapOf(ElectricCurrent to 1,
                                   Time to 1,
                                   Distance to -2)

    operator fun div(that: Scalar) = ElectricDisplacementField
    operator fun times(that: Scalar) = ElectricDisplacementField

    operator fun div(that: ElectricDisplacementField) = Scalar

    operator fun div(that: FuelEfficiency) = ElectricCharge
    operator fun times(that: Area) = ElectricCharge

    operator fun div(that: MagneticFluxDensity) = ElectricConductance

    operator fun div(that: Time) = ElectricCurrentDensity
    operator fun times(that: Frequency) = ElectricCurrentDensity

    operator fun times(that: Volume) = ElectricDipoleMoment

    operator fun div(that: Permittivity) = ElectricFieldStrength

    operator fun times(that: ElectricFieldStrength) = EnergyDensity

    operator fun div(that: ElectricCharge) = FuelEfficiency

    operator fun div(that: Pace) = MagneticFieldStrength
    operator fun times(that: Speed) = MagneticFieldStrength

    operator fun div(that: ElectricConductance) = MagneticFluxDensity
    operator fun times(that: ElectricResistance) = MagneticFluxDensity

    operator fun times(that: MagneticFlux) = MassFlowRate

    operator fun div(that: MagneticFieldStrength) = Pace

    operator fun div(that: ElectricFieldStrength) = Permittivity

    operator fun times(that: ElectricTension) = RadiantExposure

    operator fun div(that: ElectricCurrentDensity) = Time
}
