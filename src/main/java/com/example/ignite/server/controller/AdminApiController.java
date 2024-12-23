package com.example.ignite.server.controller;

import com.example.ignite.server.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller to handle Cache Admin  requests to manage issue related to Cache
 * like CorruptedTreeException or refreshing data.
 * Usage of this APIs should be restricted to Admin person only.
 *
 */
@RestController
@RequestMapping("/api/admin")
public class AdminApiController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/cache/destroy")
    public ResponseEntity<String> destroyCache(@RequestParam String cache) {
        adminService.destroyCache(cache);
        return ResponseEntity.ok(cache);
    }

    @GetMapping("/cache/clear")
    public ResponseEntity<String> clearCache(@RequestParam String cache) {
        adminService.clearCache(cache);
        return ResponseEntity.ok(cache);
    }
}
