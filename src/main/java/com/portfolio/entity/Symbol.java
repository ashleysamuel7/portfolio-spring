package com.portfolio.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "symbols")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Symbol {

    @Id
    @Column(name = "symbol_id", length = 20)
    private String symbolId;

    @Column(name = "symbol_name", nullable = false)
    private String symbolName;

    @OneToMany(mappedBy = "symbol", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Portfolio> portfolios;

    public Symbol(String symbolId, String symbolName) {
        this.symbolId=symbolId;
        this.symbolName=symbolName;
    }
}
