package com.github.pgutkowski.kgraphqlnba.player

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository

interface PlayerRepository : PagingAndSortingRepository<Player, Int>{
    fun findByNameContaining(name : String, pageable: Pageable) : Page<Player>
}