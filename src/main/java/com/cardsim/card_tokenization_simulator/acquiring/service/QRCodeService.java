package com.cardsim.card_tokenization_simulator.acquiring.service;

import com.cardsim.card_tokenization_simulator.acquiring.dto.GenerateQRCodeRequest;
import com.cardsim.card_tokenization_simulator.acquiring.dto.GenerateQRCodeResponse;
import com.cardsim.card_tokenization_simulator.acquiring.model.AcceptorPoint;
import com.cardsim.card_tokenization_simulator.acquiring.model.Merchant;
import com.cardsim.card_tokenization_simulator.acquiring.model.MerchantSite;
import com.cardsim.card_tokenization_simulator.acquiring.model.Status;
import com.cardsim.card_tokenization_simulator.acquiring.repository.AcceptorPointRepository;
import com.cardsim.card_tokenization_simulator.acquiring.repository.MerchantActivityRepository;
import com.cardsim.card_tokenization_simulator.acquiring.repository.MerchantContractRepository;
import com.cardsim.card_tokenization_simulator.acquiring.repository.MerchantRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QRCodeService {

    private final MerchantRepository merchantRepository;
    private final AcceptorPointRepository acceptorPointRepository;
    private final MerchantContractRepository merchantContractRepository;
    private final MerchantActivityRepository merchantActivityRepository;

    // constructor inject all four
    public QRCodeService(MerchantRepository merchantRepository,AcceptorPointRepository acceptorPointRepository,MerchantContractRepository merchantContractRepository,MerchantActivityRepository merchantActivityRepository) {
        this.merchantRepository = merchantRepository;
        this.acceptorPointRepository = acceptorPointRepository;
        this.merchantContractRepository = merchantContractRepository;
        this.merchantActivityRepository = merchantActivityRepository;
    }

    public GenerateQRCodeResponse generateQRCode(GenerateQRCodeRequest request) {

        Merchant merchant = merchantRepository
                .findByMerchantId(request.getMerchantId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Merchant not found: " + request.getMerchantId()));

        if (merchant.getStatus().equals(Status.CLOSED)) {
            throw new IllegalArgumentException(
                    "Cannot generate QR for closed merchant");
        }

        AcceptorPoint acceptorPoint=acceptorPointRepository.findByAcceptorPointId(request.getAcceptorPointId()).orElseThrow(()-> new IllegalArgumentException("acceptor point not found"+request.getAcceptorPointId()));

        boolean belongsToMerchant = merchant.getMerchantSites().stream()
                .flatMap(site -> site.getAcceptor().stream())
                .anyMatch(ap -> ap.getAcceptorPointId().equals(request.getAcceptorPointId()));

        if (!belongsToMerchant) {
            throw new IllegalArgumentException("acceptor point does not belong to merchant");
        }

        boolean contractCheck= merchant.getMerchantContracts().stream().anyMatch(c->c.getStatus().equals(Status.ACTIVE));
        if (!contractCheck) {
            throw new IllegalArgumentException("At least one contract must be active");
        }

        // Step 4: Get MCC from merchant's primary activity
         String mcc=merchant.getActivities().stream().filter(a->"P".equals(a.getActivityType())).findFirst().orElseThrow(()->new  IllegalArgumentException("don't have a primary activity")).getMcc();


        // Step 5: Determine QR type
        // STATIC if amount is null, DYNAMIC if amount provided
        String qrType = request.getAmount() != null ? "DYNAMIC" : "STATIC";
        // Step 6: Generate EMVCo QR string
        // call buildEMVCoQRString(merchant, acceptorPoint, request, mcc)
        String qrValue = buildEMVCoQRString(merchant, acceptorPoint, request, mcc);

        GenerateQRCodeResponse response = new GenerateQRCodeResponse();
        response.setMerchantId(merchant.getMerchantId());
        response.setAcceptorPointId(acceptorPoint.getAcceptorPointId());
        response.setQrType(qrType);
        response.setCurrency(request.getCurrency());
        response.setAmount(request.getAmount());
        response.setQrValue(qrValue);
        response.setGeneratedAt(LocalDateTime.now());
        return response;

    }

    private String buildEMVCoQRString(
            Merchant merchant,
            AcceptorPoint acceptorPoint,
            GenerateQRCodeRequest request,
            String mcc) {

        String qrType = request.getAmount() != null ? "12" : "11";
        // 11 = static, 12 = dynamic

        StringBuilder qr = new StringBuilder();

        // Field 00: Payload Format Indicator
        qr.append(emvField("00", "01"));

        // Field 01: Point of Initiation
        qr.append(emvField("01", qrType));

        // Field 26: Merchant Account Information
        String merchantInfo = emvField("00", "com.powercard.simulator")
                + emvField("01", merchant.getMerchantId());
        qr.append(emvField("26", merchantInfo));

        // Field 52: Merchant Category Code
        qr.append(emvField("52", mcc));

        // Field 53: Transaction Currency (ISO 4217 numeric)
        qr.append(emvField("53", getCurrencyNumeric(request.getCurrency())));

        // Field 54: Transaction Amount (only for dynamic QR)
        if (request.getAmount() != null) {
            qr.append(emvField("54", request.getAmount().toPlainString()));
        }

        // Field 58: Country Code
        qr.append(emvField("58", merchant.getCountryCode()));

        // Field 59: Merchant Name
        qr.append(emvField("59", merchant.getCompanyName()));

        // Field 60: Merchant City
        qr.append(emvField("60", "CASABLANCA")); // simplified

        // Field 62: Additional Data (acceptor point reference)
        String additionalData = emvField("05", acceptorPoint.getAcceptorPointId());
        qr.append(emvField("62", additionalData));

        // Field 63: CRC checksum (4 hex chars)
        String qrWithoutCRC = qr.toString() + "6304";
        String crc = calculateCRC16(qrWithoutCRC);
        qr.append(emvField("63", crc));

        return qr.toString();
    }

    private String emvField(String id, String value) {
        String length = String.format("%02d", value.length());
        return id + length + value;
    }

    private String getCurrencyNumeric(String currencyCode) {
        return switch (currencyCode.toUpperCase()) {
            case "MAD" -> "504";
            case "EUR" -> "978";
            case "USD" -> "840";
            case "GBP" -> "826";
            default -> "999";
        };
    }

    private String calculateCRC16(String data) {
        int crc = 0xFFFF;
        for (char c : data.toCharArray()) {
            crc ^= (c << 8);
            for (int i = 0; i < 8; i++) {
                if ((crc & 0x8000) != 0) {
                    crc = (crc << 1) ^ 0x1021;
                } else {
                    crc <<= 1;
                }
                crc &= 0xFFFF;
            }
        }
        return String.format("%04X", crc);
    }
}