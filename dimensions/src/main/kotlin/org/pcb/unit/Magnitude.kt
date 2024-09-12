@file:Suppress("unused")

package org.pcb.unit

import java.math.BigDecimal.TEN
import org.pcb.unit.Magnitude.ATTO
import org.pcb.unit.Magnitude.CENTI
import org.pcb.unit.Magnitude.DECA
import org.pcb.unit.Magnitude.DECI
import org.pcb.unit.Magnitude.EXA
import org.pcb.unit.Magnitude.FEMTO
import org.pcb.unit.Magnitude.GIGA
import org.pcb.unit.Magnitude.HECTO
import org.pcb.unit.Magnitude.KILO
import org.pcb.unit.Magnitude.MEGA
import org.pcb.unit.Magnitude.MICRO
import org.pcb.unit.Magnitude.MILLI
import org.pcb.unit.Magnitude.NANO
import org.pcb.unit.Magnitude.PETA
import org.pcb.unit.Magnitude.PICO
import org.pcb.unit.Magnitude.QUECTO
import org.pcb.unit.Magnitude.QUETTA
import org.pcb.unit.Magnitude.RONNA
import org.pcb.unit.Magnitude.RONTO
import org.pcb.unit.Magnitude.TERA
import org.pcb.unit.Magnitude.UNI
import org.pcb.unit.Magnitude.YOCTO
import org.pcb.unit.Magnitude.YOTTA
import org.pcb.unit.Magnitude.ZEPTO
import org.pcb.unit.Magnitude.ZETTA
import org.pcb.unit.dimension.Dimension

enum class Magnitude(val symbol: String,
                     val power: Int)
{
    QUETTA("Q", 30),
    RONNA("R", 27),
    YOTTA("Y", 24),
    ZETTA("Z", 21),
    EXA("E", 18),
    PETA("P", 15),
    TERA("T", 12),
    GIGA("G", 9),
    MEGA("M", 6),
    KILO("k", 3),
    HECTO("h", 2),
    DECA("da", 1),
    UNI("", 0),
    DECI("d", -1),
    CENTI("c", -2),
    MILLI("m", -3),
    MICRO("μ", -6),
    NANO("n", -9),
    PICO("p", -12),
    FEMTO("f", -15),
    ATTO("a", -18),
    ZEPTO("z", -21),
    YOCTO("y", -24),
    RONTO("r", -27),
    QUECTO("q", -30);

    val scale = TEN.power(power)

    operator fun times(that: Magnitude) =
        get(power + that.power)

    companion object
    {
        fun get(power: Int) =
            entries.firstOrNull { it.power == power }
            ?: throw IllegalArgumentException("No magnitude for 10^$power")
    }
}

// @formatter:off
fun <D : Dimension> quetta(unit: Unit<D>) = object : Unit<D>(unit.domain, unit.symbol, QUETTA * unit.magnitude, unit.scale) {}
fun <D : Dimension> ronna(unit: Unit<D>) = object : Unit<D>(unit.domain, unit.symbol, RONNA * unit.magnitude, unit.scale) {}
fun <D : Dimension> yotta(unit: Unit<D>) = object : Unit<D>(unit.domain, unit.symbol, YOTTA * unit.magnitude, unit.scale) {}
fun <D : Dimension> zetta(unit: Unit<D>) = object : Unit<D>(unit.domain, unit.symbol, ZETTA * unit.magnitude, unit.scale) {}
fun <D : Dimension> exa(unit: Unit<D>) = object : Unit<D>(unit.domain, unit.symbol, EXA * unit.magnitude, unit.scale) {}
fun <D : Dimension> peta(unit: Unit<D>) = object : Unit<D>(unit.domain, unit.symbol, PETA * unit.magnitude, unit.scale) {}
fun <D : Dimension> tera(unit: Unit<D>) = object : Unit<D>(unit.domain, unit.symbol, TERA * unit.magnitude, unit.scale) {}
fun <D : Dimension> giga(unit: Unit<D>) = object : Unit<D>(unit.domain, unit.symbol, GIGA * unit.magnitude, unit.scale) {}
fun <D : Dimension> mega(unit: Unit<D>) = object : Unit<D>(unit.domain, unit.symbol, MEGA * unit.magnitude, unit.scale) {}
fun <D : Dimension> kilo(unit: Unit<D>) = object : Unit<D>(unit.domain, unit.symbol, KILO * unit.magnitude, unit.scale) {}
fun <D : Dimension> hecto(unit: Unit<D>) = object : Unit<D>(unit.domain, unit.symbol, HECTO * unit.magnitude, unit.scale) {}
fun <D : Dimension> deca(unit: Unit<D>) = object : Unit<D>(unit.domain, unit.symbol, DECA * unit.magnitude, unit.scale) {}
fun <D : Dimension> uni(unit: Unit<D>) = object : Unit<D>(unit.domain, unit.symbol, UNI * unit.magnitude, unit.scale) {}
fun <D : Dimension> deci(unit: Unit<D>) = object : Unit<D>(unit.domain, unit.symbol, DECI * unit.magnitude, unit.scale) {}
fun <D : Dimension> centi(unit: Unit<D>) = object : Unit<D>(unit.domain, unit.symbol, CENTI * unit.magnitude, unit.scale) {}
fun <D : Dimension> milli(unit: Unit<D>) = object : Unit<D>(unit.domain, unit.symbol, MILLI * unit.magnitude, unit.scale) {}
fun <D : Dimension> micro(unit: Unit<D>) = object : Unit<D>(unit.domain, unit.symbol, MICRO * unit.magnitude, unit.scale) {}
fun <D : Dimension> nano(unit: Unit<D>) = object : Unit<D>(unit.domain, unit.symbol, NANO * unit.magnitude, unit.scale) {}
fun <D : Dimension> pico(unit: Unit<D>) = object : Unit<D>(unit.domain, unit.symbol, PICO * unit.magnitude, unit.scale) {}
fun <D : Dimension> femto(unit: Unit<D>) = object : Unit<D>(unit.domain, unit.symbol, FEMTO * unit.magnitude, unit.scale) {}
fun <D : Dimension> atto(unit: Unit<D>) = object : Unit<D>(unit.domain, unit.symbol, ATTO * unit.magnitude, unit.scale) {}
fun <D : Dimension> zepto(unit: Unit<D>) = object : Unit<D>(unit.domain, unit.symbol, ZEPTO * unit.magnitude, unit.scale) {}
fun <D : Dimension> yocto(unit: Unit<D>) = object : Unit<D>(unit.domain, unit.symbol, YOCTO * unit.magnitude, unit.scale) {}
fun <D : Dimension> ronto(unit: Unit<D>) = object : Unit<D>(unit.domain, unit.symbol, RONTO * unit.magnitude, unit.scale) {}
fun <D : Dimension> quecto(unit: Unit<D>) = object : Unit<D>(unit.domain, unit.symbol, QUECTO * unit.magnitude, unit.scale) {}
// @formatter:on

