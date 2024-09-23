package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(distance = 2,
           mass = 1,
           amountOfSubstance = -1,
           temperature = -1,
           time = -2)
object MolarHeatCapacity : Dimension
{
    operator fun div(that: Scalar) = MolarHeatCapacity
    operator fun times(that: Scalar) = MolarHeatCapacity

    operator fun div(that: MolarHeatCapacity) = Scalar

    operator fun times(that: AmountOfSubstance) = HeatCapacity

    operator fun div(that: ThermalExpansionCoefficient) = MolarEnergy
    operator fun times(that: Temperature) = MolarEnergy

    operator fun div(that: SpecificHeatCapacity) = MolarMass

    operator fun div(that: MolarMass) = SpecificHeatCapacity
    operator fun times(that: Molality) = SpecificHeatCapacity

    operator fun div(that: MolarEnergy) = ThermalExpansionCoefficient
}
