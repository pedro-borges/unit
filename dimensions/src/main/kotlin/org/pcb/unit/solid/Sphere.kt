package org.pcb.unit.solid

import org.pcb.unit.Constants.PI
import org.pcb.unit.Unit
import org.pcb.unit.amounts.AreaAmount
import org.pcb.unit.amounts.DistanceAmount
import org.pcb.unit.amounts.VolumeAmount
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.div
import org.pcb.unit.power
import org.pcb.unit.times

class Sphere<L : Unit<Distance>>(val radius: DistanceAmount<L>)
    : Solid<L>
{
    override val narrowest = radius * 2
    override val widest = radius * 2
    override val area = AreaAmount(amount = PI * radius.amount.power(2) * 4,
                                   unit = radius.distance)
    override val volume = VolumeAmount(amount = PI * radius.amount.power(3) * 4 / 3,
                                       unit = radius.distance)
}