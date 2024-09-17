package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(luminousIntensity = 1,
           distance = -2)
object Luminance : Dimension
{
    operator fun div(that: Scalar) = Luminance
    operator fun times(that: Scalar) = Luminance

    operator fun div(that: Luminance) = Scalar

    operator fun times(that: SolidAngle) = Illuminance

    operator fun times(that: Area) = LuminousIntensity
}