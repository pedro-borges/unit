package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(distance = 2)
object Area : Dimension
{
    operator fun div(that: Scalar) = Area
    operator fun times(that: Scalar) = Area
    operator fun div(that: Area) = Scalar

    operator fun div(that: Distance) = Distance

    operator fun times(that: EnergyDensity) = Force

    operator fun times(that: Illuminance) = LuminousFlux

    operator fun times(that: Luminance) = LuminousIntensity

    operator fun times(that: MagneticFluxDensity) = MagneticFlux

    operator fun times(that: Distance) = Volume
}
