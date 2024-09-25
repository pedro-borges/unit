package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object MagneticDipoleMoment : Dimension
{
    override val composite = mapOf(Distance to 2,
                                   ElectricCurrent to 1)

    operator fun div(that: Scalar) = MagneticDipoleMoment
    operator fun times(that: Scalar) = MagneticDipoleMoment

    operator fun div(that: MagneticDipoleMoment) = Scalar

    operator fun div(that: ElectricCurrent) = Area

    operator fun div(that: Area) = ElectricCurrent
    operator fun times(that: FuelEfficiency) = ElectricCurrent

    operator fun div(that: Speed) = ElectricDipoleMoment
    operator fun times(that: Pace) = ElectricDipoleMoment

    operator fun times(that: MagneticFluxDensity) = Energy

    operator fun div(that: Volume) = MagneticFieldStrength

    operator fun div(that: ElectricDipoleMoment) = Speed

    operator fun div(that: MagneticFieldStrength) = Volume
}
