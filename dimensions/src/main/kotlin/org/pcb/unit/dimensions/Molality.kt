package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(substance = 1,
           mass = -1)
object Molality : Dimension
{
    operator fun div(that: Scalar) = Molality
    operator fun times(that: Scalar) = Molality

    operator fun div(that: Molality) = Scalar
    operator fun times(that: MolarMass) = Scalar

    operator fun times(that: Mass) = Substance

    operator fun div(that: SpecificVolume) = Molarity
    operator fun times(that: MassDensity) = Molarity

    operator fun times(that: MolarEnergy) = SpecificEnergy

    operator fun times(that: MolarHeatCapacity) = SpecificHeatCapacity

    operator fun div(that: Molarity) = SpecificVolume
    operator fun times(that: MolarVolume) = SpecificVolume
}
