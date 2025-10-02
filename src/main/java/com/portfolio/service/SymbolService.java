package com.portfolio.service;

import com.portfolio.RequestDTO.SymbolDTO;
import com.portfolio.entity.Symbol;
import com.portfolio.exception.ResourceNotFoundException;
import com.portfolio.repository.SymbolRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SymbolService {

    private final SymbolRepository symbolRepository;

    public SymbolService(SymbolRepository repo) {
        this.symbolRepository = repo;
    }

    // Mapper helpers (simple)
    private SymbolDTO toDto(Symbol s) {
        return new SymbolDTO(s.getSymbolId(), s.getSymbolName());
    }

    private Symbol toEntity(SymbolDTO dto) {
        return new Symbol(dto.getSymbolId(), dto.getSymbolName());
    }

    public List<SymbolDTO> findAll() {
        return symbolRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public SymbolDTO findById(String id) {
        Symbol s = symbolRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Symbol not found: " + id));
        return toDto(s);
    }

    public SymbolDTO create(SymbolDTO dto) {
        if (dto.getSymbolId() == null || dto.getSymbolId().isBlank()) {
            throw new IllegalArgumentException("symbolId must not be null/blank");
        }
        // Optionally check existence to avoid overwrite:
        if (symbolRepository.existsById(dto.getSymbolId())) {
            throw new IllegalArgumentException("Symbol already exists with id: " + dto.getSymbolId());
        }
        Symbol saved = symbolRepository.save(toEntity(dto));
        return toDto(saved);
    }

    public SymbolDTO update(String id, SymbolDTO dto) {
        Symbol existing = symbolRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Symbol not found: " + id));
        // update allowed fields (do not update ID in this implementation)
        if (dto.getSymbolName() != null) existing.setSymbolName(dto.getSymbolName());
        Symbol saved = symbolRepository.save(existing);
        return toDto(saved);
    }

    public void delete(String id) {
        Symbol existing = symbolRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Symbol not found: " + id));
        symbolRepository.delete(existing);
    }
}
