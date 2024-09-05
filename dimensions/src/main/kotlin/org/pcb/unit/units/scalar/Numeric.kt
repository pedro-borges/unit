package org.pcb.unit.units.scalar

import org.pcb.unit.Quadruple
import org.pcb.unit.Quintuple
import org.pcb.unit.Sextuple
import org.pcb.unit.Triple
import org.pcb.unit.Tuple
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.ScalarAmount
import org.pcb.unit.units.scalar.Numeric.NUMBER

class Numeric(amount: Number)
    : ScalarAmount<NUMBER>(amount = amount,
                           unit = ONE)
{
    object ONE : NUMBER(1)

    open class NUMBER(scale: Number) : Unit<Scalar>(Scalar::class)
    {
        override fun <B : Unit<*>> times(second: B) = Tuple(this, second)
        override fun <B : Unit<*>> div(second: B) = Pair(this, second)
        override fun <B : Tuple<*, *>> div(second: B) = Pair(this, second)
        override fun <B : Triple<*, *, *>> div(second: B) = Pair(this, second)
        override fun <B : Quadruple<*, *, *, *>> div(second: B) = Pair(this, second)
        override fun <B : Quintuple<*, *, *, *, *>> div(second: B) = Pair(this, second)
        override fun <B : Sextuple<*, *, *, *, *, *>> div(second: B) = Pair(this, second)

        override val symbol = ""
        override val scale = scale.toBigBigDecimal()
    }
}