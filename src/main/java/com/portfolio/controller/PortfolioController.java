package com.portfolio.controller;

import com.portfolio.RequestDTO.PortfolioDTO;
import com.portfolio.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/portfolios")
@RequiredArgsConstructor
public class PortfolioController {

    private final PortfolioService service;

    @GetMapping
    public ResponseEntity<List<PortfolioDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PortfolioDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<PortfolioDTO> create(@RequestBody PortfolioDTO dto) {
        PortfolioDTO created = service.create(dto);
        return ResponseEntity.created(URI.create("/api/portfolios/" + created.getPortfolioId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PortfolioDTO> update(@PathVariable Integer id, @RequestBody PortfolioDTO dto) {
        PortfolioDTO updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
