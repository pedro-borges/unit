package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(amountOfSubstance = 1)
object AmountOfSubstance : Dimension
{
    operator fun div(that: Scalar) = AmountOfSubstance
    operator fun times(that: Scalar) = AmountOfSubstance

    operator fun div(that: AmountOfSubstance) = Scalar

    operator fun times(that: MolarEnergy) = Energy

    operator fun times(that: MolarHeatCapacity) = HeatCapacity

    operator fun div(that: Molality) = Mass
    operator fun times(that: MolarMass) = Mass

    operator fun div(that: Mass) = Molality

    operator fun div(that: Volume) = Molarity

    operator fun div(that: Molarity) = Volume
    operator fun times(that: MolarVolume) = Volume

    operator fun times(that: CatalyticEfficiency) = VolumetricFlow
}
