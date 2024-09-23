package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(substance = 1)
object Substance : Dimension
{
    operator fun div(that: Scalar) = Substance
    operator fun times(that: Scalar) = Substance

    operator fun div(that: Substance) = Scalar

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
