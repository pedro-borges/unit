package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object SpectralIntensity : Dimension
{
    override val composite = mapOf(Distance to 1,
                                   Mass to 1,
                                   Angle to -2,
                                   Time to -3)

    operator fun div(that: Scalar) = SpectralIntensity
    operator fun times(that: Scalar) = SpectralIntensity

    operator fun div(that: SpectralIntensity) = Scalar

    operator fun div(that: SpacialFrequency) = RadiantIntensity
    operator fun times(that: Distance) = RadiantIntensity

    operator fun div(that: RadiantIntensity) = SpacialFrequency

    operator fun times(that: SolidAngle) = Yank
}
