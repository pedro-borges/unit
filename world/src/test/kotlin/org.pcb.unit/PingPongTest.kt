package org.pcb.unit

import kotlin.test.Test
import org.pcb.unit.Earth.airAtomicMass
import org.pcb.unit.Earth.gasConstant
import org.pcb.unit.SIScale.CENTI
import org.pcb.unit.SIScale.MILLI
import org.pcb.unit.amounts.thermodynamics.Bar
import org.pcb.unit.base.Gram
import org.pcb.unit.base.Kelvin
import org.pcb.unit.base.Meter
import org.pcb.unit.chemisty.ChemicalElement.H
import org.pcb.unit.chemisty.ChemicalElement.He
import org.pcb.unit.config.Configuration
import org.pcb.unit.config.FancyRenderer
import org.pcb.unit.solid.Sphere

class PingPongTest
{
    @Test
    fun testPingPong()
    {
        Configuration.renderer = FancyRenderer()

        val fullBall = Sphere(Meter(2, CENTI))
        val gas = Sphere(fullBall.radius - Meter(0.55, MILLI))
        val weight = Gram(2.7)

        val pressure = Bar(1013.25, MILLI) // Sea level
        val temperature = Kelvin(293.15) // 20ºC

        val airConstant = gasConstant / airAtomicMass
        val airDensity = pressure / (airConstant * temperature)
        val airWeight = airDensity * gas.volume

        val heliumConstant = gasConstant / He.atomicMass
        val heliumDensity = pressure / (heliumConstant * temperature)
        val heliumWeight = heliumDensity * gas.volume

        val hydrogenConstant = gasConstant / H.atomicMass
        val hydrogenDensity = pressure / (hydrogenConstant * temperature)
        val hydrogenWeight = hydrogenDensity * gas.volume

        println(airWeight)
        println(heliumWeight)
        println(hydrogenWeight)
    }
}