package org.pcb.unit.generator

import org.pcb.unit.Composite

object Dimensions
{
    val DIMENSIONS = listOf("Acceleration" to Composite(distance = 1, time = -2),
                            "Angle" to Composite(angle = 1),
                            "AngularAcceleration" to Composite(angle = 1, time = -2),
                            "AngularMomentum" to Composite(mass = 2, distance = 1, time = -1),
                            "AngularVelocity" to Composite(angle = 1, time = -1),
                            "Area" to Composite(distance = 2),
                            "Capacitance" to Composite(time = 4, electricCurrent = 2, mass = -1, distance = -2),
                            "Currency" to Composite(currency = 1),
                            "Debt" to Composite(currency = 1, time = 1),
                            "Distance" to Composite(distance = 1),
                            "ElectricCharge" to Composite(electricCurrent = 1, time = 1),
                            "ElectricConductance" to Composite(time = 3, electricCurrent = 2, mass = -1, distance = -2),
                            "ElectricCurrent" to Composite(electricCurrent = 1),
                            "ElectricResistance" to Composite(distance = 2, mass = 1, electricCurrent = -2, time = -3),
                            "ElectricTension" to Composite(distance = 2, mass = 1, electricCurrent = -1, time = -3),
                            "Energy" to Composite(distance = 2, mass = 1, time = -2),
                            "EnergyDensity" to Composite(mass = 1, distance = -1, time = -2),
                            "Force" to Composite(distance = 1, mass = 1, time = -2),
                            "Frequency" to Composite(time = -1),
                            "Illuminance" to Composite(luminousIntensity = 1, angle = 2, distance = -2),
                            "Inductance" to Composite(distance = 2, mass = 1, electricCurrent = -2, time = -2),
                            "Jerk" to Composite(distance = 1, time = -3),
                            "Luminance" to Composite(luminousIntensity = 1, distance = -2),
                            "LuminousEnergy" to Composite(angle = 2, luminousIntensity = 1, time = 1),
                            "LuminousFlux" to Composite(angle = 2, luminousIntensity = 1),
                            "LuminousIntensity" to Composite(luminousIntensity = 1),
                            "MagneticFlux" to Composite(distance = 2, mass = 1, electricCurrent = -1, time = -2),
                            "MagneticFluxDensity" to Composite(mass = 1, electricCurrent = -1, time = -2),
                            "Mass" to Composite(mass = 1),
                            "MassFlowRate" to Composite(mass = 1, time = -1),
                            "Momentum" to Composite(mass = 1, distance = 1, time = -1),
                            "Pace" to Composite(time = 1, distance = -1),
                            "Power" to Composite(distance = 2, mass = 1, time = -3),
                            "Rent" to Composite(currency = 1, time = -1),
                            "Scalar" to Composite(),
                            "SolidAngle" to Composite(angle = 2),
                            "SpecificVolume" to Composite(distance = 3, mass = -1),
                            "Speed" to Composite(distance = 1, time = -1),
                            "Temperature" to Composite(temperature = 1),
                            "Time" to Composite(time = 1),
                            "Volume" to Composite(distance = 3))

    fun Composite.max() =
        max(angle, currency, distance, electricCurrent, luminousIntensity, mass, temperature, time)

    fun Composite.min() =
        min(angle, currency, distance, electricCurrent, luminousIntensity, mass, temperature, time)

    fun min(value: Int, vararg values: Int): Int
    {
        var result = value

        for (v in values)
        {
            result = kotlin.math.min(result, v)
        }

        return result
    }

    fun max(value: Int, vararg values: Int): Int
    {
        var result = value

        for (v in values)
        {
            result = kotlin.math.max(result, v)
        }

        return result
    }
}