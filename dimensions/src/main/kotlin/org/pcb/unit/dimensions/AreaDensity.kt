package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(mass = 1,
           distance = -2)
object AreaDensity : Dimension
{
    operator fun div(that: Scalar) = AreaDensity
    operator fun times(that: Scalar) = AreaDensity

    operator fun div(that: AreaDensity) = Scalar

    operator fun times(that: SpecificVolume) = Distance

    operator fun times(that: Acceleration) = EnergyDensity

    operator fun times(that: Area) = Mass

    operator fun times(that: VolumetricFlow) = Momentum
}
