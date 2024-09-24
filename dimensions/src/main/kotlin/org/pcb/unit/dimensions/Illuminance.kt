package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object Illuminance : Dimension
{
    override val composite = mapOf(Angle to 2,
                                   LuminousIntensity to 1,
                                   Distance to -2)

    operator fun div(that: Scalar) = Illuminance
    operator fun times(that: Scalar) = Illuminance

    operator fun div(that: Illuminance) = Scalar

    operator fun div(that: LuminousFlux) = FuelEfficiency

    operator fun div(that: SolidAngle) = Luminance

    operator fun div(that: FuelEfficiency) = LuminousFlux
    operator fun times(that: Area) = LuminousFlux

    operator fun div(that: Luminance) = SolidAngle
}
