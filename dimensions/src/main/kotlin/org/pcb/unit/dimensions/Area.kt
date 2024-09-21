package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(distance = 2)
object Area : Dimension
{
    operator fun div(that: Scalar) = Area
    operator fun times(that: Scalar) = Area

    operator fun div(that: Area) = Scalar
    operator fun times(that: FuelEfficiency) = Scalar

    operator fun times(that: MassFlowRate) = Action

    operator fun div(that: Distance) = Distance
    operator fun times(that: SpacialFrequency) = Distance

    operator fun times(that: RadiantExposure) = Energy

    operator fun times(that: EnergyDensity) = Force

    operator fun times(that: Illuminance) = LuminousFlux

    operator fun times(that: Luminance) = LuminousIntensity

    operator fun times(that: MagneticFluxDensity) = MagneticFlux

    operator fun times(that: AreaDensity) = MassDensity

    operator fun times(that: MassDensity) = MomentOfInertia

    operator fun div(that: VolumetricFlow) = Pace

    operator fun times(that: EnergyFluxDensity) = Power

    operator fun div(that: Volume) = SpacialFrequency

    operator fun div(that: SpacialFrequency) = Volume
    operator fun times(that: Distance) = Volume

    operator fun div(that: Pace) = VolumetricFlow
    operator fun times(that: Speed) = VolumetricFlow

    operator fun times(that: PowerDensity) = Yank
}
