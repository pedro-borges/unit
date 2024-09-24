package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object RadiantIntensity : Dimension
{
    override val composite = mapOf(Distance to 2,
                                   Mass to 1,
                                   Angle to -2,
                                   Time to -3)

    operator fun div(that: Scalar) = RadiantIntensity
    operator fun times(that: Scalar) = RadiantIntensity

    operator fun div(that: RadiantIntensity) = Scalar

    operator fun div(that: SpectralIntensity) = Distance

    operator fun times(that: SolidAngle) = Power

    operator fun div(that: Distance) = SpectralIntensity
    operator fun times(that: SpacialFrequency) = SpectralIntensity
}
