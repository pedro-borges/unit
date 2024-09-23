package org.pcb.unit.generator

import org.pcb.unit.Composite

object Dimensions
{
    val DIMENSIONS = listOf("Acceleration" to Composite(distance = 1, time = -2),
                            "Action" to Composite(distance = 2, mass = 1, time = -1),
                            "Angle" to Composite(angle = 1),
                            "AngularAcceleration" to Composite(angle = 1, time = -2),
                            "AngularMomentum" to Composite(mass = 2, distance = 1, time = -1),
                            "AngularVelocity" to Composite(angle = 1, time = -1),
                            "Area" to Composite(distance = 2),
                            "AreaDensity" to Composite(mass = 1, distance = -2),
                            "Capacitance" to Composite(time = 4, electricCurrent = 2, mass = -1, distance = -2),
                            "CatalyticEfficiency" to Composite(distance = 3, substance = -1, time = -1),
                            "Currency" to Composite(currency = 1),
                            "Debt" to Composite(currency = 1, time = 1),
                            "Distance" to Composite(distance = 1),
                            "ElectricCharge" to Composite(electricCurrent = 1, time = 1),
                            "ElectricConductance" to Composite(time = 3, electricCurrent = 2, mass = -1, distance = -2),
                            "ElectricCurrent" to Composite(electricCurrent = 1),
                            "ElectricResistance" to Composite(distance = 2, mass = 1, electricCurrent = -2, time = -3),
                            "ElectricTension" to Composite(distance = 2, mass = 1, electricCurrent = -1, time = -3),
                            "Energy" to Composite(distance = 2, mass = 1, time = -2),
                            "EnergyFluxDensity" to Composite(mass = 1, time = -3),
                            "EnergyDensity" to Composite(mass = 1, distance = -1, time = -2),
                            "Force" to Composite(distance = 1, mass = 1, time = -2),
                            "Frequency" to Composite(time = -1),
                            "FuelEfficiency" to Composite(distance = -2),
                            "HeatCapacity" to Composite(distance = 2, mass = 1, temperature = -1, time = -2),
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
                            "MassDensity" to Composite(mass = 1, distance = -3),
                            "MassFlowRate" to Composite(mass = 1, time = -1),
                            "Molality" to Composite(substance = 1, mass = -1),
                            "Molarity" to Composite(substance = 1, distance = -3),
                            "MolarMass" to Composite(mass = 1, substance = -1),
                            "MolarConductivity" to Composite(time = 3, electricCurrent = 2, substance = -1, mass = -1),
                            "MolarEnergy" to Composite(distance = 2, mass = 1, substance = -1, time = -2),
                            "MolarHeatCapacity" to Composite(distance = 2, mass = 1, substance = -1, temperature = -1, time = -2),
                            "MolarVolume" to Composite(distance = 3, substance = -1),
                            "Momentum" to Composite(mass = 1, distance = 1, time = -1),
                            "MomentOfInertia" to Composite(distance = 2, mass = 1),
                            "Pace" to Composite(time = 1, distance = -1),
                            "Power" to Composite(distance = 2, mass = 1, time = -3),
                            "PowerDensity" to Composite(mass = 1, distance = -1, time = -3),
                            "Rent" to Composite(currency = 1, time = -1),
                            "RadiantExposure" to Composite(mass = 1, time = -2),
                            "RadiantIntensity" to Composite(distance = 2, mass = 1, angle = -2, time = -3),
                            "Scalar" to Composite(),
                            "Snap" to Composite(distance = 1, time = -4),
                            "SolidAngle" to Composite(angle = 2),
                            "SpacialFrequency" to Composite(distance = -1),
                            "SpecificEnergy" to Composite(distance = 2, time = -2),
                            "SpecificHeatCapacity" to Composite(distance = 2, temperature = -1, time = -2),
                            "SpecificVolume" to Composite(distance = 3, mass = -1),
                            "SpectralIntensity" to Composite(distance = 1, mass = 1, angle = -2, time = -3),
                            "Substance" to Composite(substance = 1),
                            "Speed" to Composite(distance = 1, time = -1),
                            "Temperature" to Composite(temperature = 1),
                            "TemperatureGradient" to Composite(temperature = 1, distance = -1),
                            "ThermalConductivity" to Composite(distance = 1, mass = 1, temperature = -1, time = -3),
                            "ThermalExpansionCoefficient" to Composite(temperature = -1),
                            "ThermalResistance" to Composite(time = 3, temperature = 1, mass = -1, distance = 2),
                            "Time" to Composite(time = 1),
                            "Volume" to Composite(distance = 3),
                            "VolumetricFlow" to Composite(distance = 3, time = -1),
                            "Yank" to Composite(distance = 1, mass = 1, time = -3))

    fun Composite.max() =
        max(substance, angle, currency, distance, electricCurrent, luminousIntensity, mass, temperature, time)

    fun Composite.min() =
        min(substance, angle, currency, distance, electricCurrent, luminousIntensity, mass, temperature, time)

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