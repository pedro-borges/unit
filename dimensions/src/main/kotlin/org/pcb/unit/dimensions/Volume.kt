package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(distance = 3)
object Volume : Dimension
{
    operator fun div(that: Scalar) = Volume
    operator fun times(that: Scalar) = Volume

    operator fun div(that: Volume) = Scalar

    operator fun div(that: Distance) = Area

    operator fun div(that: Area) = Distance

    operator fun times(that: EnergyDensity) = Energy

    operator fun div(that: SpecificVolume) = Mass

    operator fun div(that: Mass) = SpecificVolume

    operator fun div(that: VolumetricFlow) = Time

    operator fun div(that: Time) = VolumetricFlow
    operator fun times(that: Frequency) = VolumetricFlow
}
