package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(distance = 1,
           mass = 1,
           electricCurrent = -2,
           time = -2)
object Permeability : Dimension
{
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
