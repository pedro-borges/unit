package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(distance = 1,
           mass = 1,
           angle = -2,
           time = -3)
object SpectralIntensity : Dimension
{
    operator fun div(that: Scalar) = SpectralIntensity
    operator fun times(that: Scalar) = SpectralIntensity

    operator fun div(that: SpectralIntensity) = Scalar

    operator fun div(that: SpacialFrequency) = RadiantIntensity
    operator fun times(that: Distance) = RadiantIntensity

    operator fun div(that: RadiantIntensity) = SpacialFrequency

    operator fun times(that: SolidAngle) = Yank
}
