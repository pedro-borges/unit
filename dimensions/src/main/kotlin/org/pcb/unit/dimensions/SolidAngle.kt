package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object SolidAngle : Dimension
{
    override val composite = mapOf(Angle to 2)

    operator fun div(that: Scalar) = SolidAngle
    operator fun times(that: Scalar) = SolidAngle

    operator fun div(that: SolidAngle) = Scalar

    operator fun div(that: Angle) = Angle

    operator fun times(that: Luminance) = Illuminance

    operator fun times(that: LuminousIntensity) = LuminousFlux

    operator fun times(that: RadiantIntensity) = Power

    operator fun times(that: SpectralIntensity) = Yank
}
