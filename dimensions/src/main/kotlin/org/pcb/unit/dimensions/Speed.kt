package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object Speed : Dimension
{
    override val composite = mapOf(Distance to 1,
                                   Time to -1)

    operator fun div(that: Scalar) = Speed
    operator fun times(that: Scalar) = Speed

    operator fun div(that: Speed) = Scalar
    operator fun times(that: Pace) = Scalar

    operator fun div(that: Time) = Acceleration
    operator fun times(that: Frequency) = Acceleration

    operator fun times(that: Absement) = Area

    operator fun div(that: Frequency) = Distance
    operator fun times(that: Time) = Distance

    operator fun times(that: Permittivity) = ElectricConductance

    operator fun times(that: Permeability) = ElectricResistance

    operator fun times(that: Momentum) = Energy

    operator fun times(that: MassFlowRate) = Force

    operator fun div(that: Distance) = Frequency
    operator fun times(that: SpacialFrequency) = Frequency

    operator fun div(that: VolumetricFlow) = FuelEfficiency

    operator fun times(that: EnergyDensity) = Intensity

    operator fun times(that: LinearDensity) = MassFlowRate

    operator fun times(that: Mass) = Momentum

    operator fun div(that: SpecificEnergy) = Pace

    operator fun times(that: Force) = Power

    operator fun div(that: Pace) = SpecificEnergy
    operator fun times(that: Speed) = SpecificEnergy

    operator fun div(that: Acceleration) = Time

    operator fun div(that: FuelEfficiency) = VolumetricFlow
    operator fun times(that: Area) = VolumetricFlow

    operator fun times(that: RadiantExposure) = Yank
}
