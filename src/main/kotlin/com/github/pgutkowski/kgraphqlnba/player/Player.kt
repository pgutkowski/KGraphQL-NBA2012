package com.github.pgutkowski.kgraphqlnba.player

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Player (
        @Id
        var id: Int = 0,

        var name: String? = null,

        var minutes: Double = 0.0,

        var possessionsFor: Int = 0,

        var possessionsOpp: Int = 0,

        var pointsFor: Int = 0,

        var pointsOpp: Int = 0,

        var offRtg: Double = 0.0,

        var defRtg: Double = 0.0,

        var overallRtg: Double = 0.0,

        var oRebFor: Int = 0,

        var oRebOpp: Int = 0,

        var dRebFor: Int = 0,

        var dRebOpp: Int = 0,

        var oRebRate: Double = 0.0,

        var dRebRate: Double = 0.0
//        var weightedMinOffCourt: Double = 0.0,
//        var weightedPossForOffCourt: Double = 0.0,
//        var weightedPossOppOffCourt: Double = 0.0,
//        var weightedPointsForOffCourt: Double = 0.0,
//        var weightedPointsOppOffCourt: Double = 0.0,
//        var weightedOffRtgOffCourt: Double = 0.0,
//        var weightedDefRtgOffCourt: Double = 0.0,
//        var weightedOverallRtgOffCourt: Double = 0.0,
//        var weightedORebForOffCourt: Double = 0.0,
//        var weightedORebOppOffCourt: Double = 0.0,
//        var weightedDRebForOffCourt: Double = 0.0,
//        var weightedDRebOppOffCourt: Double = 0.0,
//        var weightedORebRateOffCourt: Double = 0.0,
//        var weightedDRebRateOffCourt: Double = 0.0,
//        var weightedOverallRtgOnCourt: Double = 0.0,
//        var WeightedORebRateOnCourt: Double = 0.0,
//        var WeightedDRebRateOnCourt: Double = 0.0,
//        var WeightedOverallRtgOnCourtMinusOffCourt: Double = 0.0,
//        var WeightedORebRateOnCourtMinusOffCourt: Double = 0.0,
//        var WeightedDRebRateOnCourtMinusOffCourt: Double = 0.0,
//        var AdjustedPM: Double = 0.0,
//        var AdjustedPMStdErr: Double = 0.0
)