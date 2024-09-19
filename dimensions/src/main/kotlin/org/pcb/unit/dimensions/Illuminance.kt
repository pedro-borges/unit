package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(angle = 2,
           luminousIntensity = 1,
           distance = -2)
object Illuminance : Dimension
{
    operator fun div(that: Scalar) = Illuminance
    operator fun times(that: Scalar) = Illuminance

    operator fun div(that: Illuminance) = Scalar

    operator fun div(that: SolidAngle) = Luminance

    operator fun times(that: Area) = LuminousFlux

    operator fun div(that: Luminance) = SolidAngle
}
