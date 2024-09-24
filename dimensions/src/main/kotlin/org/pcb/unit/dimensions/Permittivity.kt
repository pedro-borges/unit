package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object Permittivity : Dimension
{
    override val composite = mapOf(Time to 4,
                                   ElectricCurrent to 2,
                                   Mass to -1,
                                   Distance to -3)

    operator fun div(that: Scalar) = Permittivity
    operator fun times(that: Scalar) = Permittivity

    operator fun div(that: Permittivity) = Scalar

    operator fun div(that: SpacialFrequency) = Capacitance
    operator fun times(that: Distance) = Capacitance

    operator fun div(that: Pace) = ElectricConductance
    operator fun times(that: Speed) = ElectricConductance

    operator fun div(that: Time) = ElectricConductivity
    operator fun times(that: Frequency) = ElectricConductivity

    operator fun times(that: CatalyticEfficiency) = MolarConductivity

    operator fun div(that: ElectricConductance) = Pace
    operator fun times(that: ElectricResistance) = Pace

    operator fun div(that: Capacitance) = SpacialFrequency

    operator fun div(that: ElectricConductivity) = Time
}
