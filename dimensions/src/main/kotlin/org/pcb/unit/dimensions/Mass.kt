package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object Mass : Dimension
{
    override val composite = mapOf(Mass to 1)

    operator fun div(that: Scalar) = Mass
    operator fun times(that: Scalar) = Mass

    operator fun div(that: Mass) = Scalar

    operator fun div(that: Viscosity) = Absement

    operator fun times(that: Momentum) = AngularMomentum

    operator fun div(that: AreaDensity) = Area

    operator fun div(that: Area) = AreaDensity
    operator fun times(that: FuelEfficiency) = AreaDensity

    operator fun div(that: LinearDensity) = Distance

    operator fun times(that: SpecificEnergy) = Energy

    operator fun times(that: Acceleration) = Force

    operator fun div(that: MomentOfInertia) = FuelEfficiency

    operator fun times(that: SpecificHeatCapacity) = HeatCapacity

    operator fun div(that: Distance) = LinearDensity
    operator fun times(that: SpacialFrequency) = LinearDensity

    operator fun div(that: Volume) = MassDensity

    operator fun div(that: Time) = MassFlowRate
    operator fun times(that: Frequency) = MassFlowRate

    operator fun div(that: Substance) = MolarMass

    operator fun div(that: FuelEfficiency) = MomentOfInertia
    operator fun times(that: Area) = MomentOfInertia

    operator fun div(that: Pace) = Momentum
    operator fun times(that: Speed) = Momentum

    operator fun div(that: Momentum) = Pace

    operator fun div(that: MolarMass) = Substance
    operator fun times(that: Molality) = Substance

    operator fun div(that: MassFlowRate) = Time

    operator fun div(that: Absement) = Viscosity

    operator fun div(that: MassDensity) = Volume
    operator fun times(that: SpecificVolume) = Volume

    operator fun times(that: Jerk) = Yank
}
