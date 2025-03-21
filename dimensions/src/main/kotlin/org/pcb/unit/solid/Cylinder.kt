package org.pcb.unit.solid

import org.pcb.unit.Unit
import org.pcb.unit.amounts.DistanceAmount
import org.pcb.unit.config.Configuration.calculationMathContext
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.min
import org.pcb.unit.power
import org.pcb.unit.shape.Circle

class Cylinder<L : Unit<Distance>>(val radius: DistanceAmount<L>,
                                   val height: DistanceAmount<L>)
    : Solid<L>
{
    constructor(base: Circle<L>,
                height: DistanceAmount<L>) : this(base.radius, height)

    private val base = Circle(radius)

    override val narrowest = min(base.diameter, height)
    override val widest = DistanceAmount(amount = (base.diameter.amount.power(2) + height.amount.power(2)).sqrt(calculationMathContext),
                                         unit = base.diameter.distance)
    override val area = base.area + base.perimeter * height + base.area
    override val volume = base.area * height
    override val faces = 3
    override val edges = 2
    override val vertices = 0
}