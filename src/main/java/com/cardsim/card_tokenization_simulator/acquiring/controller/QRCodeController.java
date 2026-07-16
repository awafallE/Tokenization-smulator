package com.cardsim.card_tokenization_simulator.acquiring.controller;

import com.cardsim.card_tokenization_simulator.acquiring.dto.GenerateQRCodeRequest;
import com.cardsim.card_tokenization_simulator.acquiring.dto.GenerateQRCodeResponse;
import com.cardsim.card_tokenization_simulator.acquiring.service.QRCodeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/acquiring/qrcode")
public class QRCodeController {
    private final QRCodeService qRCodeService;

    public QRCodeController(QRCodeService qRCodeService) {
        this.qRCodeService = qRCodeService;
    }

    @PostMapping
    public ResponseEntity<GenerateQRCodeResponse> generateQRCode(@Valid @RequestBody GenerateQRCodeRequest generateQRCodeRequest) {
        GenerateQRCodeResponse generateQRCodeResponse = qRCodeService.generateQRCode(generateQRCodeRequest);
        return ResponseEntity.status(HttpStatus.OK).body(generateQRCodeResponse);
    }
}
