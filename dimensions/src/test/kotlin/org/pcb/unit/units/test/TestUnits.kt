package org.pcb.unit.units.test

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Currency
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.toBigBigDecimal

object CURRENCY1 : Unit<Currency>(Currency::class, "C1", 1.toBigBigDecimal())

object CURRENCY2 : Unit<Currency>(Currency::class, "C2", 10.toBigBigDecimal())

object ELECTRICCURRENT1 : Unit<ElectricCurrent>(ElectricCurrent::class, "ECu1", 1.toBigBigDecimal())

object ELECTRICCURRENT2 : Unit<ElectricCurrent>(ElectricCurrent::class, "ECu2", 10.toBigBigDecimal())

object DISTANCE1 : Unit<Distance>(Distance::class, "L1", 1.toBigBigDecimal())

object DISTANCE2 : Unit<Distance>(Distance::class, "L2", 10.toBigBigDecimal())

object MASS1 : Unit<Mass>(Mass::class, "M1", 1.toBigBigDecimal())

object MASS2 : Unit<Mass>(Mass::class, "M2", 10.toBigBigDecimal())

object TIME1 : Unit<Time>(Time::class, "T1", 1.toBigBigDecimal())

object TIME2 : Unit<Time>(Time::class, "T2", 10.toBigBigDecimal())
