package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(angle = 2)
object SolidAngle : Dimension
{
    operator fun div(that: Scalar) = SolidAngle
    operator fun times(that: Scalar) = SolidAngle

    operator fun div(that: SolidAngle) = Scalar

    operator fun div(that: Angle) = Angle

    operator fun times(that: Luminance) = Illuminance

    operator fun times(that: LuminousIntensity) = LuminousFlux
}