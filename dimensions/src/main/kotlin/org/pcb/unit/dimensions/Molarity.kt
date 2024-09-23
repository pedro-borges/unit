package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(amountOfSubstance = 1,
           distance = -3)
object Molarity : Dimension
{
    operator fun div(that: Scalar) = Molarity
    operator fun times(that: Scalar) = Molarity

    operator fun div(that: Molarity) = Scalar
    operator fun times(that: MolarVolume) = Scalar

    operator fun times(that: Volume) = AmountOfSubstance

    operator fun times(that: MolarEnergy) = EnergyDensity

    operator fun times(that: CatalyticEfficiency) = Frequency

    operator fun div(that: Molality) = MassDensity
    operator fun times(that: MolarMass) = MassDensity

    operator fun div(that: MassDensity) = Molality
    operator fun times(that: SpecificVolume) = Molality
}
