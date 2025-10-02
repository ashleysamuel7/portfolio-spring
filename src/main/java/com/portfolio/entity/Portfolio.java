package com.portfolio.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "portfolio")
@Getter
@Setter
public class Portfolio {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "portfolio_id")
  private Integer portfolioId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "symbol_id", nullable = false)
  private Symbol symbol;

  private Integer qty;

  @Column(name = "buy_price", precision = 10, scale = 2)
  private BigDecimal buyPrice;

  @Column(name = "sell_price", precision = 10, scale = 2)
  private BigDecimal sellPrice;

  @Column(name = "buy_date")
  private LocalDateTime buyDate;

  @Column(name = "sell_date")
  private LocalDateTime sellDate;

  // Getters and setters
}
