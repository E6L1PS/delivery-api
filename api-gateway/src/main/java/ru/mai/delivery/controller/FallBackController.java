package ru.mai.delivery.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Создан: 28.05.2024.
 *
 * @author Pesternikov Danil
 */
@RestController
@RequestMapping("/fallback")
public class FallBackController {

    @GetMapping(value = "/{segment}")
    public ResponseEntity<Object> fallback(@PathVariable String segment) {
        Map<String, String> mapResponse = new HashMap<>();
        mapResponse.put("status", "SERVICE " + StringUtils.upperCase(segment) + " IS UNAVAILABLE");
        return new ResponseEntity<>(mapResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
