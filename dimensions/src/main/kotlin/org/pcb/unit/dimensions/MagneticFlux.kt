package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object MagneticFlux : Dimension
{
    override val composite = mapOf(Distance to 2,
                                   Mass to 1,
                                   ElectricCurrent to -1,
                                   Time to -2)

    operator fun div(that: Scalar) = MagneticFlux
    operator fun times(that: Scalar) = MagneticFlux

    operator fun div(that: MagneticFlux) = Scalar

    operator fun times(that: ElectricCharge) = Action

    operator fun div(that: MagneticFluxDensity) = Area

    operator fun div(that: ElectricResistance) = ElectricCharge
    operator fun times(that: ElectricConductance) = ElectricCharge

    operator fun div(that: Inductance) = ElectricCurrent

    operator fun div(that: ElectricCharge) = ElectricResistance

    operator fun div(that: Time) = ElectricTension
    operator fun times(that: Frequency) = ElectricTension

    operator fun times(that: ElectricCurrent) = Energy

    operator fun div(that: ElectricCurrent) = Inductance

    operator fun div(that: Area) = MagneticFluxDensity
    operator fun times(that: FuelEfficiency) = MagneticFluxDensity

    operator fun div(that: ElectricTension) = Time
}
