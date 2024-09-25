package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object MolarEnergy : Dimension
{
    override val composite = mapOf(Distance to 2,
                                   Mass to 1,
                                   Substance to -1,
                                   Time to -2)

    operator fun div(that: Scalar) = MolarEnergy
    operator fun times(that: Scalar) = MolarEnergy

    operator fun div(that: MolarEnergy) = Scalar

    operator fun div(that: Viscosity) = CatalyticEfficiency

    operator fun div(that: ReciprocalSubstance) = Energy
    operator fun times(that: Substance) = Energy

    operator fun div(that: MolarVolume) = EnergyDensity
    operator fun times(that: Molarity) = EnergyDensity

    operator fun div(that: Temperature) = MolarHeatCapacity
    operator fun times(that: ThermalExpansionCoefficient) = MolarHeatCapacity

    operator fun div(that: SpecificEnergy) = MolarMass

    operator fun div(that: EnergyDensity) = MolarVolume

    operator fun times(that: ReactionRate) = PowerDensity

    operator fun div(that: Energy) = ReciprocalSubstance

    operator fun div(that: MolarMass) = SpecificEnergy
    operator fun times(that: Molality) = SpecificEnergy

    operator fun div(that: MolarHeatCapacity) = Temperature

    operator fun div(that: CatalyticEfficiency) = Viscosity
}
