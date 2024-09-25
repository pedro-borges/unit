package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object HeatCapacity : Dimension
{
    override val composite = mapOf(Distance to 2,
                                   Mass to 1,
                                   Temperature to -1,
                                   Time to -2)

    operator fun div(that: Scalar) = HeatCapacity
    operator fun times(that: Scalar) = HeatCapacity

    operator fun div(that: HeatCapacity) = Scalar

    operator fun div(that: ThermalConductivity) = Absement

    operator fun div(that: ThermalExpansionCoefficient) = Energy
    operator fun times(that: Temperature) = Energy

    operator fun times(that: TemperatureGradient) = Force

    operator fun div(that: SpecificHeatCapacity) = Mass

    operator fun div(that: Substance) = MolarHeatCapacity

    operator fun div(that: Mass) = SpecificHeatCapacity

    operator fun div(that: MolarHeatCapacity) = Substance

    operator fun div(that: Absement) = ThermalConductivity

    operator fun div(that: Energy) = ThermalExpansionCoefficient
}
