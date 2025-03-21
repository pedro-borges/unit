package org.pcb.unit.solid

import org.pcb.unit.Unit
import org.pcb.unit.amounts.DistanceAmount
import org.pcb.unit.config.Configuration.calculationMathContext
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.min
import org.pcb.unit.power
import org.pcb.unit.shape.Rectangle

open class Cuboid<L : Unit<Distance>>(val length: DistanceAmount<L>,
                                      val width: DistanceAmount<L>,
                                      val height: DistanceAmount<L>)
    : Solid<L>
{
    constructor(base: Rectangle<L>,
                height: DistanceAmount<L>)
            : this(base.length, base.width, height)

    override val narrowest = min(length, width, height)
    override val widest = DistanceAmount(amount = (length.amount.power(2) + width.amount.power(2) + height.amount.power(2)).sqrt(calculationMathContext),
                                         unit = length.distance)
    override val area = (length * width + length * height + width * height) * 2
    override val volume = length * width * height
    override val faces = 6
    override val edges = 12
    override val vertices = 8
}