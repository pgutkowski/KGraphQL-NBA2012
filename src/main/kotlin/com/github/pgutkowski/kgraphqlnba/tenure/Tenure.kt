package com.github.pgutkowski.kgraphqlnba.tenure

import com.github.pgutkowski.kgraphqlnba.player.Player
import com.github.pgutkowski.kgraphqlnba.team.Team
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class Tenure (
        @Id @GeneratedValue
        var id: Int = 0,

        @ManyToOne
        var player: Player? = null,

        @ManyToOne
        var team: Team? = null,

        var startDate: LocalDate? = null,
        var endDate: LocalDate? = null,

        var comment: String? = null,

        var position: Double = 0.0
)