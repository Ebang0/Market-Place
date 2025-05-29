package com.marketplace.Offre_Demande.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.marketplace.Offre_Demande.dto.RequestDtoRequest;
import com.marketplace.Offre_Demande.dto.RequestDtoResponse;
import com.marketplace.Offre_Demande.entity.Request;
import com.marketplace.Offre_Demande.entity.Request.Status;
import com.marketplace.Offre_Demande.exception.ResourceNotFoundException;
import com.marketplace.Offre_Demande.mappers.RequestMapper;
import com.marketplace.Offre_Demande.repository.ProductRepository;
import com.marketplace.Offre_Demande.repository.RequestRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class RequestServiceImplement implements RequestService {

    private final RequestMapper requestMapper;
    private final RequestRepository requestRepository;
    private final ProductRepository productRepository;

    @Override
    public void Save(Long idConsommateur,RequestDtoRequest requestDtoRequest) {
        if(idConsommateur == null || requestDtoRequest == null)
        {
            throw new ResourceNotFoundException("Pas d'informtion");
        }

        Request request = requestMapper.DtoToEntity(requestDtoRequest);
        request.setIdConsommateur(idConsommateur);
        request.setStatus(Status.Reading);
        request.setDateCreate(LocalDate.now());
        requestRepository.save(request);
    }

    @Override
    public void Delete(Long id) {
        if(id == null)
            throw new ResourceNotFoundException("Pas d'informaton");
        
        Request request = requestRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Pas demande"));
        
        requestRepository.delete(request);
    }

    @Override
    public void Update(Long id,RequestDtoRequest requestDtoRequest) {
        if(requestDtoRequest == null)
            throw new ResourceNotFoundException("Pas d'information");
        
        Request requests = requestRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Pas demande"));
        
        Request request = requestMapper.DtoToEntity(requestDtoRequest);

        requests.setDescription(request.getDescription());
        request.setName(request.getName());
        request.setProduct(request.getProduct());
        request.setQuantity(request.getQuantity());

        requestRepository.save(requests);

    }

    @Override
    public void UpdateValidation(Long id) {
        if(id == null)
            throw new ResourceNotFoundException("Pas d'information");

        Request request = requestRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Pas demande"));

        request.setValidate(true);

        requestRepository.save(request);
    }

    @Override
    public void AnnulerRequest(Long id) {
        if(id == null)
            throw new ResourceNotFoundException("Pas d'information");
        
        Request request = requestRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Pas demande"));

        request.setIdProducteur(null);
        request.setStatus(Status.Reading);
        requestRepository.save(request);
    }

    @Override
    public void UpdateStatus(Long id) {
           
    }

    @Override
    public RequestDtoResponse getRequest(Long id) {
        if(id == null)
            throw new ResourceNotFoundException("Pas d'information");
        
        Request request = requestRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Pas demande"));
        
        return requestMapper.EntityToDto(request);
    }

    @Override
    public List<RequestDtoResponse> getAll() {
        List<Request> requests = requestRepository.findAll();

        if(requests.size() < 1)
            throw new ResourceNotFoundException("Pas de demande");
        
        return requests.stream().map(requestMapper::EntityToDto).collect(Collectors.toList());  
    }

    @Override
    public List<RequestDtoResponse> getAllForValidate(Long idValidate) {

        if(idValidate == null || idValidate > 2 || idValidate < 1)
            throw new ResourceNotFoundException("Pas d'information");
          
        List<Request> requests = new ArrayList<>();

        if(idValidate == 1)
            requests=requestRepository.findByValidate(true);
        else 
            requests = requestRepository.findByValidate(false);        
            
        if(requests.size() < 1)
            throw new ResourceNotFoundException("Pas de demande");
        
        return requests.stream().map(requestMapper::EntityToDto).collect(Collectors.toList());  
    }

    @Override
    public List<RequestDtoResponse> getAllForProduct(Long idProduct) {
        
        if(idProduct == null)
            throw new ResourceNotFoundException("Pas d'information");

        List<Request> request = requestRepository.findByProduct(productRepository.findById(idProduct).get());
        List<Request> requests = request.stream().filter(o -> o.getValidate()).collect(Collectors.toList());
        return requests.stream().map(requestMapper::EntityToDto).collect(Collectors.toList());  
    }

    @Override
    public void ValidateRequestProducteur(Long idProducteur, Long idRequest) {
        if(idProducteur == null || idRequest == null)
            throw new ResourceNotFoundException("Pas d'information");
        
        Request request = requestRepository.findById(idRequest).get();
        request.setIdProducteur(idProducteur);

        requestRepository.save(request);
    }

}
