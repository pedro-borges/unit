package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object Distance : Dimension
{
    override val composite = mapOf(Distance to 1)

    operator fun div(that: Scalar) = Distance
    operator fun times(that: Scalar) = Distance

    operator fun div(that: Distance) = Scalar
    operator fun times(that: SpacialFrequency) = Scalar

    operator fun times(that: Momentum) = Action

    operator fun div(that: SpacialFrequency) = Area
    operator fun times(that: Distance) = Area

    operator fun div(that: SpecificVolume) = AreaDensity
    operator fun times(that: MassDensity) = AreaDensity

    operator fun times(that: Permittivity) = Capacitance

    operator fun times(that: ElectricConductivity) = ElectricConductance

    operator fun times(that: Force) = Energy

    operator fun times(that: RadiantExposure) = Force

    operator fun div(that: Volume) = FuelEfficiency

    operator fun times(that: Permeability) = Inductance

    operator fun times(that: PowerDensity) = Intensity

    operator fun times(that: AreaDensity) = LinearDensity

    operator fun times(that: LinearDensity) = Mass

    operator fun times(that: MassFlowRate) = Momentum

    operator fun times(that: Yank) = Power

    operator fun times(that: EnergyDensity) = RadiantExposure

    operator fun times(that: SpectralIntensity) = RadiantIntensity

    operator fun div(that: Area) = SpacialFrequency
    operator fun times(that: FuelEfficiency) = SpacialFrequency

    operator fun times(that: Acceleration) = SpecificEnergy

    operator fun div(that: AreaDensity) = SpecificVolume

    operator fun div(that: Time) = Speed
    operator fun times(that: Frequency) = Speed

    operator fun times(that: TemperatureGradient) = Temperature

    operator fun div(that: Speed) = Time
    operator fun times(that: Pace) = Time

    operator fun div(that: FuelEfficiency) = Volume
    operator fun times(that: Area) = Volume

    operator fun times(that: Intensity) = Yank
}
