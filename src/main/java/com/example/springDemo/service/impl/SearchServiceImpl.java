package com.example.springDemo.service.impl;

import com.example.springDemo.client.SearchClient;
import com.example.springDemo.constants.SolrFieldNames;
import com.example.springDemo.dto.ProductResponseDTO;
import com.example.springDemo.dto.SearchRequestDTO;
import com.example.springDemo.dto.SearchResponseDTO;
import com.example.springDemo.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchClient searchClient;


    public void awaitTerminationAfterShutdown(ExecutorService threadPool) {
        threadPool.shutdown();
        try {
            if (!threadPool.awaitTermination(1, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
            }
        } catch (InterruptedException ex) {
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public SearchResponseDTO productSearch(SearchRequestDTO request) {
        SearchResponseDTO res = new SearchResponseDTO();
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(() -> {
            System.out.println(Thread.currentThread().getId());
            List<ProductResponseDTO> al = getSearchtermBaseproducts(request.getSearchTerm());
            res.setProducts(al);
        });
        executor.execute(() -> {
            System.out.println(Thread.currentThread().getId());
            String q = SolrFieldNames.STOCK_LOCATION+":"+request.getLocation();
            List<ProductResponseDTO> all = getSearchtermBaseproducts(q);
            res.setProductsByLocation(all);
        });

        awaitTerminationAfterShutdown(executor);
        return res;
    }

    public List<ProductResponseDTO> getSearchtermBaseproducts(String query){

        boolean stock;
        double price;

        Map<String, Object> products = searchClient.getProducts(query);
        ArrayList<HashMap<String, Object>> locationList = (ArrayList<HashMap<String, Object>>)((HashMap<String, Object>)products.get("response")).get("docs");

        List<ProductResponseDTO> productsList = new ArrayList<>();

        for(HashMap<String, Object> obj : locationList){
            if ((int) obj.get(SolrFieldNames.IN_STOCK) > 0) {
                stock = true;
            } else {
                stock = false;
            }
            price = Double.parseDouble(obj.get(SolrFieldNames.OFFER_PRICE).toString());
            productsList.add(new ProductResponseDTO(stock, (int) price, obj.get(SolrFieldNames.DESCRIPTION).toString(), obj.get(SolrFieldNames.NAME).toString()));

        }
        return productsList;
    }
}
