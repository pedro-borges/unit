package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(angle = 2,
           luminousIntensity = 1,
           time = 1)
object LuminousEnergy : Dimension
{
    operator fun div(that: Scalar) = LuminousEnergy
    operator fun times(that: Scalar) = LuminousEnergy

    operator fun div(that: LuminousEnergy) = Scalar

    operator fun div(that: Time) = LuminousFlux
    operator fun times(that: Frequency) = LuminousFlux

    operator fun div(that: LuminousFlux) = Time
}