package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object Area : Dimension
{
    override val composite = mapOf(Distance to 2)

    operator fun div(that: Scalar) = Area
    operator fun times(that: Scalar) = Area

    operator fun div(that: Area) = Scalar
    operator fun times(that: FuelEfficiency) = Scalar

    operator fun div(that: Speed) = Absement
    operator fun times(that: Pace) = Absement

    operator fun times(that: MassFlowRate) = Action

    operator fun div(that: Distance) = Distance
    operator fun times(that: SpacialFrequency) = Distance

    operator fun times(that: ElectricCurrentDensity) = ElectricCurrent

    operator fun times(that: RadiantExposure) = Energy

    operator fun times(that: EnergyDensity) = Force

    operator fun div(that: SpecificVolume) = LinearDensity
    operator fun times(that: MassDensity) = LinearDensity

    operator fun times(that: Illuminance) = LuminousFlux

    operator fun times(that: Luminance) = LuminousIntensity

    operator fun times(that: MagneticFluxDensity) = MagneticFlux

    operator fun times(that: AreaDensity) = Mass

    operator fun times(that: Mass) = MomentOfInertia

    operator fun div(that: VolumetricFlow) = Pace

    operator fun times(that: Intensity) = Power

    operator fun div(that: Volume) = SpacialFrequency

    operator fun div(that: LinearDensity) = SpecificVolume

    operator fun div(that: Absement) = Speed

    operator fun div(that: SpacialFrequency) = Volume
    operator fun times(that: Distance) = Volume

    operator fun div(that: Pace) = VolumetricFlow
    operator fun times(that: Speed) = VolumetricFlow

    operator fun times(that: PowerDensity) = Yank
}
