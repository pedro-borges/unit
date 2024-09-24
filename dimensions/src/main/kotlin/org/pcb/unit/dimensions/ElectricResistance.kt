package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object ElectricResistance : Dimension
{
    override val composite = mapOf(Distance to 2,
                                   Mass to 1,
                                   ElectricCurrent to -2,
                                   Time to -3)

    operator fun div(that: Scalar) = ElectricResistance
    operator fun times(that: Scalar) = ElectricResistance

    operator fun div(that: ElectricResistance) = Scalar
    operator fun times(that: ElectricConductance) = Scalar

    operator fun times(that: ElectricCurrent) = ElectricTension

    operator fun div(that: Inductance) = Frequency

    operator fun div(that: Frequency) = Inductance
    operator fun times(that: Time) = Inductance

    operator fun times(that: ElectricCharge) = MagneticFlux

    operator fun times(that: Permittivity) = Pace

    operator fun div(that: Speed) = Permeability
    operator fun times(that: Pace) = Permeability

    operator fun times(that: ElectricConductivity) = SpacialFrequency

    operator fun div(that: Permeability) = Speed

    operator fun times(that: Capacitance) = Time
}
