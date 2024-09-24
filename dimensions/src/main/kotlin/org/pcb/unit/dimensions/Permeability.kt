package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object Permeability : Dimension
{
    override val composite = mapOf(Distance to 1,
                                   Mass to 1,
                                   ElectricCurrent to -2,
                                   Time to -2)

    operator fun div(that: Scalar) = Permeability
    operator fun times(that: Scalar) = Permeability

    operator fun div(that: Permeability) = Scalar

    operator fun div(that: Pace) = ElectricResistance
    operator fun times(that: Speed) = ElectricResistance

    operator fun div(that: SpacialFrequency) = Inductance
    operator fun times(that: Distance) = Inductance

    operator fun div(that: ElectricResistance) = Pace
    operator fun times(that: ElectricConductance) = Pace

    operator fun div(that: Inductance) = SpacialFrequency
}
