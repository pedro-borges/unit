package org.pcb.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.units.amount.MomentumAmount
import org.pcb.units.amount.mass.Gram.GRAM
import org.pcb.units.amount.mass.Ounce.OUNCE
import org.pcb.units.amount.mass.Pound.POUND
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.amount.space.Foot.FOOT
import org.pcb.units.amount.space.Inch.INCH
import org.pcb.units.amount.space.Meter.METER
import org.pcb.units.amount.time.Minute.MINUTE
import org.pcb.units.amount.time.Second.SECOND

class MomentumAmountTest
{
    @Test
    fun plusSameMomentum()
    {
        assertEquals(
            expected = MomentumAmount(1, GRAM, METER, SECOND),
            actual = MomentumAmount(0, GRAM, METER, SECOND) + MomentumAmount(1, GRAM, METER, SECOND))
    }

    @Test
    fun plusOtherMomentum()
    {
        assertEquals(
            expected = MomentumAmount(1, POUND, FOOT, MINUTE),
            actual = MomentumAmount(0, POUND, FOOT, MINUTE) + MomentumAmount(3.2, OUNCE, INCH, SECOND))
    }

    @Test
    fun minusSameMomentum()
    {
        assertEquals(
            expected = MomentumAmount(0, GRAM, METER, SECOND),
            actual = MomentumAmount(1, GRAM, METER, SECOND) - MomentumAmount(1, GRAM, METER, SECOND))
    }

    @Test
    fun minusOtherMomentum()
    {
        assertEquals(
            expected = MomentumAmount(0, POUND, FOOT, MINUTE),
            actual = MomentumAmount(1, POUND, FOOT, MINUTE) - MomentumAmount(3.2, OUNCE, INCH, SECOND))
    }

    @Test
    fun divSameMomentum()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = MomentumAmount(2, GRAM, METER, SECOND) / MomentumAmount(2, GRAM, METER, SECOND))
    }

    @Test
    fun divOtherMomentum()
    {
        assertEquals(
            expected = NaturalAmount(1),
            actual = MomentumAmount(2, POUND, FOOT, MINUTE) / MomentumAmount(6.4, OUNCE, INCH, SECOND))
    }
}