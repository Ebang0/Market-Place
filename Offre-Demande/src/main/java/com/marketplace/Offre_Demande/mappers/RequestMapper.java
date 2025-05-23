package com.marketplace.Offre_Demande.mappers;

import org.springframework.stereotype.Service;

import com.marketplace.Offre_Demande.dto.RequestDtoRequest;
import com.marketplace.Offre_Demande.dto.RequestDtoResponse;
import com.marketplace.Offre_Demande.entity.Request;
import com.marketplace.Offre_Demande.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RequestMapper {
    private final ProductRepository productRepository;
    public Request DtoToEntity(RequestDtoRequest requestDtoRequest)
    {
        Request request = new Request();
        request.setName(requestDtoRequest.name());
        request.setDescription(requestDtoRequest.description());
        request.setQuantity(requestDtoRequest.quantity());
        request.setProduct(productRepository.findById(requestDtoRequest.idProduct()).get());
        request.setDate(requestDtoRequest.date());

        return request;
    }

    public RequestDtoResponse EntityToDto(Request request)
    {
        return new RequestDtoResponse(request.getName(),request.getId(),request.getQuantity(),request.getDate(),request.getDescription(),request.getPrice());
    }
}
