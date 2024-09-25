package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object ElectricFieldStrength : Dimension
{
    override val composite = mapOf(Distance to 1,
                                   Mass to 1,
                                   ElectricCurrent to -1,
                                   Time to -3)

    operator fun div(that: Scalar) = ElectricFieldStrength
    operator fun times(that: Scalar) = ElectricFieldStrength

    operator fun div(that: ElectricFieldStrength) = Scalar

    operator fun times(that: ElectricConductivity) = ElectricCurrentDensity

    operator fun times(that: Permittivity) = ElectricDisplacementField

    operator fun div(that: MagneticFieldStrength) = ElectricResistance

    operator fun div(that: SpacialFrequency) = ElectricTension
    operator fun times(that: Distance) = ElectricTension

    operator fun times(that: ElectricDipoleMoment) = Energy

    operator fun times(that: ElectricDisplacementField) = EnergyDensity

    operator fun times(that: ElectricCharge) = Force

    operator fun times(that: MagneticFieldStrength) = Intensity

    operator fun div(that: ElectricResistance) = MagneticFieldStrength
    operator fun times(that: ElectricConductance) = MagneticFieldStrength

    operator fun times(that: Absement) = MagneticFlux

    operator fun div(that: Speed) = MagneticFluxDensity
    operator fun times(that: Pace) = MagneticFluxDensity

    operator fun times(that: ElectricCurrentDensity) = PowerDensity

    operator fun div(that: ElectricTension) = SpacialFrequency

    operator fun div(that: MagneticFluxDensity) = Speed

    operator fun times(that: ElectricCurrent) = Yank
}
