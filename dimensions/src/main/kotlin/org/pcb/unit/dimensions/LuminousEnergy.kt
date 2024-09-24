package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object LuminousEnergy : Dimension
{
    override val composite = mapOf(Angle to 2,
                                   LuminousIntensity to 1,
                                   Time to 1)

    operator fun div(that: Scalar) = LuminousEnergy
    operator fun times(that: Scalar) = LuminousEnergy

    operator fun div(that: LuminousEnergy) = Scalar

    operator fun div(that: Time) = LuminousFlux
    operator fun times(that: Frequency) = LuminousFlux

    operator fun div(that: LuminousFlux) = Time
}
