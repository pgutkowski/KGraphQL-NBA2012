package com.github.pgutkowski.kgraphqlnba.tenure

import com.github.pgutkowski.kgraphqlnba.CSVDataImporter
import com.github.pgutkowski.kgraphqlnba.player.PlayerRepository
import com.github.pgutkowski.kgraphqlnba.team.TeamRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
open class TenureImporter : CSVDataImporter() {

    @Value("classpath:\${app.file.playerTenures}")
    lateinit var tenuresResource: Resource

    @Autowired
    lateinit var tenureRepository: TenureRepository

    @Autowired
    lateinit var teamRepository: TeamRepository

    @Autowired
    lateinit var playerRepository: PlayerRepository

    override fun import() = importFromCsv(tenuresResource.inputStream) { row ->
        val player = playerRepository.findOne(row[0].toInt())
                ?: throw IllegalArgumentException("Cannot import tenure without valid player id")
        val team = teamRepository.findOne(row[4])
                ?: throw IllegalArgumentException("Cannot import tenure without valid team id")

        tenureRepository.save(Tenure(
                player = player,
                team = team,
                startDate = LocalDate.parse(row[8]),
                endDate = LocalDate.parse(row[9]),
                comment = row[7],
                position = row[6].toDouble()
        ))
    }
}