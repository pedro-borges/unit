package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object AreaDensity : Dimension
{
    override val composite = mapOf(Mass to 1,
                                   Distance to -2)

    operator fun div(that: Scalar) = AreaDensity
    operator fun times(that: Scalar) = AreaDensity

    operator fun div(that: AreaDensity) = Scalar

    operator fun div(that: MassDensity) = Distance
    operator fun times(that: SpecificVolume) = Distance

    operator fun times(that: Acceleration) = EnergyDensity

    operator fun div(that: Mass) = FuelEfficiency

    operator fun div(that: SpacialFrequency) = LinearDensity
    operator fun times(that: Distance) = LinearDensity

    operator fun div(that: FuelEfficiency) = Mass
    operator fun times(that: Area) = Mass

    operator fun div(that: Distance) = MassDensity
    operator fun times(that: SpacialFrequency) = MassDensity

    operator fun times(that: VolumetricFlow) = Momentum

    operator fun div(that: Viscosity) = Pace

    operator fun times(that: Jerk) = PowerDensity

    operator fun times(that: SpecificEnergy) = RadiantExposure

    operator fun div(that: LinearDensity) = SpacialFrequency

    operator fun div(that: Pace) = Viscosity
    operator fun times(that: Speed) = Viscosity
}
