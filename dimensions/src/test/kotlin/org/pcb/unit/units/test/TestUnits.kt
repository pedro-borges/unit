package org.pcb.unit.units.test

import org.pcb.unit.Unit
import org.pcb.unit.dimension.Currency
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Time
import org.pcb.unit.toBigBigDecimal

object CURRENCY1 : Unit<Currency>(Currency::class)
{
    override val symbol = "C1"
    override val scale = 1.toBigBigDecimal()
}

object CURRENCY2 : Unit<Currency>(Currency::class)
{
    override val symbol = "C2"
    override val scale = 10.toBigBigDecimal()
}

object ELECTRICCURRENT1 : Unit<ElectricCurrent>(ElectricCurrent::class)
{
    override val symbol = "ECu1"
    override val scale = 1.toBigBigDecimal()
}

object ELECTRICCURRENT2 : Unit<ElectricCurrent>(ElectricCurrent::class)
{
    override val symbol = "ECu2"
    override val scale = 10.toBigBigDecimal()
}

object DISTANCE1 : Unit<Distance>(Distance::class)
{
    override val symbol = "L1"
    override val scale = 1.toBigBigDecimal()
}

object DISTANCE2 : Unit<Distance>(Distance::class)
{
    override val symbol = "L2"
    override val scale = 10.toBigBigDecimal()
}

object MASS1 : Unit<Mass>(Mass::class)
{
    override val symbol = "M1"
    override val scale = 1.toBigBigDecimal()
}

object MASS2 : Unit<Mass>(Mass::class)
{
    override val symbol = "M2"
    override val scale = 10.toBigBigDecimal()
}

object TIME1 : Unit<Time>(Time::class)
{
    override val symbol = "T1"
    override val scale = 1.toBigBigDecimal()
}

object TIME2 : Unit<Time>(Time::class)
{
    override val symbol = "T2"
    override val scale = 10.toBigBigDecimal()
}
