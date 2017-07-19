package com.github.pgutkowski.kgraphqlnba.game

import org.springframework.data.repository.PagingAndSortingRepository


interface GameRepository : PagingAndSortingRepository<Game, String>