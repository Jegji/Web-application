package org.example.service;

import org.example.Material;
import org.example.dto.MaterialReadDto;
import org.example.dto.MaterialSummeryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class CommunicationService {
    private final RestTemplate restTemplate;
    @Autowired
    public CommunicationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void notifyFilamentsToDelete(UUID id) {
        restTemplate.delete("http://localhost:8082/api/filaments/material/" + id);
    }

    public void notifyFilamentsNewProject(Material material) {
        String url = "http://localhost:8082/api/filaments/material/" + material.getId();
        MaterialSummeryDto materialReadDto = MaterialSummeryDto.from(material);
        restTemplate.postForObject(url, materialReadDto, Void.class);
    }

    public void notifyFilamentsUpdateProject(Material material) {
        String url = "http://localhost:8082/api/filaments/material/" + material.getId();
        MaterialReadDto materialReadDto = MaterialReadDto.from(material);
        restTemplate.put(url, materialReadDto);
    }
}
