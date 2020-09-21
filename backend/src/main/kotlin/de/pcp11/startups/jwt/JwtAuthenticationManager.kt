package de.pcp11.startups.jwt

import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class JwtAuthenticationManager(private val jwtUtil: JwtUtil) : ReactiveAuthenticationManager {

    override fun authenticate(authentication: Authentication): Mono<Authentication> {
        return Mono.just(authentication)
                .map { jwtUtil.validateJwt(it.credentials as String) }
                .onErrorResume { Mono.empty() }
                .map { jws ->
                    UsernamePasswordAuthenticationToken(
                            jws.body.subject,
                            authentication.credentials as String,
                            mutableListOf(SimpleGrantedAuthority("ROLE_USER")))
                }
    }
}