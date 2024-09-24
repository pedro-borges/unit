package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object Force : Dimension
{
    override val composite = mapOf(Distance to 1,
                                   Mass to 1,
                                   Time to -2)

    operator fun div(that: Scalar) = Force
    operator fun times(that: Scalar) = Force

    operator fun div(that: Force) = Scalar

    operator fun div(that: Mass) = Acceleration

    operator fun div(that: EnergyDensity) = Area

    operator fun div(that: RadiantExposure) = Distance

    operator fun div(that: SpacialFrequency) = Energy
    operator fun times(that: Distance) = Energy

    operator fun div(that: Area) = EnergyDensity
    operator fun times(that: FuelEfficiency) = EnergyDensity

    operator fun div(that: Momentum) = Frequency

    operator fun div(that: TemperatureGradient) = HeatCapacity

    operator fun div(that: SpecificEnergy) = LinearDensity

    operator fun div(that: Acceleration) = Mass

    operator fun div(that: Speed) = MassFlowRate
    operator fun times(that: Pace) = MassFlowRate

    operator fun div(that: Frequency) = Momentum
    operator fun times(that: Time) = Momentum

    operator fun div(that: Power) = Pace

    operator fun div(that: Pace) = Power
    operator fun times(that: Speed) = Power

    operator fun div(that: Distance) = RadiantExposure
    operator fun times(that: SpacialFrequency) = RadiantExposure

    operator fun div(that: Energy) = SpacialFrequency

    operator fun div(that: LinearDensity) = SpecificEnergy

    operator fun div(that: MassFlowRate) = Speed

    operator fun div(that: HeatCapacity) = TemperatureGradient

    operator fun div(that: Yank) = Time

    operator fun div(that: Time) = Yank
    operator fun times(that: Frequency) = Yank
}
