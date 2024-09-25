package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object ThermalConductivity : Dimension
{
    override val composite = mapOf(Distance to 1,
                                   Mass to 1,
                                   Temperature to -1,
                                   Time to -3)

    operator fun div(that: Scalar) = ThermalConductivity
    operator fun times(that: Scalar) = ThermalConductivity

    operator fun div(that: ThermalConductivity) = Scalar

    operator fun times(that: Absement) = HeatCapacity

    operator fun times(that: TemperatureGradient) = Intensity

    operator fun div(that: Yank) = ThermalExpansionCoefficient

    operator fun times(that: ThermalResistance) = Volume

    operator fun div(that: ThermalExpansionCoefficient) = Yank
    operator fun times(that: Temperature) = Yank
}
