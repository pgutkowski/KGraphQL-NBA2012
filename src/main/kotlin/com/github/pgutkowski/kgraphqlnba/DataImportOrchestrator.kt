package com.github.pgutkowski.kgraphqlnba

import com.github.pgutkowski.kgraphqlnba.game.GameImporter
import com.github.pgutkowski.kgraphqlnba.player.PlayerImporter
import com.github.pgutkowski.kgraphqlnba.team.TeamImporter
import com.github.pgutkowski.kgraphqlnba.tenure.TenureImporter
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct
import kotlin.system.measureTimeMillis

@Component
open class DataImportOrchestrator(
        val gameImporter: GameImporter,
        val playerImporter: PlayerImporter,
        val teamImporter: TeamImporter,
        val tenureImporter: TenureImporter
) {

    private val log = LoggerFactory.getLogger(this.javaClass)

    @PostConstruct
    fun importData(){

        val timeImportTeams = measureTimeMillis {
            teamImporter.import()
        }
        log.info("Teams imported in $timeImportTeams ms")

        val timeImportPlayers = measureTimeMillis {
            playerImporter.import()
        }
        log.info("Players imported in $timeImportPlayers ms")

        val timeImportTenures = measureTimeMillis {
            tenureImporter.import()
        }
        log.info("Tenures imported in $timeImportTenures ms")

        val timeImportGames = measureTimeMillis {
            gameImporter.import()
        }
        log.info("Games imported in $timeImportGames ms")
    }

}