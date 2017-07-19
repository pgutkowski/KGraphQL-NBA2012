package com.github.pgutkowski.kgraphqlnba.team

import com.github.pgutkowski.kgraphqlnba.CSVDataImporter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component

@Component
open class TeamImporter : CSVDataImporter() {

    @Value("classpath:\${app.file.teams}")
    lateinit var teamsResource: Resource

    @Autowired
    lateinit var teamRepository: TeamRepository

    override fun import() = importFromCsv(teamsResource.inputStream) { row ->
        teamRepository.save(Team(
                id = row[0],
                minutes = row[1].toDouble(),
                possessionsFor = row[2].toInt(),
                possessionsOpp = row[3].toInt(),
                pointsFor = row[4].toInt(),
                pointsOpp = row[5].toInt(),
                offRtg = row[6].toDouble(),
                defRtg = row[7].toDouble(),
                overallRtg = row[8].toDouble(),
                oRebFor = row[9].toInt(),
                oRebOpp = row[10].toInt(),
                dRebFor = row[11].toInt(),
                dRebOpp = row[12].toInt(),
                oRebRate = row[13].toDouble(),
                dRebRate = row[14].toDouble()
        ))
    }
}