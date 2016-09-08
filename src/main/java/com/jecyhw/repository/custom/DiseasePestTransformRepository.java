package com.jecyhw.repository.custom;

import com.jecyhw.model.database.Pest;
import com.jecyhw.document.DiseasePest;

/**
 * Created by jecyhw on 16-9-7.
 */
public interface DiseasePestTransformRepository {
    DiseasePest transformPictures(DiseasePest diseasePest);
    Pest transformPest(DiseasePest diseasePest);
}
