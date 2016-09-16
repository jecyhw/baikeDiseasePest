{
  "CollectionName": "虫害数据",
  "FacetCategories": {
    "FacetCategory": [
      {
        "Name": "chineseName",
        "Type": "String",
        "IsFilterVisible": "false"
      },
      {
        "Name": "scientificName",
        "Type": "String",
        "IsFilterVisible": "false"
      },
      {
        "Name": "kingdom",
        "Type": "String"
      },
      {
        "Name": "phylum",
        "Type": "String"
      }
    ,
      {
        "Name": "subPhylum",
        "Type": "String",
        "IsFilterVisible": "false"
      },
      {
        "Name": "pestClass",
        "Type": "String",
        "IsFilterVisible": "false"
      },
      {
        "Name": "pestSubClass",
        "Type": "String",
        "IsFilterVisible": "false"
      },
      {
        "Name": "order",
        "Type": "String",
        "IsFilterVisible": "false"
      },
      {
        "Name": "subOrder",
        "Type": "String",
        "IsFilterVisible": "false"
      },
      {
        "Name": "family",
        "Type": "String",
        "IsFilterVisible": "false"
      },
      {
        "Name": "subFamily",
        "Type": "String",
        "IsFilterVisible": "false"
      },
      {
        "Name": "genus",
        "Type": "String",
        "IsFilterVisible": "false"
      },
      {
        "Name": "subGenus",
        "Type": "String",
        "IsFilterVisible": "false"
      }
    <#--,-->
      <#--{-->
        <#--"Name": "species",-->
        <#--"Type": "String",-->
        <#--"IsFilterVisible": "false"-->
      <#--},-->
      <#--{-->
        <#--"Name": "subSpecies",-->
        <#--"Type": "String",-->
        <#--"IsFilterVisible": "false"-->
      <#--},-->
      <#--{-->
        <#--"Name": "briefIntroduction",-->
        <#--"Type": "String",-->
        <#--"IsFilterVisible": "false"-->
      <#--},-->
      <#--{-->
        <#--"Name": "distributionArea",-->
        <#--"Type": "String",-->
        <#--"IsFilterVisible": "false"-->
      <#--},-->
      <#--{-->
        <#--"Name": "morphologicalCharacteristic",-->
        <#--"Type": "String",-->
        <#--"IsFilterVisible": "false"-->
      <#--},-->
      <#--{-->
        <#--"Name": "occurrenceRule",-->
        <#--"Type": "String",-->
        <#--"IsFilterVisible": "false"-->
      <#--},-->
      <#--{-->
        <#--"Name": "preventionMethod",-->
        <#--"Type": "String",-->
        <#--"IsFilterVisible": "false"-->
      <#--},-->
      <#--{-->
        <#--"Name": "livingHabit",-->
        <#--"Type": "String",-->
        <#--"IsFilterVisible": "false"-->
      <#--},-->
      <#--{-->
        <#--"Name": "pictures",-->
        <#--"Type": "Link",-->
        <#--"IsFilterVisible": "false"-->
      <#--}-->
    ]
  },
  "Items": {
    "Item": [
    <#list pests as pest>
      {
        "Id": "${pest.pictures?first.filename}",
        "Img": "#${pest.pictures?first.filename}",
        "Name": "${pest.chineseName?json_string}",
        "Description": "${pest.briefIntroduction!?json_string}",
        "Facets": {
          "Facet": [
            {
              "String": {
                "Value": "${pest.chineseName?json_string}"
              },
              "Name": "chineseName"
            },
            {
              "String": {
                "Value": "${pest.scientificName!?json_string}"
              },
              "Name": "scientificName"
            },
            {
              "String": {
                "Value": "${pest.kingdom!?json_string}"
              },
              "Name": "kingdom"
            },
            {
              "String": {
                "Value": "${pest.phylum!?json_string}"
              },
              "Name": "phylum"
            },
            {
              "String": {
                "Value": "${pest.subPhylum!?json_string}"
              },
              "Name": "subPhylum"
            },
            {
              "String": {
                "Value": "${pest.pestClass!?json_string}"
              },
              "Name": "pestClass"
            },
            {
              "String": {
                "Value": "${pest.pestSubClass!?json_string}"
              },
              "Name": "pestSubClass"
            },
            {
              "String": {
                "Value": "${pest.order!?json_string}"
              },
              "Name": "order"
            },
            {
              "String": {
                "Value": "${pest.subOrder!?json_string}"
              },
              "Name": "subOrder"
            },
            {
              "String": {
                "Value": "${pest.family!?json_string}"
              },
              "Name": "family"
            },
            {
              "String": {
                "Value": "${pest.subFamily!?json_string}"
              },
              "Name": "subFamily"
            },
            {
              "String": {
                "Value": "${pest.genus!?json_string}"
              },
              "Name": "genus"
            },
            {
              "String": {
                "Value": "${pest.subGenus!?json_string}"
              },
              "Name": "subGenus"
            },
            {
              "String": {
                "Value": "${pest.species!?json_string}"
              },
              "Name": "species"
            },
            {
              "String": {
                "Value": "${pest.subSpecies!?json_string}"
              },
              "Name": "subSpecies"
            },
            {
              "String": {
                "Value": "${pest.briefIntroduction!?json_string}"
              },
              "Name": "briefIntroduction"
            },
            {
              "String": {
                "Value": "${pest.distributionArea!?json_string}"
              },
              "Name": "distributionArea"
            },
            {
              "String": {
                "Value": "${pest.morphologicalCharacteristic!?json_string}"
              },
              "Name": "morphologicalCharacteristic"
            },
            {
              "String": {
                "Value": "${pest.occurrenceRule!?json_string}"
              },
              "Name": "occurrenceRule"
            },
            {
              "String": {
                "Value": "${pest.preventionMethod!?json_string}"
              },
              "Name": "preventionMethod"
            },
            {
              "String": {
                "Value": "${pest.livingHabit!?json_string}"
              },
              "Name": "livingHabit"
            },
            {
              "Link": [
                  <#list pest.pictures as picture>
                    {
                      "Value": "${picture.filename}"
                    }
                      <#if picture?has_next>,</#if>
                  </#list>
              ],
              "Name": "pictures"
            }
          ]
        }
      }
        <#if pest?has_next>,</#if>
    </#list>
    ],
    "ImgBase": "pestDeepZoomFiles.xml"
  }
}
