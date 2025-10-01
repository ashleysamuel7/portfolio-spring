package com.portfolio.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "symbols")
public class Symbol {

    @Id
    @Column(name = "symbol_id", length = 20)
    private String symbolId;

    @Column(name = "symbol_name", nullable = false)
    private String symbolName;

    @OneToMany(mappedBy = "symbol", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Portfolio> portfolios;

}
