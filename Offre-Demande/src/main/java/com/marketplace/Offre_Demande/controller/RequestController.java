package com.marketplace.Offre_Demande.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marketplace.Offre_Demande.service.RequestServiceImplement;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/request")
@RequiredArgsConstructor
public class RequestController {
    private final RequestServiceImplement requestServiceImplement;

    
}
