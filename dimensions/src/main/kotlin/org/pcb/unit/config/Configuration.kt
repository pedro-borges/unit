package org.pcb.unit.config

import java.math.MathContext
import java.math.RoundingMode.HALF_EVEN

object Configuration
{
    /**
     * Precision used for internal calculations
     */
    var calculationMathContext = MathContext(68, HALF_EVEN)

    /**
     * precision used for Amount equals/hashCode and rendering
     */
    var displayMathContext = MathContext(34, HALF_EVEN)

    /**
     * Rendering of amount.toString()
     */
    var renderer: Renderer = PlainRenderer()
}