package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object MolarConductivity : Dimension
{
    override val composite = mapOf(Time to 3,
                                   ElectricCurrent to 2,
                                   Mass to -1,
                                   Substance to -1)

    operator fun div(that: Scalar) = MolarConductivity
    operator fun times(that: Scalar) = MolarConductivity

    operator fun div(that: MolarConductivity) = Scalar

    operator fun div(that: Permittivity) = CatalyticEfficiency

    operator fun div(that: MolarVolume) = ElectricConductivity
    operator fun times(that: Molarity) = ElectricConductivity

    operator fun div(that: ElectricConductivity) = MolarVolume

    operator fun div(that: CatalyticEfficiency) = Permittivity
}
