package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(distance = 3,
           time = -1)
object VolumetricFlow : Dimension
{
    operator fun div(that: Scalar) = VolumetricFlow
    operator fun times(that: Scalar) = VolumetricFlow

    operator fun div(that: VolumetricFlow) = Scalar

    operator fun div(that: Speed) = Area
    operator fun times(that: Pace) = Area

    operator fun div(that: Substance) = CatalyticEfficiency

    operator fun div(that: Volume) = Frequency

    operator fun div(that: SpecificVolume) = MassFlowRate
    operator fun times(that: MassDensity) = MassFlowRate

    operator fun times(that: AreaDensity) = Momentum

    operator fun times(that: EnergyDensity) = Power

    operator fun div(that: MassFlowRate) = SpecificVolume

    operator fun div(that: Area) = Speed
    operator fun times(that: FuelEfficiency) = Speed

    operator fun div(that: CatalyticEfficiency) = Substance

    operator fun div(that: Frequency) = Volume
    operator fun times(that: Time) = Volume
}
