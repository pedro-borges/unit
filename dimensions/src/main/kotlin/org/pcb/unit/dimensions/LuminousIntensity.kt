package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object LuminousIntensity : Dimension
{
    override val composite = mapOf(LuminousIntensity to 1)

    operator fun div(that: Scalar) = LuminousIntensity
    operator fun times(that: Scalar) = LuminousIntensity

    operator fun div(that: LuminousIntensity) = Scalar

    operator fun div(that: Luminance) = Area

    operator fun div(that: Area) = Luminance
    operator fun times(that: FuelEfficiency) = Luminance

    operator fun times(that: SolidAngle) = LuminousFlux
}
