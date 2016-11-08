package com.jecyhw.model.database;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;

/**
 * Created by jecyhw on 16-9-6.
 */
final public class Disease extends BaseModel{
    /**
     * 中文名
     */
    @NotBlank
    @Indexed(unique = true)
    @Field("chinese_name")
    private String chineseName;

    /**
     * 别名
     */
    private String alias;

    /**
     * 英文名
     */
    @Field("scientific_name")
    private String scientificName;

    /**
     * 分布(分布范围)
     */
    @Field("distribution_area")
    private String distributionArea;

    /**
     * 症状(病害症状,危害情况)
     */
    private String symptom;

    /**
     * 简介(基本信息)
     */
    @NotBlank
    @Field("brief_introduction")
    private String briefIntroduction;

    /**
     * 病原(病原中文名)
     */
    private String pathogeny;


    /**
     * 病原英文名(病原拉丁学名,病原学名)
     */
    @Field("pathogeny_english")
    private String pathogenyEnglish;

    /**
     * 病原类型
     */
    @NotBlank
    @Field("pathogenic_type")
    private String pathogenicType;

    /**
     * 为害植物(主要危害作物,寄主)
     */
    @Field("damage_plant")
    private String damagePlant;

    /**
     * 为害部位(主要为害部位)
     */
    @Field("disease_position")
    private String diseasePosition;


    /**
     * 传播方式(传播途径)
     */
    @Field("transmission_way")
    private String transmissionWay;

    /**
     * 发病规律(发生特点,发生规律)
     */
    @Field("occurrence_rule")
    private String occurrenceRule;

    /**
     * 防治方法
     */
    @NotBlank
    @Field("prevention_method")
    private String preventionMethod;

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getDistributionArea() {
        return distributionArea;
    }

    public void setDistributionArea(String distributionArea) {
        this.distributionArea = distributionArea;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getBriefIntroduction() {
        return briefIntroduction;
    }

    public void setBriefIntroduction(String briefIntroduction) {
        this.briefIntroduction = briefIntroduction;
    }

    public String getPathogeny() {
        return pathogeny;
    }

    public void setPathogeny(String pathogeny) {
        this.pathogeny = pathogeny;
    }

    public String getPathogenyEnglish() {
        return pathogenyEnglish;
    }

    public void setPathogenyEnglish(String pathogenyEnglish) {
        this.pathogenyEnglish = pathogenyEnglish;
    }

    public String getPathogenicType() {
        return pathogenicType;
    }

    public void setPathogenicType(String pathogenicType) {
        this.pathogenicType = pathogenicType;
    }

    public String getDamagePlant() {
        return damagePlant;
    }

    public void setDamagePlant(String damagePlant) {
        this.damagePlant = damagePlant;
    }

    public String getDiseasePosition() {
        return diseasePosition;
    }

    public void setDiseasePosition(String diseasePosition) {
        this.diseasePosition = diseasePosition;
    }

    public String getTransmissionWay() {
        return transmissionWay;
    }

    public void setTransmissionWay(String transmissionWay) {
        this.transmissionWay = transmissionWay;
    }

    public String getOccurrenceRule() {
        return occurrenceRule;
    }

    public void setOccurrenceRule(String occurrenceRule) {
        this.occurrenceRule = occurrenceRule;
    }

    public String getPreventionMethod() {
        return preventionMethod;
    }

    public void setPreventionMethod(String preventionMethod) {
        this.preventionMethod = preventionMethod;
    }
}
