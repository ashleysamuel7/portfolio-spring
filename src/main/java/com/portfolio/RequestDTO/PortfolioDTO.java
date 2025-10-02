package com.portfolio.RequestDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class PortfolioDTO {
  private Integer portfolioId;
  private Integer userId;
  private String symbolId;
  private Integer qty;
  private BigDecimal buyPrice;
  private BigDecimal sellPrice;
  private LocalDateTime buyDate;
  private LocalDateTime sellDate;
}
