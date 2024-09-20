package org.pcb.unit.dimensions

import org.pcb.unit.Composite

@Suppress("UNUSED_PARAMETER")
@Composite(distance = 1,
		   mass = 1,
		   time = -2)
object Force : Dimension
{
	operator fun div(that: Scalar) = Force
	operator fun times(that: Scalar) = Force

	operator fun div(that: Force) = Scalar

	operator fun div(that: Mass) = Acceleration

	operator fun div(that: EnergyDensity) = Area

	operator fun times(that: Distance) = Energy

	operator fun div(that: Area) = EnergyDensity

	operator fun div(that: Momentum) = Frequency

	operator fun div(that: Acceleration) = Mass

	operator fun div(that: Speed) = MassFlowRate
	operator fun times(that: Pace) = MassFlowRate

	operator fun div(that: Frequency) = Momentum
	operator fun times(that: Time) = Momentum

	operator fun div(that: Power) = Pace

	operator fun div(that: Pace) = Power
	operator fun times(that: Speed) = Power

	operator fun div(that: MassFlowRate) = Speed
}
