package com.marketplace.Offre_Demande.service;

import java.util.List;

import com.marketplace.Offre_Demande.dto.RequestDtoRequest;
import com.marketplace.Offre_Demande.dto.RequestDtoResponse;

public interface RequestService {

    public void Save(Long idConsommateur,RequestDtoRequest requestDtoRequest);
    public void Delete(Long id);
    public void Update(Long id,RequestDtoRequest requestDtoRequest);
    public void ValidateRequestProducteur(Long idProducteur,Long idRequest);
    public void UpdateValidation(Long id);
    public void AnnulerRequest(Long id);
    public void UpdateStatus(Long id);
    public RequestDtoResponse getRequest(Long id);
    public List<RequestDtoResponse> getAll();
    public List<RequestDtoResponse> getAllForValidate(Long idValidate);
    public List<RequestDtoResponse> getAllForProduct(Long idProduct);
}
