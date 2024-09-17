package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Numeric
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Currency
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.Frequency
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class FrequencyAmount<T : Unit<Time>>(
        amount: Number,
        unit: Pair<Unit<Scalar>, T>)
    : Amount<Frequency>(amount = amount.toBigDecimal(),
                        unit = CompositeUnit(
                            domain = Frequency::class,
                            units = mapOf(unit.minor to -1)))
{
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Frequency>) =
            FrequencyAmount(amount = amount / unit.scale,
                            unit = NUMERIC to SECOND)
    }

    // region -> Frequency

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        FrequencyAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                        unit = NUMERIC to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        FrequencyAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                        unit = NUMERIC to time)

    @JvmName("plusFrequency")
    operator fun <T : Unit<Time>> plus(that: FrequencyAmount<T>) =
        FrequencyAmount(amount = amount + that.amount / convert(that.time to time),
                        unit = NUMERIC to time)

    operator fun plus(that: FrequencyAmount<T>) =
        FrequencyAmount(amount = amount + that.amount,
                        unit = NUMERIC to time)

    @JvmName("minusFrequency")
    operator fun <T : Unit<Time>> minus(that: FrequencyAmount<T>) =
        FrequencyAmount(amount = amount - that.amount / convert(that.time to time),
                        unit = NUMERIC to time)

    operator fun minus(that: FrequencyAmount<T>) =
        FrequencyAmount(amount = amount - that.amount,
                        unit = NUMERIC to time)

    // endregion

    // region -> Numeric

    @JvmName("divFrequency")
    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        Numeric(amount = amount / that.amount * convert(that.time to time))

    operator fun div(that: FrequencyAmount<T>) =
        Numeric(amount = amount / that.amount)

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        Numeric(amount = amount * that.amount * convert(that.time to time))

    operator fun times(that: TimeAmount<T>) =
        Numeric(amount = amount * that.amount)

    // endregion

    // region -> Speed

    operator fun <D : Unit<Distance>> times(that: DistanceAmount<D>) =
        SpeedAmount(amount = that.amount / amount,
                    unit = that.distance to time)

    // endregion

    // region -> Acceleration

    @JvmName("timesSpeed")
    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: SpeedAmount<D, T>) =
        AccelerationAmount(amount = that.amount / amount / convert(that.time to time),
                           unit = that.distance to time)

    operator fun <D : Unit<Distance>> times(that: SpeedAmount<D, T>) =
        AccelerationAmount(amount = that.amount / amount,
                           unit = that.distance to time)

    // endregion

    // region -> Currency

    @JvmName("timesDebt")
    operator fun <C : Unit<Currency>, T : Unit<Time>> times(that: DebtAmount<C, T>) =
        CurrencyAmount(amount = that.amount / amount * convert(that.time to time),
                       unit = that.currency)

    operator fun <C : Unit<Currency>> times(that: DebtAmount<C, T>) =
        CurrencyAmount(amount = that.amount / amount,
                       unit = that.currency)

    // endregion

    // region -> Rent

    operator fun <C : Unit<Currency>> times(that: CurrencyAmount<C>) =
        RentAmount(amount = that.amount / amount,
                   unit = that.currency to time)

    // endregion

    // region -> ElectricConductance

    @JvmName("timesCapacitance")
    operator fun <T : Unit<Time>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, D : Unit<Distance>> times(that: CapacitanceAmount<D, EC, M, T>) =
        ElectricConductanceAmount(amount = that.amount * amount * convert(that.time to time,
                                                                          that.time to time,
                                                                          that.time to time,
                                                                          that.time to time),
                                  unit = U2(time, that.electricCurrent) to U2(that.mass, that.distance))

    operator fun <EC : Unit<ElectricCurrent>, M : Unit<Mass>, D : Unit<Distance>> times(that: CapacitanceAmount<D, EC, M, T>) =
        ElectricConductanceAmount(amount = that.amount * amount,
                                  unit = U2(time, that.electricCurrent) to U2(that.mass, that.distance))

    // endregion
}
