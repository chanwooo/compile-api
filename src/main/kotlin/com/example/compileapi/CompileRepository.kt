package com.example.compileapi

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CompileRepository: JpaRepository<Code, Long> {

}
