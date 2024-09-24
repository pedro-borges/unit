package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(mass = 1,
           distance = -2)
object AreaDensity : Dimension
{
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

    operator fun times(that: Jerk) = PowerDensity

    operator fun times(that: SpecificEnergy) = RadiantExposure

    operator fun div(that: LinearDensity) = SpacialFrequency
}
