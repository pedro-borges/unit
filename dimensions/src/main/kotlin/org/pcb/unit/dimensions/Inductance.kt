package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object Inductance : Dimension
{
    override val composite = mapOf(Distance to 2,
                                   Mass to 1,
                                   ElectricCurrent to -2,
                                   Time to -2)

    operator fun div(that: Scalar) = Inductance
    operator fun times(that: Scalar) = Inductance

    operator fun div(that: Inductance) = Scalar

    operator fun div(that: Permeability) = Distance

    operator fun div(that: Time) = ElectricResistance
    operator fun times(that: Frequency) = ElectricResistance

    operator fun times(that: ElectricCurrent) = MagneticFlux

    operator fun div(that: Distance) = Permeability
    operator fun times(that: SpacialFrequency) = Permeability

    operator fun div(that: ElectricResistance) = Time
    operator fun times(that: ElectricConductance) = Time
}
