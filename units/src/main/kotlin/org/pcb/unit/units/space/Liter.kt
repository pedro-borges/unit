package org.pcb.unit.units.space

import org.pcb.unit.Quadruple
import org.pcb.unit.Quintuple
import org.pcb.unit.Triple
import org.pcb.unit.Tuple
import org.pcb.unit.Unit
import org.pcb.unit.dimension.Volume
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.VolumeAmount
import org.pcb.unit.units.space.DeciMeter.DECIMETER

@Suppress("unused")
class Liter(amount: Number)
    : VolumeAmount<DECIMETER>(amount = amount,
                              unit = DECIMETER * DECIMETER * DECIMETER)
{
    constructor(amount: String) : this(amount.toBigDecimal())

    object LITER : Unit<Volume>(Volume::class)
    {
        override fun <B : Unit<*>> times(second: B) = Tuple(this, second)
        override fun <B : Unit<*>> div(second: B) = Pair(this, second)
        override fun <B : Tuple<*, *>> div(second: B) = Pair(this, second)
        override fun <B : Triple<*, *, *>> div(second: B) = Pair(this, second)
        override fun <B : Quadruple<*, *, *, *>> div(second: B) = Pair(this, second)
        override fun <B : Quintuple<*, *, *, *, *>> div(second: B) = Pair(this, second)
        override val symbol = "L"
        override val scale = 1.toBigBigDecimal()
    }
}