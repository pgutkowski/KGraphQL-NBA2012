package com.github.pgutkowski.kgraphqlnba.player

import com.github.pgutkowski.kgraphqlnba.CSVDataImporter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component

@Component
open class PlayerImporter : CSVDataImporter() {

    @Value("classpath:\${app.file.players}")
    lateinit var playersResource: Resource

    @Autowired
    lateinit var playerRepository: PlayerRepository

    override fun import() = importFromCsv(playersResource.inputStream) { row ->
        playerRepository.save(Player(
                id = row[0].toInt(),
                name = row[1],
                minutes = row[3].toDoubleOrZero(),
                possessionsFor = row[4].toIntOrZero(),
                possessionsOpp = row[5].toIntOrZero(),
                pointsFor = row[6].toIntOrZero(),
                pointsOpp = row[7].toIntOrZero(),
                offRtg = row[8].toDoubleOrZero(),
                defRtg = row[9].toDoubleOrZero(),
                overallRtg = row[10].toDoubleOrZero(),
                oRebFor = row[11].toIntOrZero(),
                oRebOpp = row[12].toIntOrZero(),
                dRebFor = row[13].toIntOrZero(),
                dRebOpp = row[14].toIntOrZero(),
                oRebRate = row[15].toDoubleOrZero(),
                dRebRate = row[16].toDoubleOrZero()
        ))
    }
}