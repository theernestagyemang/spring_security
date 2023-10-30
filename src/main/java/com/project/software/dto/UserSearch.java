package com.project.software.dto;

import com.project.software.entity.Client;
import com.project.software.entity.Freelancer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Repository
@Transactional
public class UserSearch {

    // ------------------------
    // PRIVATE FIELDS
    // ------------------------

    // Spring will inject here the entity manager object
    @PersistenceContext
    private EntityManager entityManager;


    // ------------------------
    // PUBLIC METHODS
    // ------------------------


    public List<Freelancer> search(String text) {

        // get the full text entity manager
        SearchSession searchSession = Search.session((Session) entityManager);

        SearchResult<Freelancer> result = searchSession.search(Freelancer.class)
                .where(f -> f.match()
                        .fields("fullName", "currentLocation", "educationLevel", "proffesionalTitile", "highestQualification")
                        .matching(text).fuzzy())
                .fetch(50);

        long totalHitCount = result.total().hitCount();
        List<Freelancer> hits = result.hits();

        return hits;
    }


    public List<Freelancer> searchMultipleTerm(String keywords) {
        String[] arrKeywords = keywords.split(",");
        return findByList(Arrays.asList(arrKeywords));
    }

    public List<Freelancer> searchMultipleTerm(String keywords, Integer page, Integer max) {
        String[] arrKeywords = keywords.split(",");
        return findByList(Arrays.asList(arrKeywords));
    }

    public List<Freelancer> findByList(List<String> text) {


        SearchSession searchSession = Search.session((Session) entityManager);

        SearchResult<Freelancer> result = searchSession.search(Freelancer.class)
                .where(f -> f.match()
                        .fields("fullName", "currentLocation", "educationLevel", "proffesionalTitile", "highestQualification", "yearsOfExperience", "primaryContact")
                        .matching(text).fuzzy())
                .fetch(50);

        long totalHitCount = result.total().hitCount();
        List<Freelancer> hits = result.hits();

        return hits;


    } // method search



    //list of Employers
    public List<Client> searchEmployers(String text) {
        SearchSession searchSession = Search.session((Session) entityManager);

        SearchResult<Client> result = searchSession.search(Client.class)
                .where(f -> f.match()
                        .fields("companyName", "email", "phoneNumber")
                        .matching(text).fuzzy())
                .fetch(50);

        long totalHitCount = result.total().hitCount();

        return result.hits();

    } // method search


    public List<Client> searchPaginatedClients(String q, Integer page, Integer max) {
        SearchSession searchSession = Search.session((Session) entityManager);

        SearchResult<Client> result = searchSession.search(Client.class)
                .where(f -> f.match()
                        .fields("companyName", "email", "phoneNumber")
                        .matching(q).fuzzy())
                .fetch(50);

        long totalHitCount = result.total().hitCount();
        List<Client> list = result.hits();

        return list;
    }


    public List<Freelancer> searchPaginated(String q, Integer page, Integer max) {
        return search(q);
    }

} // class
