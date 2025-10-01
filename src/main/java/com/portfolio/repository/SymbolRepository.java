package com.portfolio.repository;


import com.portfolio.entity.Portfolio;
import com.portfolio.entity.Symbol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SymbolRepository extends JpaRepository<Symbol, Integer> {
}
