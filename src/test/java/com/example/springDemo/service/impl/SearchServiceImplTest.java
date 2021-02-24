package com.example.springDemo.service.impl;

import com.example.springDemo.client.SearchClient;

import com.example.springDemo.dto.SearchRequestDTO;
import com.example.springDemo.dto.SearchResponseDTO;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import java.net.URL;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
class SearchServiceImplTest {

    @InjectMocks
    private SearchServiceImpl searchService;

    @Mock
    private SearchClient searchClient;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void teardown(){
        verifyNoMoreInteractions(searchClient);
    }

    @Test
    void productSearch() throws IOException {
        SearchResponseDTO searchResponseDTO;
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> searchTermMockObject = objectMapper.readValue(new URL("file:src/test/resources/search.mock"), Map.class);
        Map<String, Object> locationMockObject = objectMapper.readValue(new URL("file:src/test/resources/location.mock"), Map.class);
        Mockito.when(searchClient.getProducts("samsung")).thenReturn(searchTermMockObject);
        Mockito.when(searchClient.getProducts("stockLocation:jakarta")).thenReturn(locationMockObject);
        SearchRequestDTO requestDTO = new SearchRequestDTO();
        requestDTO.setSearchTerm("samsung");
        requestDTO.setLocation("jakarta");
        searchResponseDTO = searchService.productSearch(requestDTO);
        //System.out.println(searchResponseDTO.getProducts().get(0).getTitle());
        assertEquals(searchResponseDTO.getProducts().size(), 10);
        assertEquals(searchResponseDTO.getProductsByLocation().size(), 10);

        Mockito.verify(searchClient).getProducts("samsung");
        Mockito.verify(searchClient).getProducts("stockLocation:jakarta");
    }

    @Test
    public void testWithException() throws IOException {
        SearchResponseDTO searchResponseDTO;
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> searchTermMockObject = objectMapper.readValue(new URL("file:src/test/resources/search.mock"), Map.class);

        Mockito.when(searchClient.getProducts("samsung")).thenReturn(searchTermMockObject);
        Mockito.when(searchClient.getProducts("stockLocation:jakarta")).thenThrow(NullPointerException.class);
        SearchRequestDTO requestDTO = new SearchRequestDTO();
        requestDTO.setSearchTerm("samsung");
        requestDTO.setLocation("jakarta");
        searchResponseDTO = searchService.productSearch(requestDTO);
        //System.out.println(searchResponseDTO.getProducts().get(0).getTitle());
        assertEquals(searchResponseDTO.getProducts().size(), 10);
        assertEquals(searchResponseDTO.getProductsByLocation(), null);

        Mockito.verify(searchClient).getProducts("samsung");
        Mockito.verify(searchClient).getProducts("stockLocation:jakarta");
    }
}