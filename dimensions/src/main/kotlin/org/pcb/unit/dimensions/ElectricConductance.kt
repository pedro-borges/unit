package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object ElectricConductance : Dimension
{
    override val composite = mapOf(Time to 3,
                                   ElectricCurrent to 2,
                                   Mass to -1,
                                   Distance to -2)

    operator fun div(that: Scalar) = ElectricConductance
    operator fun times(that: Scalar) = ElectricConductance

    operator fun div(that: ElectricConductance) = Scalar
    operator fun times(that: ElectricResistance) = Scalar

    operator fun div(that: Frequency) = Capacitance
    operator fun times(that: Time) = Capacitance

    operator fun div(that: ElectricConductivity) = Distance

    operator fun times(that: MagneticFlux) = ElectricCharge

    operator fun div(that: Distance) = ElectricConductivity
    operator fun times(that: SpacialFrequency) = ElectricConductivity

    operator fun times(that: ElectricTension) = ElectricCurrent

    operator fun times(that: MagneticFluxDensity) = ElectricDisplacementField

    operator fun div(that: Capacitance) = Frequency

    operator fun times(that: ElectricFieldStrength) = MagneticFieldStrength

    operator fun times(that: Permeability) = Pace

    operator fun div(that: Speed) = Permittivity
    operator fun times(that: Pace) = Permittivity

    operator fun div(that: Permittivity) = Speed

    operator fun times(that: Inductance) = Time
}
