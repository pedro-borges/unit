package org.pcb.unit.solid

import org.pcb.unit.Unit
import org.pcb.unit.amounts.DistanceAmount
import org.pcb.unit.config.Configuration.calculationMathContext
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.min
import org.pcb.unit.power
import org.pcb.unit.shape.Triangle

open class TriangularPrism<L : Unit<Distance>>(val a: DistanceAmount<L>,
                                               val b: DistanceAmount<L>,
                                               val c: DistanceAmount<L>,
                                               val height: DistanceAmount<L>)
    : Solid<L>
{
    constructor(base: Triangle<L>,
                height: DistanceAmount<L>)
            : this(base.a, base.b, base.c, height)

    private val base = Triangle(a, b, c)

    override val narrowest = min(a, b, c, height)
    override val widest = DistanceAmount(amount = (base.widest.amount.power(2) + height.amount.power(2)).sqrt(calculationMathContext),
                                         unit = a.distance)
    override val area = base.area + base.perimeter * height + base.area
    override val volume = base.area * height
    override val faces = 5
    override val edges = 9
    override val vertices = 6
}