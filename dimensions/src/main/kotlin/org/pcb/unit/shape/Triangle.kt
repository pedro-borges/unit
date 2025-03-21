package org.pcb.unit.shape

import org.pcb.unit.Unit
import org.pcb.unit.amounts.AreaAmount
import org.pcb.unit.amounts.DistanceAmount
import org.pcb.unit.config.Configuration.calculationMathContext
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.div
import org.pcb.unit.max
import org.pcb.unit.min
import org.pcb.unit.solid.TriangularPrism

class Triangle<L : Unit<Distance>>(val a: DistanceAmount<L>,
                                   val b: DistanceAmount<L> = a,
                                   val c: DistanceAmount<L> = b)
    : Shape<L>
{
    override val narrowest = min(a, b, c)
    override val widest = max(a, b, c)
    override val perimeter = a + b + c
    override val area = AreaAmount(amount = (((a.amount + b.amount + c.amount) / 2).let { it * (it - a.amount) * (it - b.amount) * (it - c.amount) }.sqrt(calculationMathContext)),
                                   unit = a.distance)
    override val edges = 3
    override val vertices = 3

    @JvmName("timesHeight")
    operator fun <L : Unit<Distance>> times(height: DistanceAmount<L>) =
        TriangularPrism(base = this,
                        height = DistanceAmount(amount = height.amount * convert(height.distance to a.distance),
                                                unit = a.distance))

    operator fun times(height: DistanceAmount<L>) =
        TriangularPrism(base = this,
                        height = height)
}
