package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(luminousIntensity = 1)
object LuminousIntensity : Dimension
{
    operator fun div(that: Scalar) = LuminousIntensity
    operator fun times(that: Scalar) = LuminousIntensity

    operator fun div(that: LuminousIntensity) = Scalar

    operator fun div(that: Luminance) = Area

    operator fun div(that: Area) = Luminance

    operator fun times(that: SolidAngle) = LuminousFlux
}
