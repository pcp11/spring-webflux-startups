package de.pcp11.startups.repository

import de.pcp11.startups.model.Startup
import org.springframework.data.repository.reactive.ReactiveSortingRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux


@Repository
interface StartupRepository : ReactiveSortingRepository<Startup, Long> {

    fun findByOrderByTotalFundsDesc(): Flux<Startup>
}