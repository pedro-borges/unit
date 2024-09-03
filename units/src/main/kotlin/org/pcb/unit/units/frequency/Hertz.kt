package org.pcb.unit.units.frequency

import org.pcb.unit.Quadruple
import org.pcb.unit.Quintuple
import org.pcb.unit.Triple
import org.pcb.unit.Tuple
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Frequency
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.FrequencyAmount
import org.pcb.unit.units.base.Second.SECOND
import org.pcb.unit.units.scalar.NaturalAmount.ONE

class Hertz(amount: Number)
    : FrequencyAmount<SECOND>(amount = amount.toBigBigDecimal(),
                              unit = ONE / SECOND)
{
    object HERTZ : Unit<Frequency>(Frequency::class)
    {
        override fun <B : Unit<*>> times(second: B) = Tuple(this, second)
        override fun <B : Unit<*>> div(second: B) = Pair(this, second)
        override fun <B : Tuple<*, *>> div(second: B) = Pair(this, second)
        override fun <B : Triple<*, *, *>> div(second: B) = Pair(this, second)
        override fun <B : Quadruple<*, *, *, *>> div(second: B) = Pair(this, second)
        override fun <B : Quintuple<*, *, *, *, *>> div(second: B) = Pair(this, second)
        override val symbol = "Hz"
        override val scale = 1.toBigBigDecimal()
    }
}