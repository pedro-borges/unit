package org.pcb.units

import kotlin.test.Test
import kotlin.test.assertEquals
import org.pcb.units.amount.ForceAmount
import org.pcb.units.amount.MassAmount
import org.pcb.units.amount.MomentumAmount
import org.pcb.units.amount.SpeedAmount
import org.pcb.units.amount.TimeAmount
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
            expected = MomentumAmount(1, POUND, FOOT, SECOND),
            actual = MomentumAmount(0, POUND, FOOT, SECOND) + MomentumAmount(16 * 12 * 60, OUNCE, INCH, MINUTE))
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
            expected = MomentumAmount(0, POUND, FOOT, SECOND),
            actual = MomentumAmount(1, POUND, FOOT, SECOND) - MomentumAmount(16 * 12 * 60, OUNCE, INCH, MINUTE))
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
            actual = MomentumAmount(2, POUND, FOOT, SECOND) / MomentumAmount(2 * 16 * 12 * 60, OUNCE, INCH, MINUTE))
    }

    @Test
    fun divSameMass()
    {
        assertEquals(
            expected = SpeedAmount(1, METER, SECOND),
            actual = MomentumAmount(2, GRAM, METER, SECOND) / MassAmount(2, GRAM))
    }

    @Test
    fun divOtherMass()
    {
        assertEquals(
            expected = SpeedAmount(1, METER, SECOND),
            actual = MomentumAmount(2, POUND, METER, SECOND) / MassAmount(2 * 16, OUNCE))
    }

    @Test
    fun divSameSpeed()
    {
        assertEquals(
            expected = MassAmount(1, GRAM),
            actual = MomentumAmount(2, GRAM, METER, SECOND) / SpeedAmount(2, METER, SECOND))
    }

    @Test
    fun divOtherSpeed()
    {
        assertEquals(
            expected = MassAmount(1, GRAM),
            actual = MomentumAmount(2, GRAM, FOOT, SECOND) / SpeedAmount(2 * 12 * 60, INCH, MINUTE))
    }

    @Test
    fun divSameTime()
    {
        assertEquals(
            expected = ForceAmount(1, GRAM, METER, SECOND, SECOND),
            actual = MomentumAmount(2, GRAM, METER, SECOND) / TimeAmount(2, SECOND))
    }

    @Test
    fun divOtherTime()
    {
        assertEquals(
            expected = ForceAmount(1, GRAM, METER, MINUTE, MINUTE),
            actual = MomentumAmount(2, GRAM, METER, MINUTE) / TimeAmount(2 * 60, SECOND))
    }
}