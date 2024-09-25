package org.pcb.unit.dimensions

@Suppress("UNUSED_PARAMETER")
object ElectricDipoleMoment : Dimension
{
    override val composite = mapOf(Distance to 1,
                                   ElectricCurrent to 1,
                                   Time to 1)

    operator fun div(that: Scalar) = ElectricDipoleMoment
    operator fun times(that: Scalar) = ElectricDipoleMoment

    operator fun div(that: ElectricDipoleMoment) = Scalar

    operator fun div(that: ElectricCurrent) = Absement

    operator fun div(that: ElectricCharge) = Distance

    operator fun div(that: Distance) = ElectricCharge
    operator fun times(that: SpacialFrequency) = ElectricCharge

    operator fun div(that: Absement) = ElectricCurrent

    operator fun div(that: Volume) = ElectricDisplacementField

    operator fun times(that: ElectricFieldStrength) = Energy

    operator fun div(that: Pace) = MagneticDipoleMoment
    operator fun times(that: Speed) = MagneticDipoleMoment

    operator fun times(that: MagneticFluxDensity) = Momentum

    operator fun div(that: MagneticDipoleMoment) = Pace

    operator fun div(that: ElectricDisplacementField) = Volume
}
