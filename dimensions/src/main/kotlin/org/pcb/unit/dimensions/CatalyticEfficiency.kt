package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(distance = 3,
           substance = -1,
           time = -1)
object CatalyticEfficiency : Dimension
{
    operator fun div(that: Scalar) = CatalyticEfficiency
    operator fun times(that: Scalar) = CatalyticEfficiency

    operator fun div(that: CatalyticEfficiency) = Scalar

    operator fun div(that: MolarVolume) = Frequency
    operator fun times(that: Molarity) = Frequency

    operator fun times(that: Permittivity) = MolarConductivity

    operator fun div(that: Frequency) = MolarVolume
    operator fun times(that: Time) = MolarVolume

    operator fun times(that: Substance) = VolumetricFlow
}
