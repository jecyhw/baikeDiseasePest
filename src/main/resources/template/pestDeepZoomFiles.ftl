<?xml version="1.0" encoding="UTF-8"?>
<Collection MaxLevel="0" TileSize="256" Format="png" NextItemId="${deepZoomFiles?size}" xmlns="http://schemas.microsoft.com/deepzoom/2009">
    <Items>
        <#list deepZoomFiles as file>
            <I Id="${file.id}" N="${file.id}"  Source="images/${file.id}.xml"><Size Width="${file.width?c}" Height="${file.height?c}"/></I>
        </#list>
    </Items>
</Collection>
