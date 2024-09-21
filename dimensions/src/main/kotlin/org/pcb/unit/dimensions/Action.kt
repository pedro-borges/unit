package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(distance = 2,
           mass = 1,
           time = -1)
object Action : Dimension
{
    operator fun div(that: Scalar) = Action
    operator fun times(that: Scalar) = Action

    operator fun div(that: Action) = Scalar

    operator fun div(that: MassFlowRate) = Area

    operator fun div(that: Momentum) = Distance

    operator fun div(that: MagneticFlux) = ElectricCharge

    operator fun div(that: Time) = Energy
    operator fun times(that: Frequency) = Energy

    operator fun div(that: MomentOfInertia) = Frequency

    operator fun div(that: ElectricCharge) = MagneticFlux

    operator fun div(that: Area) = MassFlowRate
    operator fun times(that: FuelEfficiency) = MassFlowRate

    operator fun div(that: Frequency) = MomentOfInertia
    operator fun times(that: Time) = MomentOfInertia

    operator fun div(that: Distance) = Momentum
    operator fun times(that: SpacialFrequency) = Momentum

    operator fun div(that: Energy) = Time
}
