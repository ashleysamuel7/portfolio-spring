package com.portfolio.RequestDTO;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class PortfolioDTO {

    private int portfolioId;

    private int userId;

    private String symbolId;

    private int qty;

    private BigDecimal buyPrice;

    private BigDecimal sellPrice;

    private LocalDateTime buyDate;

    private LocalDateTime sellDate;

}
