package com.jecyhw.repository.transform.impl;

import com.jecyhw.model.database.Pest;
import com.jecyhw.document.DiseasePest;
import com.jecyhw.document.Paragraph;
import com.jecyhw.document.Picture;
import com.jecyhw.repository.transform.Transform;
import com.jecyhw.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jecyhw on 16-9-6.
 */
@Component
final public class DiseasePestToPestImpl implements Transform<DiseasePest, Pest> {

    Logger logger = LoggerFactory.getLogger(DiseasePestToPestImpl.class);

    DiseasePest diseasePest;

    Map<String, Picture> linkPictures;

    @Override
    public Pest transform(DiseasePest diseasePest) {
        this.diseasePest = diseasePest;
        this.linkPictures = new HashMap<>();
        Pest pest = new Pest();
        pest.setChineseName(chineseName());
        pest.setScientificName(scientificName());
        pest.setKingdom(kingdom());
        pest.setPhylum(phylum());
        pest.setSubPhylum(subPhylum());
        pest.setPestClass(pestClass());
        pest.setPestSubClass(pestSubClass());
        pest.setOrder(order());
        pest.setSubOrder(subOrder());
        pest.setFamily(family());
        pest.setSubFamily(subFamily());
        pest.setGenus(genus());
        pest.setSubGenus(subGenus());
        pest.setSpecies(species());
        pest.setSubSpecies(subSpecies());
        pest.setBriefIntroduction(briefIntroduction());
        pest.setDistributionArea(distributionArea());
        pest.setMorphologicalCharacteristic(morphologicalCharacteristic());
        pest.setOccurrenceRule(occurrenceRule());
        pest.setPreventionMethod(preventionMethod());
        pest.setLivingHabit(livingHabit());
        pest.setPictures(pictures());
        return pest;
    }

    private String chineseName() {
        return diseasePest.getName();
    }

    private String scientificName() {
        return introductionStringOrListValue("拉丁学名");
    }

    private String kingdom() {
        return updateFilterValue(introductionStringOrListValue("界"));
    }

    private String phylum() {
        return updateFilterValue(introductionStringOrListValue("门"));
    }

    private String subPhylum() {
        return updateFilterValue(introductionStringOrListValue("亚门"));
    }

    private String pestClass() {
        return updateFilterValue(introductionStringOrListValue("纲"));
    }

    private String pestSubClass() {
        return updateFilterValue(introductionStringOrListValue("亚纲"));
    }

    private String order() {
        return updateFilterValue(introductionStringOrListValue("目"));
    }

    private String subOrder() {
        return updateFilterValue(introductionStringOrListValue("亚目"));
    }

    private String family() {
        return updateFilterValue(introductionStringOrListValue("科"));
    }

    private String subFamily() {
        return updateFilterValue(introductionStringOrListValue("亚科"));
    }

    private String genus() {
        return updateFilterValue(introductionStringOrListValue("属"));
    }

    private String subGenus() {
        return updateFilterValue(introductionStringOrListValue("亚属"));
    }

    private String species() {
        return introductionStringOrListValue("种");
    }

    private String subSpecies() {
        return introductionStringOrListValue("亚种");
    }

    private String briefIntroduction() {
        return introductionStringOrListValue("简介");
    }

    private String distributionArea() {
        String result = introductionStringOrListValue("分布地区");
        if (result == null) {
            result = introductionStringOrListValue("分布区域");
            if (result == null) {
                result = introductionStringOrListValue("分布范围");
                if (result == null) {
                    result = introductionStringOrListValue("分布");
                    if (result == null) {
                        result = introductionStringOrListValue("分布与危害");
                        if (result == null) {
                            result = introductionStringOrListValue("分布与寄主");
                            if (result == null) {
                                result = introductionStringOrListValueInMap("昆虫名", "分布范围");
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    private String morphologicalCharacteristic() {
        String result = introductionStringOrListValue("形态特征");
        if (result == null) {
            result = introductionStringOrListValue("形态特征及习性");
            if (result == null) {
                result = introductionStringOrListValue("外形特征");
                if (result == null) {
                    result = introductionStringOrListValue("形体特征");
                    if (result == null) {
                        result = introductionStringOrListValue("形态");
                        if (result == null) {
                            result = introductionStringOrListValue("特征描述");
                            if (result == null) {
                                result = introductionStringOrListValue("特征");
                                if (result == null) {
                                    result = introductionStringOrListValue("特点");
                                    if (result == null) {
                                        result = introductionStringOrListValueInMap("昆虫名", "外形特征");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    private String occurrenceRule() {
        String result = introductionStringOrListValue("发生规律");
        if (result == null) {
            result = introductionStringOrListValue("发病特点");
            if (result == null) {
                result = introductionStringOrListValue("发生消长规律");
            }
        }
        return result;
    }

    private String preventionMethod() {
        String result = introductionStringOrListValue("防治方法");
        if (result == null) {
            result = introductionStringOrListValue("防治措施");
            if (result == null) {
                result = introductionStringOrListValue("防治");
                if (result == null) {
                    result = introductionStringOrListValue("除害处理");
                    if (result == null) {
                        result = introductionStringOrListValue("控制方法");
                        if (result == null) {
                            result = introductionStringOrListValue("控制技术");
                            if (result == null) {
                                result = introductionStringOrListValue("无公害防治措施");
                                if (result == null) {
                                    result = introductionStringOrListValueInMap("昆虫名", "防治方法");
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    private String livingHabit() {
        String result = introductionStringOrListValue("生活习性");
        if (result == null) {
            result = introductionStringOrListValue("生活史及习性");
            if (result == null) {
                result = introductionStringOrListValue("生活史和习性");
                if (result == null) {
                    result = introductionStringOrListValue("生活史");
                    if (result == null) {
                        result = introductionStringOrListValueInMap("生物学及习性", "生活习性");
                        if (result == null) {
                            result = introductionStringOrListValueInMap("昆虫名", "生活习性");
                        }
                    }
                }
            }
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    private List<Picture> pictures() {
        map((Map<String, Object>)diseasePest.getIntroduction());
        return new ArrayList<>(linkPictures.values());
    }

    private String introductionStringOrListValueInMap(String key, String subKey) {
        logger.debug(key);
        Map<String, Object> map = introductionMap(key);
        if (map != null) {
            return introductionStringOrListValue(map.get(subKey));
        }
        return null;
    }

    private String introductionStringOrListValue(String key) {
        return introductionStringOrListValue(introductionObjectValue(key));
    }

    private String introductionStringOrListValue(Object value) {
        if (value instanceof String) {
            return StringUtil.removeBlank((String) value);
        } else if (value instanceof List){
            return paraListValue(value);
        } else {
            return null;
        }
    }

    private String updateFilterValue(String value) {
        if (value == null) {
            return value;
        }
        String result = StringUtil.removeBlank(value);
        if (!result.matches("^[a-zA-Z0-9(（)）]*$")) {
            result = result.replaceAll("[a-zA-Z0-9(（)）]", "");
        }
        return result;
    }

    private Object introductionObjectValue(String key) {
        return diseasePest.getIntroduction().get(key);
    }

    @SuppressWarnings("unchecked")
    private String paraListValue(Object paragraphs) {
        if (paragraphs instanceof List) {
            List<String> stringList = new ArrayList<>();
            for (Paragraph paragraph : (List<Paragraph>)paragraphs) {
                stringList.add(StringUtil.removeBlank(paragraph.getContent()));
            }
            if (stringList.size() > 0) {
                return String.join("\n", stringList);
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> introductionMap(String key) {
        return (Map<String, Object>) diseasePest.getIntroduction().get(key);
    }

    @SuppressWarnings("unchecked")
    private void map(Map<String, Object> map) {
        for (String key : map.keySet()) {
            Object obj = map.get(key);
            if (obj instanceof List) {
                list((List<Object>) obj);
            } else if (obj instanceof Map) {
                map((Map<String, Object>) obj);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void list(List<Object> list) {
        for (Object obj : list) {
            if (obj instanceof Paragraph) {
                paragraph((Paragraph) obj);
            } else if (obj instanceof Picture) {
                picture((Picture) obj);
            } else if (obj instanceof List) {
                list((List<Object>) obj);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void paragraph(Paragraph paragraph) {
        for (Object obj : paragraph.getPictures()) {
            if (obj instanceof List) {
                list((List<Object>) obj);
            } else if (obj instanceof Picture) {
                picture((Picture) obj);
            }
        }
    }

    /**
     * 获取图片,
     * @param picture
     */
    private void picture(Picture picture) {
        String link = picture.getReference();
        if (!linkPictures.containsKey(link)) {
            linkPictures.put(link, picture);
        }
    }
}
