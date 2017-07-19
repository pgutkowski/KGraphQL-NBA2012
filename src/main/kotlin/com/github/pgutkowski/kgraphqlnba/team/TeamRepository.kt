package com.github.pgutkowski.kgraphqlnba.team

import org.springframework.data.repository.PagingAndSortingRepository


interface TeamRepository : PagingAndSortingRepository<Team, String>