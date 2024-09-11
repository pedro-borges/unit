@file:Suppress("unused")

package org.pcb.unit

import java.math.BigDecimal
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
                     val scale: BigDecimal)
{
    QUETTA("Q", TEN.power(30)),
    RONNA("R", TEN.power(27)),
    YOTTA("Y", TEN.power(24)),
    ZETTA("Z", TEN.power(21)),
    EXA("E", TEN.power(18)),
    PETA("P", TEN.power(15)),
    TERA("T", TEN.power(12)),
    GIGA("G", TEN.power(9)),
    MEGA("M", TEN.power(6)),
    KILO("k", TEN.power(3)),
    HECTO("h", TEN.power(2)),
    DECA("da", TEN.power(1)),
    UNI("", TEN.power(0)),
    DECI("d", TEN.power(-1)),
    CENTI("c", TEN.power(-2)),
    MILLI("m", TEN.power(-3)),
    MICRO("μ", TEN.power(-6)),
    NANO("n", TEN.power(-9)),
    PICO("p", TEN.power(-12)),
    FEMTO("f", TEN.power(-15)),
    ATTO("a", TEN.power(-18)),
    ZEPTO("z", TEN.power(-21)),
    YOCTO("y", TEN.power(-24)),
    RONTO("r", TEN.power(-27)),
    QUECTO("q", TEN.power(-30)),
}

// @formatter:off
fun <D : Dimension> quetta(unit: Unit<D>) = object : Unit<D>(unit.domain, QUETTA.symbol + unit.symbol, unit.scale * QUETTA.scale) {}
fun <D : Dimension> ronna(unit: Unit<D>) = object : Unit<D>(unit.domain, RONNA.symbol + unit.symbol, unit.scale * RONNA.scale) {}
fun <D : Dimension> yotta(unit: Unit<D>) = object : Unit<D>(unit.domain, YOTTA.symbol + unit.symbol, unit.scale * YOTTA.scale) {}
fun <D : Dimension> zetta(unit: Unit<D>) = object : Unit<D>(unit.domain, ZETTA.symbol + unit.symbol, unit.scale * ZETTA.scale) {}
fun <D : Dimension> exa(unit: Unit<D>) = object : Unit<D>(unit.domain, EXA.symbol + unit.symbol, unit.scale * EXA.scale) {}
fun <D : Dimension> peta(unit: Unit<D>) = object : Unit<D>(unit.domain, PETA.symbol + unit.symbol, unit.scale * PETA.scale) {}
fun <D : Dimension> tera(unit: Unit<D>) = object : Unit<D>(unit.domain, TERA.symbol + unit.symbol, unit.scale * TERA.scale) {}
fun <D : Dimension> giga(unit: Unit<D>) = object : Unit<D>(unit.domain, GIGA.symbol + unit.symbol, unit.scale * GIGA.scale) {}
fun <D : Dimension> mega(unit: Unit<D>) = object : Unit<D>(unit.domain, MEGA.symbol + unit.symbol, unit.scale * MEGA.scale) {}
fun <D : Dimension> kilo(unit: Unit<D>) = object : Unit<D>(unit.domain, KILO.symbol + unit.symbol, unit.scale * KILO.scale) {}
fun <D : Dimension> hecto(unit: Unit<D>) = object : Unit<D>(unit.domain, HECTO.symbol + unit.symbol, unit.scale * HECTO.scale) {}
fun <D : Dimension> deca(unit: Unit<D>) = object : Unit<D>(unit.domain, DECA.symbol + unit.symbol, unit.scale * DECA.scale) {}
fun <D : Dimension> uni(unit: Unit<D>) = object : Unit<D>(unit.domain, UNI.symbol + unit.symbol, unit.scale * UNI.scale) {}
fun <D : Dimension> deci(unit: Unit<D>) = object : Unit<D>(unit.domain, DECI.symbol + unit.symbol, unit.scale * DECI.scale) {}
fun <D : Dimension> centi(unit: Unit<D>) = object : Unit<D>(unit.domain, CENTI.symbol + unit.symbol, unit.scale * CENTI.scale) {}
fun <D : Dimension> milli(unit: Unit<D>) = object : Unit<D>(unit.domain, MILLI.symbol + unit.symbol, unit.scale * MILLI.scale) {}
fun <D : Dimension> micro(unit: Unit<D>) = object : Unit<D>(unit.domain, MICRO.symbol + unit.symbol, unit.scale * MICRO.scale) {}
fun <D : Dimension> nano(unit: Unit<D>) = object : Unit<D>(unit.domain, NANO.symbol + unit.symbol, unit.scale * NANO.scale) {}
fun <D : Dimension> pico(unit: Unit<D>) = object : Unit<D>(unit.domain, PICO.symbol + unit.symbol, unit.scale * PICO.scale) {}
fun <D : Dimension> femto(unit: Unit<D>) = object : Unit<D>(unit.domain, FEMTO.symbol + unit.symbol, unit.scale * FEMTO.scale) {}
fun <D : Dimension> atto(unit: Unit<D>) = object : Unit<D>(unit.domain, ATTO.symbol + unit.symbol, unit.scale * ATTO.scale) {}
fun <D : Dimension> zepto(unit: Unit<D>) = object : Unit<D>(unit.domain, ZEPTO.symbol + unit.symbol, unit.scale * ZEPTO.scale) {}
fun <D : Dimension> yocto(unit: Unit<D>) = object : Unit<D>(unit.domain, YOCTO.symbol + unit.symbol, unit.scale * YOCTO.scale) {}
fun <D : Dimension> ronto(unit: Unit<D>) = object : Unit<D>(unit.domain, RONTO.symbol + unit.symbol, unit.scale * RONTO.scale) {}
fun <D : Dimension> quecto(unit: Unit<D>) = object : Unit<D>(unit.domain, QUECTO.symbol + unit.symbol, unit.scale * QUECTO.scale) {}
// @formatter:on

