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
            actual = MomentumAmount(2, POUND, METER, SECOND) / MassAmount(32, OUNCE))
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
            actual = MomentumAmount(2, GRAM, FOOT, SECOND) / SpeedAmount(1440, INCH, MINUTE))
    }

    @Test
    fun divSameTime()
    {
        assertEquals(
            expected = ForceAmount(1, GRAM, METER, SECOND),
            actual = MomentumAmount(2, GRAM, METER, SECOND) / TimeAmount(2, SECOND))
    }

    @Test
    fun divOtherTime()
    {
        assertEquals(
            expected = ForceAmount(1, GRAM, METER, MINUTE),
            actual = MomentumAmount(2, GRAM, METER, MINUTE) / TimeAmount(120, SECOND))
    }
}