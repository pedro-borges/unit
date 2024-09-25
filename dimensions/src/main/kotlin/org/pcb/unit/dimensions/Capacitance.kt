package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object Capacitance : Dimension
{
    override val composite = mapOf(Time to 4,
                                   ElectricCurrent to 2,
                                   Mass to -1,
                                   Distance to -2)

    operator fun div(that: Scalar) = Capacitance
    operator fun times(that: Scalar) = Capacitance

    operator fun div(that: Capacitance) = Scalar

    operator fun div(that: ElectricConductivity) = Absement

    operator fun div(that: Permittivity) = Distance

    operator fun times(that: ElectricTension) = ElectricCharge

    operator fun div(that: Time) = ElectricConductance
    operator fun times(that: Frequency) = ElectricConductance

    operator fun div(that: Absement) = ElectricConductivity

    operator fun div(that: Distance) = Permittivity
    operator fun times(that: SpacialFrequency) = Permittivity

    operator fun div(that: ElectricConductance) = Time
    operator fun times(that: ElectricResistance) = Time
}
