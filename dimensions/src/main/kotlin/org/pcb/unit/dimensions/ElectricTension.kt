package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object ElectricTension : Dimension
{
    override val composite = mapOf(Distance to 2,
                                   Mass to 1,
                                   ElectricCurrent to -1,
                                   Time to -3)

    operator fun div(that: Scalar) = ElectricTension
    operator fun times(that: Scalar) = ElectricTension

    operator fun div(that: ElectricTension) = Scalar

    operator fun times(that: Capacitance) = ElectricCharge

    operator fun div(that: ElectricResistance) = ElectricCurrent
    operator fun times(that: ElectricConductance) = ElectricCurrent

    operator fun div(that: ElectricCurrent) = ElectricResistance

    operator fun times(that: ElectricCharge) = Energy

    operator fun div(that: MagneticFlux) = Frequency

    operator fun times(that: ElectricCurrentDensity) = Intensity

    operator fun div(that: Frequency) = MagneticFlux
    operator fun times(that: Time) = MagneticFlux

    operator fun times(that: ElectricCurrent) = Power
}
