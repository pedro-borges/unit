package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(distance = -2)
object FuelEfficiency : Dimension
{
    operator fun div(that: Scalar) = FuelEfficiency
    operator fun times(that: Scalar) = FuelEfficiency

    operator fun div(that: FuelEfficiency) = Scalar
    operator fun times(that: Area) = Scalar

    operator fun times(that: MassDensity) = AreaDensity

    operator fun times(that: Volume) = Distance

    operator fun times(that: Force) = EnergyDensity

    operator fun times(that: Power) = EnergyFluxDensity

    operator fun times(that: LuminousFlux) = Illuminance

    operator fun times(that: LuminousIntensity) = Luminance

    operator fun times(that: MagneticFlux) = MagneticFluxDensity

    operator fun times(that: MomentOfInertia) = MassDensity

    operator fun times(that: Action) = MassFlowRate

    operator fun times(that: Yank) = PowerDensity

    operator fun times(that: Energy) = RadiantExposure

    operator fun div(that: SpacialFrequency) = SpacialFrequency
    operator fun times(that: Distance) = SpacialFrequency

    operator fun times(that: VolumetricFlow) = Speed
}
