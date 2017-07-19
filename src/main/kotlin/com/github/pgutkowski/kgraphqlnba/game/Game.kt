package com.github.pgutkowski.kgraphqlnba.game

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.Id

import com.github.pgutkowski.kgraphqlnba.team.Team
import javax.persistence.ManyToOne

@Entity
data class Game (
        @Id
        var id: String? = "",

        var date : LocalDate? = null,

        var boxScoreUrl : String? = null,

        var playByPlayUrl : String? = null,

        @ManyToOne
        var homeTeam: Team? = null,

        @ManyToOne
        var awayTeam: Team? = null
)