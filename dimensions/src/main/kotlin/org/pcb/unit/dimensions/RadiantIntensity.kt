package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(distance = 2,
           mass = 1,
           angle = -2,
           time = -3)
object RadiantIntensity : Dimension
{
    operator fun div(that: Scalar) = RadiantIntensity
    operator fun times(that: Scalar) = RadiantIntensity

    operator fun div(that: RadiantIntensity) = Scalar

    operator fun div(that: SpectralIntensity) = Distance

    operator fun times(that: SolidAngle) = Power

    operator fun div(that: Distance) = SpectralIntensity
    operator fun times(that: SpacialFrequency) = SpectralIntensity
}
