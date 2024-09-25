package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object Absement : Dimension
{
    override val composite = mapOf(Distance to 1,
                                   Time to 1)

    operator fun div(that: Scalar) = Absement
    operator fun times(that: Scalar) = Absement

    operator fun div(that: Absement) = Scalar

    operator fun times(that: Force) = Action

    operator fun div(that: Pace) = Area
    operator fun times(that: Speed) = Area

    operator fun times(that: ElectricConductivity) = Capacitance

    operator fun div(that: Time) = Distance
    operator fun times(that: Frequency) = Distance

    operator fun times(that: Yank) = Energy

    operator fun times(that: Intensity) = Force

    operator fun times(that: ThermalConductivity) = HeatCapacity

    operator fun times(that: EnergyDensity) = MassFlowRate

    operator fun times(that: Momentum) = MomentOfInertia

    operator fun times(that: RadiantExposure) = Momentum

    operator fun div(that: Area) = Pace
    operator fun times(that: FuelEfficiency) = Pace

    operator fun times(that: PowerDensity) = RadiantExposure

    operator fun times(that: Jerk) = SpecificEnergy

    operator fun div(that: Distance) = Time
    operator fun times(that: SpacialFrequency) = Time

    operator fun times(that: SpecificEnergy) = VolumetricFlow
}
