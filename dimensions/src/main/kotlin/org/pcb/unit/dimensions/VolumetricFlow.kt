package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object VolumetricFlow : Dimension
{
    override val composite = mapOf(Distance to 3,
                                   Time to -1)

    operator fun div(that: Scalar) = VolumetricFlow
    operator fun times(that: Scalar) = VolumetricFlow

    operator fun div(that: VolumetricFlow) = Scalar

    operator fun times(that: LinearDensity) = Action

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
