package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object Luminance : Dimension
{
    override val composite = mapOf(LuminousIntensity to 1,
                                   Distance to -2)

    operator fun div(that: Scalar) = Luminance
    operator fun times(that: Scalar) = Luminance

    operator fun div(that: Luminance) = Scalar

    operator fun div(that: LuminousIntensity) = FuelEfficiency

    operator fun times(that: SolidAngle) = Illuminance

    operator fun div(that: FuelEfficiency) = LuminousIntensity
    operator fun times(that: Area) = LuminousIntensity
}
