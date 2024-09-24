package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(distance = 2,
           mass = 1,
           electricCurrent = -2,
           time = -2)
object Inductance : Dimension
{
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
