package com.github.pgutkowski.kgraphqlnba.game

import com.github.pgutkowski.kgraphqlnba.CSVDataImporter
import com.github.pgutkowski.kgraphqlnba.team.TeamRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
open class GameImporter : CSVDataImporter() {

    @Value("classpath:\${app.file.games}")
    lateinit var gamesResource: Resource

    @Autowired
    lateinit var gameRepository: GameRepository

    @Autowired
    lateinit var teamRepository: TeamRepository

    override fun import() {
        importFromCsv(gamesResource.inputStream) { row ->
            val homeTeam = teamRepository.findOne(row[7])
                    ?: throw IllegalArgumentException("Cannot import game without valid home team id")
            val awayTeam = teamRepository.findOne(row[9])
                    ?: throw IllegalArgumentException("Cannot import game without valid away team id")

            gameRepository.save(Game(
                    id = row[3],
                    date = LocalDate.parse(row[2]),
                    boxScoreUrl = row[4],
                    playByPlayUrl = row[5],
                    homeTeam = homeTeam,
                    awayTeam = awayTeam
            ))
        }
    }


}