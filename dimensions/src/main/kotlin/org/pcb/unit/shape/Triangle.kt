package org.pcb.unit.shape

import org.pcb.unit.Unit
import org.pcb.unit.amounts.AreaAmount
import org.pcb.unit.amounts.DistanceAmount
import org.pcb.unit.config.Configuration.calculationMathContext
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.div
import org.pcb.unit.max
import org.pcb.unit.min

class Triangle<L : Unit<Distance>>(val a: DistanceAmount<L>,
                                   val b: DistanceAmount<L> = a,
                                   val c: DistanceAmount<L> = b)
    : Shape<L>
{
    override val narrowest = min(a, b, c)
    override val widest = max(a, b, c)
    override val perimeter = a + b + c
    override val area = AreaAmount(amount = (((a + b + c).amount / 2).let { it * (it - a.amount) * (it - b.amount) * (it - c.amount) }.sqrt(calculationMathContext)),
                                   unit = a.distance)
}