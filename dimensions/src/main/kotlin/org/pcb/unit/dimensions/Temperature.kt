package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object Temperature : Dimension
{
    override val composite = mapOf(Temperature to 1)

    operator fun div(that: Scalar) = Temperature
    operator fun times(that: Scalar) = Temperature

    operator fun div(that: Temperature) = Scalar
    operator fun times(that: ThermalExpansionCoefficient) = Scalar

    operator fun div(that: TemperatureGradient) = Distance

    operator fun times(that: HeatCapacity) = Energy

    operator fun times(that: MolarHeatCapacity) = MolarEnergy

    operator fun times(that: SpecificHeatCapacity) = SpecificEnergy

    operator fun div(that: Distance) = TemperatureGradient
    operator fun times(that: SpacialFrequency) = TemperatureGradient

    operator fun times(that: ThermalConductivity) = Yank
}
