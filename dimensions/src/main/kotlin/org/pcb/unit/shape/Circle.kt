package org.pcb.unit.shape

import org.pcb.unit.Constants.PI
import org.pcb.unit.Unit
import org.pcb.unit.amounts.AreaAmount
import org.pcb.unit.amounts.DistanceAmount
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.power
import org.pcb.unit.times

class Circle<L : Unit<Distance>>(val radius: DistanceAmount<L>)
    : Shape<L>
{
    override val narrowest = DistanceAmount(amount = radius.amount * 2,
                                            unit = radius.distance)
    override val widest = DistanceAmount(amount = radius.amount * 2,
                                         unit = radius.distance)
    override val perimeter = DistanceAmount(amount = PI * radius.amount * 2,
                                            unit = radius.distance)
    override val area = AreaAmount(amount = PI * radius.amount.power(2),
                                   unit = radius.distance)
}