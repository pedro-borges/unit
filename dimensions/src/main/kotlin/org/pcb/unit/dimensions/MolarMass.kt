package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(mass = 1,
           amountOfSubstance = -1)
object MolarMass : Dimension
{
    operator fun div(that: Scalar) = MolarMass
    operator fun times(that: Scalar) = MolarMass

    operator fun div(that: MolarMass) = Scalar
    operator fun times(that: Molality) = Scalar

    operator fun times(that: AmountOfSubstance) = Mass

    operator fun div(that: MolarVolume) = MassDensity
    operator fun times(that: Molarity) = MassDensity

    operator fun times(that: SpecificEnergy) = MolarEnergy

    operator fun times(that: SpecificHeatCapacity) = MolarHeatCapacity

    operator fun div(that: MassDensity) = MolarVolume
    operator fun times(that: SpecificVolume) = MolarVolume
}
