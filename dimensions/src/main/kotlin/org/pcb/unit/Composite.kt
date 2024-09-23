package org.pcb.unit

annotation class Composite(
        val substance: Int = 0,
        val angle: Int = 0,
        val currency: Int = 0,
        val distance: Int = 0,
        val electricCurrent: Int = 0,
        val luminousIntensity: Int = 0,
        val mass: Int = 0,
        val temperature: Int = 0,
        val time: Int = 0)

operator fun Composite.plus(that: Composite) =
    Composite(substance = substance + that.substance,
              angle = angle + that.angle,
              currency = currency + that.currency,
              distance = distance + that.distance,
              electricCurrent = electricCurrent + that.electricCurrent,
              luminousIntensity = luminousIntensity + that.luminousIntensity,
              mass = mass + that.mass,
              temperature = temperature + that.temperature,
              time = time + that.time)

operator fun Composite.minus(that: Composite) =
    Composite(substance = substance - that.substance,
              angle = angle - that.angle,
              currency = currency - that.currency,
              distance = distance - that.distance,
              electricCurrent = electricCurrent - that.electricCurrent,
              luminousIntensity = luminousIntensity - that.luminousIntensity,
              mass = mass - that.mass,
              temperature = temperature - that.temperature,
              time = time - that.time)
