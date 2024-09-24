package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object ThermalResistance : Dimension
{
    override val composite = mapOf(Time to 3,
                                   Distance to 2,
                                   Temperature to 1,
                                   Mass to -1)

    operator fun div(that: Scalar) = ThermalResistance
    operator fun times(that: Scalar) = ThermalResistance

    operator fun div(that: ThermalResistance) = Scalar

    operator fun times(that: ThermalConductivity) = Volume
}
