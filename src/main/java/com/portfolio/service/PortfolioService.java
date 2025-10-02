package com.portfolio.service;

import com.portfolio.RequestDTO.PortfolioDTO;
import com.portfolio.entity.Portfolio;
import com.portfolio.entity.Symbol;
import com.portfolio.entity.User;
import com.portfolio.exception.ResourceNotFoundException;
import com.portfolio.repository.PortfolioRepository;
import com.portfolio.repository.SymbolRepository;
import com.portfolio.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PortfolioService {

  private final PortfolioRepository portfolioRepo;
  private final UserRepository userRepo;
  private final SymbolRepository symbolRepo;

  public PortfolioService(
      PortfolioRepository portfolioRepo, UserRepository userRepo, SymbolRepository symbolRepo) {
    this.portfolioRepo = portfolioRepo;
    this.userRepo = userRepo;
    this.symbolRepo = symbolRepo;
  }

  // Mapper: entity -> dto
  private PortfolioDTO toDto(Portfolio p) {
    PortfolioDTO dto = new PortfolioDTO();
    dto.setPortfolioId(p.getPortfolioId());
    dto.setUserId(p.getUser() != null ? p.getUser().getUserId() : null); // adjust getUserId name
    dto.setSymbolId(p.getSymbol() != null ? p.getSymbol().getSymbolId() : null);
    dto.setQty(p.getQty());
    dto.setBuyPrice(p.getBuyPrice());
    dto.setSellPrice(p.getSellPrice());
    dto.setBuyDate(p.getBuyDate());
    dto.setSellDate(p.getSellDate());
    return dto;
  }

  // Mapper: dto -> entity (for create)
  private Portfolio toEntity(PortfolioDTO dto) {
    Portfolio p = new Portfolio();
    p.setQty(dto.getQty());
    p.setBuyPrice(dto.getBuyPrice());
    p.setSellPrice(dto.getSellPrice());
    p.setBuyDate(dto.getBuyDate());
    p.setSellDate(dto.getSellDate());
    return p;
  }

  public List<PortfolioDTO> findAll() {
    List<Portfolio> allPortfolio = portfolioRepo.findAll();
    if (allPortfolio.isEmpty()) {
      throw new ResourceNotFoundException("Portfolio not found");
    }
    return portfolioRepo.findAll().stream().map(this::toDto).collect(Collectors.toList());
  }

  public PortfolioDTO findById(Integer id) {
    Portfolio p =
        portfolioRepo
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Portfolio not found: " + id));
    return toDto(p);
  }

  public PortfolioDTO create(PortfolioDTO dto) {
    if (dto.getUserId() == null) throw new IllegalArgumentException("userId is required");
    if (dto.getSymbolId() == null || dto.getSymbolId().isBlank())
      throw new IllegalArgumentException("symbolId is required");

    User user =
        userRepo
            .findById(dto.getUserId())
            .orElseThrow(() -> new ResourceNotFoundException("User not found: " + dto.getUserId()));

    Symbol symbol =
        symbolRepo
            .findById(dto.getSymbolId())
            .orElseThrow(
                () -> new ResourceNotFoundException("Symbol not found: " + dto.getSymbolId()));

    Portfolio p = toEntity(dto);
    p.setUser(user);
    p.setSymbol(symbol);

    Portfolio saved = portfolioRepo.save(p);
    return toDto(saved);
  }

  public PortfolioDTO update(Integer id, PortfolioDTO dto) {
    Portfolio existing =
        portfolioRepo
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Portfolio not found: " + id));

    // optionally update relations if IDs provided
    if (dto.getUserId() != null && !dto.getUserId().equals(existing.getUser().getUserId())) {
      User user =
          userRepo
              .findById(dto.getUserId())
              .orElseThrow(
                  () -> new ResourceNotFoundException("User not found: " + dto.getUserId()));
      existing.setUser(user);
    }

    if (dto.getSymbolId() != null
        && !dto.getSymbolId().equals(existing.getSymbol().getSymbolId())) {
      Symbol symbol =
          symbolRepo
              .findById(dto.getSymbolId())
              .orElseThrow(
                  () -> new ResourceNotFoundException("Symbol not found: " + dto.getSymbolId()));
      existing.setSymbol(symbol);
    }

    // update simple fields
    if (dto.getQty() != null) existing.setQty(dto.getQty());
    if (dto.getBuyPrice() != null) existing.setBuyPrice(dto.getBuyPrice());
    if (dto.getSellPrice() != null) existing.setSellPrice(dto.getSellPrice());
    if (dto.getBuyDate() != null) existing.setBuyDate(dto.getBuyDate());
    if (dto.getSellDate() != null) existing.setSellDate(dto.getSellDate());

    Portfolio saved = portfolioRepo.save(existing);
    return toDto(saved);
  }

  public void delete(Integer id) {
    Portfolio existing =
        portfolioRepo
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Portfolio not found: " + id));
    portfolioRepo.delete(existing);
  }
}
