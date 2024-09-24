package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(substance = 1,
           time = -1,
           distance = -3)
object ReactionRate : Dimension
{
    operator fun div(that: Scalar) = ReactionRate
    operator fun times(that: Scalar) = ReactionRate

    operator fun div(that: ReactionRate) = Scalar

    operator fun div(that: Molarity) = Frequency
    operator fun times(that: MolarVolume) = Frequency

    operator fun div(that: Frequency) = Molarity
    operator fun times(that: Time) = Molarity

    operator fun times(that: MolarEnergy) = PowerDensity
}
