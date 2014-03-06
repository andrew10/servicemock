<div class="btn-group" data-toggle="buttons-radio">
    <g:each in="${items}" var="item">
        <button type="button" class="btn ${active == item ? 'active' : ''}"
                onclick="$('#hidden-method-field').attr('value', '${item}')">
            ${item}</button>
    </g:each>
    <g:hiddenField name="${fieldName}" id="hidden-method-field" value="${active}"/>
</div>
