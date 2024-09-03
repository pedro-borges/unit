package org.pcb.unit.units.finance

import org.pcb.unit.Quadruple
import org.pcb.unit.Quintuple
import org.pcb.unit.Sextuple
import org.pcb.unit.Triple
import org.pcb.unit.Tuple
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Currency
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.CurrencyAmount
import org.pcb.unit.units.finance.Gbp.GBP

class Gbp(amount: Number)
    : CurrencyAmount<GBP>(amount = amount,
                          unit = GBP)
{
    object GBP : Unit<Currency>(Currency::class)
    {
        override fun <B : Unit<*>> times(second: B) = Tuple(this, second)
        override fun <B : Unit<*>> div(second: B) = Pair(this, second)
        override fun <B : Tuple<*, *>> div(second: B) = Pair(this, second)
        override fun <B : Triple<*, *, *>> div(second: B) = Pair(this, second)
        override fun <B : Quadruple<*, *, *, *>> div(second: B) = Pair(this, second)
        override fun <B : Quintuple<*, *, *, *, *>> div(second: B) = Pair(this, second)
        override fun <B : Sextuple<*, *, *, *, *, *>> div(second: B) = Pair(this, second)

        override val symbol = "GBP"
        override val scale = 1.toBigBigDecimal()
    }
}