package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(angle = 2,
           luminousIntensity = 1)
object LuminousFlux : Dimension
{
    operator fun div(that: Scalar) = LuminousFlux
    operator fun times(that: Scalar) = LuminousFlux

    operator fun div(that: LuminousFlux) = Scalar

    operator fun div(that: Illuminance) = Area

    operator fun div(that: LuminousEnergy) = Frequency

    operator fun div(that: Area) = Illuminance
    operator fun times(that: FuelEfficiency) = Illuminance

    operator fun div(that: Frequency) = LuminousEnergy
    operator fun times(that: Time) = LuminousEnergy

    operator fun div(that: SolidAngle) = LuminousIntensity

    operator fun div(that: LuminousIntensity) = SolidAngle
}
