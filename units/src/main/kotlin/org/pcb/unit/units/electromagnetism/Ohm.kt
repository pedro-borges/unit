package org.pcb.unit.units.electromagnetism

import org.pcb.unit.Quadruple
import org.pcb.unit.Quintuple
import org.pcb.unit.Sextuple
import org.pcb.unit.Triple
import org.pcb.unit.Tuple
import org.pcb.unit.Unit
import org.pcb.unit.dimension.ElectricResistance
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.ElectricResistanceAmount
import org.pcb.unit.units.base.Ampere.AMPERE
import org.pcb.unit.units.base.Kilogram.KILOGRAM
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND

class Ohm(amount: Number)
    : ElectricResistanceAmount<KILOGRAM, METER, SECOND, AMPERE>(amount = amount,
                                                                unit = (KILOGRAM * METER * METER) /
                                                                       (SECOND * SECOND * SECOND * AMPERE * AMPERE))
{
    object OHM : Unit<ElectricResistance>(ElectricResistance::class)
    {
        override fun <B : Unit<*>> times(second: B) = Tuple(this, second)
        override fun <B : Unit<*>> div(second: B) = Pair(this, second)
        override fun <B : Tuple<*, *>> div(second: B) = Pair(this, second)
        override fun <B : Triple<*, *, *>> div(second: B) = Pair(this, second)
        override fun <B : Quadruple<*, *, *, *>> div(second: B) = Pair(this, second)
        override fun <B : Quintuple<*, *, *, *, *>> div(second: B) = Pair(this, second)
        override fun <B : Sextuple<*, *, *, *, *, *>> div(second: B) = Pair(this, second)

        override val symbol = "Ω"
        override val scale = 1.toBigBigDecimal()
    }
}
