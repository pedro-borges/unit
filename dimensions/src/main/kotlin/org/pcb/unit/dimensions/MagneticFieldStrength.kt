package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object MagneticFieldStrength : Dimension
{
    override val composite = mapOf(ElectricCurrent to 1,
                                   Distance to -1)

    operator fun div(that: Scalar) = MagneticFieldStrength
    operator fun times(that: Scalar) = MagneticFieldStrength

    operator fun div(that: MagneticFieldStrength) = Scalar

    operator fun div(that: ElectricCurrentDensity) = Distance

    operator fun times(that: Absement) = ElectricCharge

    operator fun div(that: ElectricFieldStrength) = ElectricConductance

    operator fun div(that: ElectricTension) = ElectricConductivity

    operator fun div(that: SpacialFrequency) = ElectricCurrent
    operator fun times(that: Distance) = ElectricCurrent

    operator fun div(that: Distance) = ElectricCurrentDensity
    operator fun times(that: SpacialFrequency) = ElectricCurrentDensity

    operator fun div(that: Speed) = ElectricDisplacementField
    operator fun times(that: Pace) = ElectricDisplacementField

    operator fun div(that: ElectricConductance) = ElectricFieldStrength
    operator fun times(that: ElectricResistance) = ElectricFieldStrength

    operator fun div(that: ElectricConductivity) = ElectricTension

    operator fun times(that: MagneticFluxDensity) = EnergyDensity

    operator fun times(that: MagneticFlux) = Force

    operator fun times(that: ElectricFieldStrength) = Intensity

    operator fun times(that: Volume) = MagneticDipoleMoment

    operator fun times(that: Permeability) = MagneticFluxDensity

    operator fun div(that: ElectricCurrent) = SpacialFrequency

    operator fun div(that: ElectricDisplacementField) = Speed

    operator fun times(that: ElectricTension) = Yank
}
