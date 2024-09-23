@file:Suppress("unused")

package org.pcb.unit

import org.pcb.unit.SIScale.ATTO
import org.pcb.unit.SIScale.CENTI
import org.pcb.unit.SIScale.Companion.get
import org.pcb.unit.SIScale.DECA
import org.pcb.unit.SIScale.DECI
import org.pcb.unit.SIScale.EXA
import org.pcb.unit.SIScale.FEMTO
import org.pcb.unit.SIScale.GIGA
import org.pcb.unit.SIScale.HECTO
import org.pcb.unit.SIScale.KILO
import org.pcb.unit.SIScale.MEGA
import org.pcb.unit.SIScale.MICRO
import org.pcb.unit.SIScale.MILLI
import org.pcb.unit.SIScale.NANO
import org.pcb.unit.SIScale.PETA
import org.pcb.unit.SIScale.PICO
import org.pcb.unit.SIScale.QUECTO
import org.pcb.unit.SIScale.QUETTA
import org.pcb.unit.SIScale.RONNA
import org.pcb.unit.SIScale.RONTO
import org.pcb.unit.SIScale.TERA
import org.pcb.unit.SIScale.YOCTO
import org.pcb.unit.SIScale.YOTTA
import org.pcb.unit.SIScale.ZEPTO
import org.pcb.unit.SIScale.ZETTA
import org.pcb.unit.dimensions.Dimension

interface Magnitude
{
    val symbol: String
    val power: Int

    operator fun times(that: Magnitude) =
        get(power + that.power)

    companion object
    {
        val UNI = object : Magnitude
        {
            override val symbol = ""
            override val power = 0
        }
    }
}

class Scale(override val symbol: String,
            override val power: Int) : Magnitude

enum class SIScale(override val symbol: String,
                   override val power: Int) : Magnitude
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

    companion object
    {
        fun get(power: Int) =
            entries.firstOrNull { it.power == power }
            ?: Scale("10" + power.toSuperscript(), power)
    }
}

// @formatter:off
fun <D : Dimension> quetta(unit: Unit<D>) = object : Unit<D>(unit.dimension, unit.symbol, unit.scale, QUETTA * unit.magnitude) {}
fun <D : Dimension> ronna(unit: Unit<D>) = object : Unit<D>(unit.dimension, unit.symbol, unit.scale, RONNA * unit.magnitude) {}
fun <D : Dimension> yotta(unit: Unit<D>) = object : Unit<D>(unit.dimension, unit.symbol, unit.scale, YOTTA * unit.magnitude) {}
fun <D : Dimension> zetta(unit: Unit<D>) = object : Unit<D>(unit.dimension, unit.symbol, unit.scale, ZETTA * unit.magnitude) {}
fun <D : Dimension> exa(unit: Unit<D>) = object : Unit<D>(unit.dimension, unit.symbol, unit.scale, EXA * unit.magnitude) {}
fun <D : Dimension> peta(unit: Unit<D>) = object : Unit<D>(unit.dimension, unit.symbol, unit.scale, PETA * unit.magnitude) {}
fun <D : Dimension> tera(unit: Unit<D>) = object : Unit<D>(unit.dimension, unit.symbol, unit.scale, TERA * unit.magnitude) {}
fun <D : Dimension> giga(unit: Unit<D>) = object : Unit<D>(unit.dimension, unit.symbol, unit.scale, GIGA * unit.magnitude) {}
fun <D : Dimension> mega(unit: Unit<D>) = object : Unit<D>(unit.dimension, unit.symbol, unit.scale, MEGA * unit.magnitude) {}
fun <D : Dimension> kilo(unit: Unit<D>) = object : Unit<D>(unit.dimension, unit.symbol, unit.scale, KILO * unit.magnitude) {}
fun <D : Dimension> hecto(unit: Unit<D>) = object : Unit<D>(unit.dimension, unit.symbol, unit.scale, HECTO * unit.magnitude) {}
fun <D : Dimension> deca(unit: Unit<D>) = object : Unit<D>(unit.dimension, unit.symbol, unit.scale, DECA * unit.magnitude) {}
fun <D : Dimension> deci(unit: Unit<D>) = object : Unit<D>(unit.dimension, unit.symbol, unit.scale, DECI * unit.magnitude) {}
fun <D : Dimension> centi(unit: Unit<D>) = object : Unit<D>(unit.dimension, unit.symbol, unit.scale, CENTI * unit.magnitude) {}
fun <D : Dimension> milli(unit: Unit<D>) = object : Unit<D>(unit.dimension, unit.symbol, unit.scale, MILLI * unit.magnitude) {}
fun <D : Dimension> micro(unit: Unit<D>) = object : Unit<D>(unit.dimension, unit.symbol, unit.scale, MICRO * unit.magnitude) {}
fun <D : Dimension> nano(unit: Unit<D>) = object : Unit<D>(unit.dimension, unit.symbol, unit.scale, NANO * unit.magnitude) {}
fun <D : Dimension> pico(unit: Unit<D>) = object : Unit<D>(unit.dimension, unit.symbol, unit.scale, PICO * unit.magnitude) {}
fun <D : Dimension> femto(unit: Unit<D>) = object : Unit<D>(unit.dimension, unit.symbol, unit.scale, FEMTO * unit.magnitude) {}
fun <D : Dimension> atto(unit: Unit<D>) = object : Unit<D>(unit.dimension, unit.symbol, unit.scale, ATTO * unit.magnitude) {}
fun <D : Dimension> zepto(unit: Unit<D>) = object : Unit<D>(unit.dimension, unit.symbol, unit.scale, ZEPTO * unit.magnitude) {}
fun <D : Dimension> yocto(unit: Unit<D>) = object : Unit<D>(unit.dimension, unit.symbol, unit.scale, YOCTO * unit.magnitude) {}
fun <D : Dimension> ronto(unit: Unit<D>) = object : Unit<D>(unit.dimension, unit.symbol, unit.scale, RONTO * unit.magnitude) {}
fun <D : Dimension> quecto(unit: Unit<D>) = object : Unit<D>(unit.dimension, unit.symbol, unit.scale, QUECTO * unit.magnitude) {}
// @formatter:on

