package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object TemperatureGradient : Dimension
{
    override val composite = mapOf(Temperature to 1,
                                   Distance to -1)

    operator fun div(that: Scalar) = TemperatureGradient
    operator fun times(that: Scalar) = TemperatureGradient

    operator fun div(that: TemperatureGradient) = Scalar

    operator fun times(that: SpecificHeatCapacity) = Acceleration

    operator fun times(that: HeatCapacity) = Force

    operator fun times(that: ThermalConductivity) = Intensity

    operator fun div(that: Temperature) = SpacialFrequency
    operator fun times(that: ThermalExpansionCoefficient) = SpacialFrequency

    operator fun div(that: SpacialFrequency) = Temperature
    operator fun times(that: Distance) = Temperature
}
