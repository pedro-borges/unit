package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object ElectricConductivity : Dimension
{
    override val composite = mapOf(Time to 3,
                                   ElectricCurrent to 2,
                                   Mass to -1,
                                   Distance to -3)

    operator fun div(that: Scalar) = ElectricConductivity
    operator fun times(that: Scalar) = ElectricConductivity

    operator fun div(that: ElectricConductivity) = Scalar

    operator fun div(that: SpacialFrequency) = ElectricConductance
    operator fun times(that: Distance) = ElectricConductance

    operator fun div(that: Permittivity) = Frequency

    operator fun div(that: Molarity) = MolarConductivity
    operator fun times(that: MolarVolume) = MolarConductivity

    operator fun div(that: MolarConductivity) = Molarity

    operator fun times(that: Inductance) = Pace

    operator fun div(that: Frequency) = Permittivity
    operator fun times(that: Time) = Permittivity

    operator fun div(that: ElectricConductance) = SpacialFrequency
    operator fun times(that: ElectricResistance) = SpacialFrequency
}
