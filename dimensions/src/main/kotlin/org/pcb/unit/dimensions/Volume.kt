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
    operator fun times(that: SpacialFrequency) = Area

    operator fun div(that: Area) = Distance
    operator fun times(that: FuelEfficiency) = Distance

    operator fun times(that: EnergyDensity) = Energy

    operator fun div(that: SpecificVolume) = MassDensity
    operator fun times(that: Mass) = MassDensity

    operator fun times(that: PowerDensity) = Power

    operator fun div(that: MassDensity) = SpecificVolume

    operator fun div(that: VolumetricFlow) = Time

    operator fun div(that: Time) = VolumetricFlow
    operator fun times(that: Frequency) = VolumetricFlow
}
