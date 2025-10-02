package com.portfolio.controller;

import com.portfolio.RequestDTO.SymbolDTO;
import com.portfolio.service.SymbolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/symbols")
@RequiredArgsConstructor
public class SymbolController {

    private  SymbolService service;


    @GetMapping("/all")
    public ResponseEntity<List<SymbolDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SymbolDTO> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<SymbolDTO> create(@RequestBody SymbolDTO dto) {
        SymbolDTO created = service.create(dto);
        // location header to the created resource
        return ResponseEntity.created(URI.create("/api/symbols/" + created.getSymbolId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SymbolDTO> update(@PathVariable("id") String id, @RequestBody SymbolDTO dto) {
        SymbolDTO updated = service.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
