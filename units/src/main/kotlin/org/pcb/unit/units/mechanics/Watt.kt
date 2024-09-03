package org.pcb.unit.units.mechanics

import org.pcb.unit.Quadruple
import org.pcb.unit.Quintuple
import org.pcb.unit.Triple
import org.pcb.unit.Tuple
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Power
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.PowerAmount
import org.pcb.unit.units.base.Kilogram.KILOGRAM
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND

class Watt(amount: Number)
    : PowerAmount<KILOGRAM, METER, SECOND>(amount = amount,
                                           unit = (KILOGRAM * METER * METER) /
                                                  (SECOND * SECOND * SECOND))
{
    object WATT : Unit<Power>(Power::class)
    {
        override fun <B : Unit<*>> times(second: B) = Tuple(this, second)
        override fun <B : Unit<*>> div(second: B) = Pair(this, second)
        override fun <B : Tuple<*, *>> div(second: B) = Pair(this, second)
        override fun <B : Triple<*, *, *>> div(second: B) = Pair(this, second)
        override fun <B : Quadruple<*, *, *, *>> div(second: B) = Pair(this, second)
        override fun <B : Quintuple<*, *, *, *, *>> div(second: B) = Pair(this, second)
        override val symbol = "W"
        override val scale = 1.toBigBigDecimal()
    }
}