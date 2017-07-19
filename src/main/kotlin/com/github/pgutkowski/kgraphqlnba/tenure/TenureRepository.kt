package com.github.pgutkowski.kgraphqlnba.tenure

import com.github.pgutkowski.kgraphqlnba.player.Player
import org.springframework.data.repository.PagingAndSortingRepository


interface TenureRepository : PagingAndSortingRepository<Tenure, Int>{
    fun findTopByPlayerOrderByStartDate(player: Player): Tenure

    fun findByPlayer(player: Player): List<Tenure>
}