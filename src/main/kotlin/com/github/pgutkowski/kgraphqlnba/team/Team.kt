package com.github.pgutkowski.kgraphqlnba.team

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Team (
        @Id var id: String = "",
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
)