package com.project.software.service;

import com.project.software.entity.Client;
import com.project.software.repository.ClientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    public final ClientRepo repository;

    public List<Client> findAll() {
        return new ArrayList<>(repository.findAll());
    }

    public void saveAll(List<Client> t) {
        repository.saveAll(t);
    }

    public void delete(Client t) {
        repository.delete(t);
    }

    public Optional<Client> findById(Integer id) {
        return repository.findById(id);
    }

    public boolean save(Client client) {
        try {
            repository.save(client);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Optional<Client> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public Optional<Client> findByFullName(String name) {
        return repository.findByFullName(name);
    }


    public boolean deleteById(Integer id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }

    }


    public Page<Client> findAll(Integer page) {
        return repository.findAll(PageRequest.of(page, 10, Sort.by(Sort.Direction.ASC, "fullName")));
    }

    public Integer getCompaniesCount() {
        return repository.getCompaniesCount();
    }
}
