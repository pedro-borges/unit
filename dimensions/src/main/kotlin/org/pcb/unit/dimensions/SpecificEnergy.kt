package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(distance = 2,
           time = -2)
object SpecificEnergy : Dimension
{
    operator fun div(that: Scalar) = SpecificEnergy
    operator fun times(that: Scalar) = SpecificEnergy

    operator fun div(that: SpecificEnergy) = Scalar

    operator fun div(that: Distance) = Acceleration
    operator fun times(that: SpacialFrequency) = Acceleration

    operator fun div(that: Acceleration) = Distance

    operator fun times(that: MassDensity) = Energy

    operator fun div(that: SpecificVolume) = EnergyDensity
    operator fun times(that: Mass) = EnergyDensity

    operator fun times(that: MassFlowRate) = Power

    operator fun times(that: AreaDensity) = RadiantExposure

    operator fun div(that: EnergyDensity) = SpecificVolume

    operator fun div(that: Speed) = Speed
    operator fun times(that: Pace) = Speed
}
