package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object ElectricCurrentDensity : Dimension
{
    override val composite = mapOf(ElectricCurrent to 1,
                                   Distance to -2)

    operator fun div(that: Scalar) = ElectricCurrentDensity
    operator fun times(that: Scalar) = ElectricCurrentDensity

    operator fun div(that: ElectricCurrentDensity) = Scalar

    operator fun div(that: ElectricFieldStrength) = ElectricConductivity

    operator fun div(that: FuelEfficiency) = ElectricCurrent
    operator fun times(that: Area) = ElectricCurrent

    operator fun div(that: Frequency) = ElectricDisplacementField
    operator fun times(that: Time) = ElectricDisplacementField

    operator fun div(that: ElectricConductivity) = ElectricFieldStrength

    operator fun times(that: MagneticFluxDensity) = EnergyDensityGradient

    operator fun div(that: ElectricDisplacementField) = Frequency

    operator fun div(that: ElectricCurrent) = FuelEfficiency

    operator fun times(that: ElectricTension) = Intensity

    operator fun div(that: SpacialFrequency) = MagneticFieldStrength
    operator fun times(that: Distance) = MagneticFieldStrength

    operator fun times(that: Inductance) = MagneticFluxDensity

    operator fun times(that: ElectricFieldStrength) = PowerDensity

    operator fun times(that: MagneticFlux) = RadiantExposure

    operator fun div(that: MagneticFieldStrength) = SpacialFrequency
}
