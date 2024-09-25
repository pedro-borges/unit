package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object FuelEfficiency : Dimension
{
    override val composite = mapOf(Distance to -2)

    operator fun div(that: Scalar) = FuelEfficiency
    operator fun times(that: Scalar) = FuelEfficiency

    operator fun div(that: FuelEfficiency) = Scalar
    operator fun times(that: Area) = Scalar

    operator fun times(that: Mass) = AreaDensity

    operator fun times(that: Volume) = Distance

    operator fun times(that: MagneticDipoleMoment) = ElectricCurrent

    operator fun times(that: ElectricCurrent) = ElectricCurrentDensity

    operator fun times(that: ElectricCharge) = ElectricDisplacementField

    operator fun times(that: Force) = EnergyDensity

    operator fun times(that: RadiantExposure) = EnergyDensityGradient

    operator fun times(that: LuminousFlux) = Illuminance

    operator fun times(that: Power) = Intensity

    operator fun times(that: LuminousIntensity) = Luminance

    operator fun times(that: MagneticFlux) = MagneticFluxDensity

    operator fun times(that: MomentOfInertia) = Mass

    operator fun times(that: LinearDensity) = MassDensity

    operator fun times(that: Action) = MassFlowRate

    operator fun times(that: Absement) = Pace

    operator fun times(that: Yank) = PowerDensity

    operator fun times(that: Energy) = RadiantExposure

    operator fun div(that: SpacialFrequency) = SpacialFrequency
    operator fun times(that: Distance) = SpacialFrequency

    operator fun times(that: VolumetricFlow) = Speed

    operator fun times(that: Momentum) = Viscosity
}
