package org.pcb.unit.shape

import org.pcb.unit.Unit
import org.pcb.unit.amounts.DistanceAmount
import org.pcb.unit.config.Configuration.calculationMathContext
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.power
import org.pcb.unit.times

class Square<L : Unit<Distance>>(side: DistanceAmount<L>)
    : Shape<L>
{
    override val narrowest = side
    override val widest = DistanceAmount(amount = (side.amount.power(2) * 2).sqrt(calculationMathContext),
                                         unit = side.distance)
    override val perimeter = side * 4
    override val area = side * side
}