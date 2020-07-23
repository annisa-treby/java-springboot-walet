package com.enigma.spotify.controller;

import com.enigma.spotify.entity.History;
import com.enigma.spotify.entity.Profile;
import com.enigma.spotify.services.HistoryService;
import com.enigma.spotify.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    HistoryService historyService;

    @PostMapping
    public History saveHistory(@RequestBody History history) {
        return historyService.saveHistory(history);
    }

    @PostMapping("/update")
    public History updateHistory(@RequestBody History history) {
        return historyService.saveHistory(history);
    }

    @GetMapping("/histories")
    public Page<History> getAllHistory(@RequestParam Integer page, @RequestParam Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return historyService.getAllHistory(pageable);
    }

    @GetMapping
    public Page<History> searchHistory(@RequestBody History searchForm, @RequestParam Integer page, @RequestParam Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return historyService.searchHistory(searchForm, pageable);
    }

    @DeleteMapping
    public void deleteHistory(@RequestBody History history) {
        historyService.deleteHistory(history.getId());
    }

    @GetMapping("{/id}")
    public History getHistoryById(@PathVariable String id) {
        return historyService.getHistory(id);
    }
}
