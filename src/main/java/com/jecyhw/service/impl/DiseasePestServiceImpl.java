package com.jecyhw.service.impl;

import com.google.common.collect.ImmutableList;
import com.jecyhw.html.baike.BaikeSearch;
import com.jecyhw.html.response.filter.impl.baike.DiseasePestFilter;
import com.jecyhw.model.database.Pest;
import com.jecyhw.document.DiseasePest;
import com.jecyhw.repository.DiseasePestRepository;
import com.jecyhw.service.DiseasePestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by jecyhw on 16-8-26.
 */
@Deprecated
@Service
final public class DiseasePestServiceImpl implements DiseasePestService {

    @Autowired
    DiseasePestRepository diseasePestRepository;

    @Override
    public DiseasePest search(String word, DiseasePest.Type type) throws IOException {
        DiseasePest diseasePest = diseasePestRepository.findByName(word);
        if (diseasePest == null) {
            BaikeSearch.Params params = new BaikeSearch.Params(word);
            BaikeSearch search = new BaikeSearch(params);
            diseasePest = new DiseasePest(new DiseasePestFilter(search.html()).filter(), word, search.getResponseUrl(), type);
            diseasePestRepository.save(diseasePest);
        }
        return diseasePest;
    }

    @Override
    public List<DiseasePest> all() {
        return ImmutableList.copyOf(diseasePestRepository.findAll());
    }

    @Override
    public void transformPictures() {
        for (DiseasePest diseasePest : diseasePestRepository.findAll()) {
            diseasePestRepository.transformPictures(diseasePest);
        }
    }

    @Override
    public List<Pest> transformPest() {
        List<Pest> pests = new ArrayList<>();
        for (DiseasePest diseasePest : diseasePestRepository.findByType(DiseasePest.Type.PEST)) {
            pests.add(diseasePestRepository.transformPest(diseasePest));
        }
        return pests;
    }


    @Override
    public Set<String> collectionFields(DiseasePest.Type type) {
        return collectionFields(diseasePestRepository.findByType(type));
    }

    @Override
    public Set<String> collectionFields(List<DiseasePest> allData) {
        return diseasePestRepository.collectionFields(allData);
    }

    @Override
    public Set<String> collectionFields() {
        return diseasePestRepository.collectionFields();
    }


}
