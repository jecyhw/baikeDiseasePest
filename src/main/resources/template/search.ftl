<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
<#list diseasePests as diseasePest>
    <div class="panel panel-default">
        <div class="panel-heading" role="tab" id="heading${diseasePest?index}">
            <h4 class="panel-title">
                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse${diseasePest?index}" aria-expanded="true" aria-controls="collapse${diseasePest?index}">
                    ${diseasePest.name}
                </a>
            </h4>
        </div>
        <div id="collapse${diseasePest?index}" class="panel-collapse collapse <#if diseasePest?is_first>in</#if>" role="tabpanel" aria-labelledby="headingS${diseasePest?index}">
            <div class="panel-body"  style="overflow: auto; max-height: 500px;">
                ${diseasePest}
            </div>
        </div>
    </div>
</#list>
</div>
