package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(distance = 2,
           mass = 1,
           substance = -1,
           time = -2)
object MolarEnergy : Dimension
{
    operator fun div(that: Scalar) = MolarEnergy
    operator fun times(that: Scalar) = MolarEnergy

    operator fun div(that: MolarEnergy) = Scalar

    operator fun times(that: Substance) = Energy

    operator fun div(that: MolarVolume) = EnergyDensity
    operator fun times(that: Molarity) = EnergyDensity

    operator fun div(that: Temperature) = MolarHeatCapacity
    operator fun times(that: ThermalExpansionCoefficient) = MolarHeatCapacity

    operator fun div(that: SpecificEnergy) = MolarMass

    operator fun div(that: EnergyDensity) = MolarVolume

    operator fun times(that: ReactionRate) = PowerDensity

    operator fun div(that: MolarMass) = SpecificEnergy
    operator fun times(that: Molality) = SpecificEnergy

    operator fun div(that: MolarHeatCapacity) = Temperature
}
