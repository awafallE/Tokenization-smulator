package com.cardsim.card_tokenization_simulator.acquiring.service;

import com.cardsim.card_tokenization_simulator.acquiring.dto.*;
import com.cardsim.card_tokenization_simulator.acquiring.model.*;
import com.cardsim.card_tokenization_simulator.acquiring.repository.MerchantContractRepository;
import com.cardsim.card_tokenization_simulator.acquiring.repository.MerchantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MerchantContractService {

    // inject MerchantRepository and MerchantContractRepository
private final MerchantContractRepository merchantContractRepository;
private final MerchantRepository merchantRepository;
public MerchantContractService(MerchantContractRepository merchantContractRepository,
                               MerchantRepository merchantRepository) {
    this.merchantContractRepository = merchantContractRepository;
    this.merchantRepository = merchantRepository;
}
    @Transactional
    public MerchantContractResponse createContract(
            String merchantId,
            CreateContractRequest request) {

        // Step 1: Find merchant — throw if not found
          Optional<Merchant> merchant=merchantRepository.findByMerchantId(merchantId);
          merchant.orElseThrow(()-> new IllegalArgumentException("merchant not found"));
        // Step 2: Validate merchant is not CLOSED
        if( merchant.get().getStatus().equals(Status.CLOSED)) {
            throw new IllegalArgumentException("Cannot create contract with closed merchant.");
        }
        // Step 3: Check contractReference not already exists
         merchantContractRepository.findByContractReference(request.getContractReference()).ifPresent(merchantContract -> { throw new IllegalArgumentException("contract reference already exist"+request.getContractReference());});


        // Step 4: Create MerchantContract entity
        MerchantContract merchantContract=new MerchantContract();
        merchantContract.setContractReference(request.getContractReference());
        merchantContract.setContractEndDate(request.getContractEndDate());
        merchantContract.setContractStartDate(request.getContractStartDate());
        merchantContract.setCreatedAt(LocalDateTime.now());
        merchantContract.setStatus(Status.PENDING);
        merchantContract.setProductCode(request.getProductCode());
        merchantContract.setAcceptedNetworks(request.getAcceptedNetworks());
        merchantContract.setMerchant(merchant.get());//to review
        // status = PENDING
        // createdAt = now

        // Step 5: Map services
        // for each service dto → ContractService → set contract → add to list
        List<ContractService> contractServices=new ArrayList<>();
        for(ContractServiceDto contractService:request.getServices()){
            ContractService service=new ContractService();
            service.setServiceName(contractService.getServiceName());
            service.setEnabled(contractService.getEnabled());
            service.setMerchantContract(merchantContract); // missing — add this

            contractServices.add(service);


        }
        merchantContract.setContractServices(contractServices);


        // Step 6: Map billable elements
        // for each billable dto → BillableElement → set contract
        //   for each charging condition dto → ChargingCondition → set billable
        List<BillableElement> billableElements=new ArrayList<>();
       for (BillableElementDto billableElementDto : request.getBillableElements()) {
           BillableElement billableElement=new BillableElement();
           billableElement.setPayableEvent(billableElementDto.getPayableEvent());

           List<ChargingCondition> chargingConditions = new ArrayList<>();

           for(ChargingConditionDto chargingConditionDto:billableElementDto.getChargingConditions()){
               ChargingCondition chargingCondition=new ChargingCondition();
               chargingCondition.setRate(chargingConditionDto.getRate());
               chargingCondition.setMaxAmount(chargingConditionDto.getMaxAmount());
               chargingCondition.setMinAmount(chargingConditionDto.getMinAmount());
               chargingCondition.setConditionName(chargingConditionDto.getConditionName());
               chargingCondition.setBillableElement(billableElement);
               chargingConditions.add(chargingCondition);
           }
           billableElement.setChargingCondition(chargingConditions);
           billableElement.setMerchantContract(merchantContract);
           billableElements.add(
                   billableElement
           );
       }
        merchantContract.setBillableElement(billableElements);

        // Step 7: Map settlement conditions
        List<ContractSettlementCondition> newConditions = new ArrayList<>();
        for (ContractSettlementConditionDto conditionDto : request.getSettlementConditions()) {
            ContractSettlementCondition newCondition = new ContractSettlementCondition();
            newCondition.setSettlementFrequency(conditionDto.getSettlementFrequency());
            newCondition.setAccountNumber(conditionDto.getAccountNumber());
            newCondition.setMerchantContract(merchantContract);
            newConditions.add(newCondition);
        }
        merchantContract.setContractSettlementCondition(newConditions);

        // Step 8: Save contract (cascade saves everything)
       MerchantContract saved= merchantContractRepository.save(merchantContract);
        // Step 9: Return response
        return new MerchantContractResponse(
                saved.getContractReference(),
                merchantId,
                saved.getProductCode(),
                saved.getStatus().name(),
                saved.getContractStartDate(),
                saved.getAcceptedNetworks(),
                saved.getCreatedAt(),
                "Merchant created successfully - pending validation"
        );    }

    @Transactional
    public MerchantContractResponse activateContract(String contractReference) {
        MerchantContract contract = merchantContractRepository
                .findByContractReference(contractReference)
                .orElseThrow(() -> new IllegalArgumentException("Contract not found: " + contractReference));

        if (!contract.getStatus().equals(Status.PENDING)) {
            throw new IllegalStateException("Contract can only be activated from PENDING status");
        }
        contract.setStatus(Status.ACTIVE);
        MerchantContract saved = merchantContractRepository.save(contract);
        return buildResponse(saved, "Contract activated successfully");
    }

    @Transactional
    public MerchantContractResponse suspendContract(String contractReference) {
        MerchantContract contract = merchantContractRepository
                .findByContractReference(contractReference)
                .orElseThrow(() -> new IllegalArgumentException("Contract not found: " + contractReference));

        if (!contract.getStatus().equals(Status.ACTIVE)) {
            throw new IllegalStateException("Contract can only be suspended from ACTIVE status");
        }
        contract.setStatus(Status.SUSPENDED);
        MerchantContract saved = merchantContractRepository.save(contract);
        return buildResponse(saved, "Contract suspended successfully");
    }

    @Transactional
    public MerchantContractResponse unsuspendContract(String contractReference) {
        MerchantContract contract = merchantContractRepository
                .findByContractReference(contractReference)
                .orElseThrow(() -> new IllegalArgumentException("Contract not found: " + contractReference));

        if (!contract.getStatus().equals(Status.SUSPENDED)) {
            throw new IllegalStateException("Contract can only be unsuspended from SUSPENDED status");
        }
        contract.setStatus(Status.ACTIVE);
        MerchantContract saved = merchantContractRepository.save(contract);
        return buildResponse(saved, "Contract unsuspended successfully");
    }

    @Transactional
    public MerchantContractResponse closeContract(String contractReference) {
        MerchantContract contract = merchantContractRepository
                .findByContractReference(contractReference)
                .orElseThrow(() -> new IllegalArgumentException("Contract not found: " + contractReference));

        if (contract.getStatus().equals(Status.CLOSED)) {
            throw new IllegalStateException("Contract is already closed");
        }
        contract.setStatus(Status.CLOSED);
        MerchantContract saved = merchantContractRepository.save(contract);
        return buildResponse(saved, "Contract closed successfully");
    }

    private MerchantContractResponse buildResponse(MerchantContract contract, String message) {
        return new MerchantContractResponse(
                contract.getContractReference(),
                contract.getMerchant().getMerchantId(),
                contract.getProductCode(),
                contract.getStatus().name(),
                contract.getContractStartDate(),
                contract.getAcceptedNetworks(),
                contract.getCreatedAt(),
                message
        );
    }
}