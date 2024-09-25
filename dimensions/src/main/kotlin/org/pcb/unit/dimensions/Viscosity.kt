package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object Viscosity : Dimension
{
    override val composite = mapOf(Mass to 1,
                                   Distance to -1,
                                   Time to -1)

    operator fun div(that: Scalar) = Viscosity
    operator fun times(that: Scalar) = Viscosity

    operator fun div(that: Viscosity) = Scalar

    operator fun div(that: EnergyDensityGradient) = Absement

    operator fun times(that: Volume) = Action

    operator fun times(that: MomentOfInertia) = AngularMomentum

    operator fun div(that: Speed) = AreaDensity
    operator fun times(that: Pace) = AreaDensity

    operator fun times(that: VolumetricFlow) = Energy

    operator fun div(that: Time) = EnergyDensity
    operator fun times(that: Frequency) = EnergyDensity

    operator fun div(that: Absement) = EnergyDensityGradient

    operator fun div(that: LinearDensity) = Frequency

    operator fun div(that: Momentum) = FuelEfficiency

    operator fun times(that: Acceleration) = Intensity

    operator fun div(that: Frequency) = LinearDensity
    operator fun times(that: Time) = LinearDensity

    operator fun times(that: Absement) = Mass

    operator fun div(that: SpacialFrequency) = MassFlowRate
    operator fun times(that: Distance) = MassFlowRate

    operator fun times(that: CatalyticEfficiency) = MolarEnergy

    operator fun div(that: FuelEfficiency) = Momentum
    operator fun times(that: Area) = Momentum

    operator fun div(that: RadiantExposure) = Pace

    operator fun div(that: Pace) = RadiantExposure
    operator fun times(that: Speed) = RadiantExposure

    operator fun div(that: MassFlowRate) = SpacialFrequency

    operator fun div(that: AreaDensity) = Speed

    operator fun times(that: SpecificHeatCapacity) = ThermalConductivity

    operator fun div(that: EnergyDensity) = Time

    operator fun times(that: SpecificEnergy) = Yank
}
