package com.github.pgutkowski.kgraphqlnba.player

import org.springframework.data.repository.PagingAndSortingRepository

interface PlayerRepository : PagingAndSortingRepository<Player, Int>