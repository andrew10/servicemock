<%@ page import="servicemock.RequestMethod" %>
<g:hiddenField name="id" bean="${mockInstance}"/>
<g:hiddenField name="version" bean="${mockInstance}"/>


<div class="control-group ${hasErrors([field: 'title', bean: mockInstance], 'error')}">
    <label class="control-label" for="url">URL:</label>

    <div class="controls">
        <span class="add-on">${request.hostUrl}</span>
        <g:textField name="url" bean="${mockInstance}" class="url-field"/>
        <g:hasErrors field="url" bean="${mockInstance}">
            <span class="help-block error-message"><g:fieldError field="url" bean="${mockInstance}"/></span>
        </g:hasErrors>
    </div>
</div>

<div class="control-group">
    <label class="control-label" for="url">Method:</label>

    <div class="controls">
        <div class="btn-group" data-toggle="buttons-radio">
            <g:each in="${RequestMethod.values().collect { it.name() }}" var="method">
                <button type="button" class="btn ${mockInstance?.method?.toString() == method ? 'active' : ''}"
                        onclick="$('#hidden-method-field').attr('value', '${method}')">
                    ${method}</button>
            </g:each>
            <g:hiddenField name="method" id="hidden-method-field" value="${mockInstance?.method}"/>
        </div>
        <g:hasErrors field="method" bean="${mockInstance}">
            <span class="help-block error-message"><g:fieldError field="method" bean="${mockInstance}"/></span>
        </g:hasErrors>
    </div>
</div>


<div class="control-group  ${hasErrors([field: 'description', bean: mockInstance], 'error')}">
    <label class="control-label" for="response">Response:</label>

    <div class="controls">
        <g:textArea name="response" value="${mockInstance?.response}" class="response-field"/>
        <g:hasErrors field="response" bean="${mockInstance}">
            <span class="help-block error-message"><g:fieldError field="response" bean="${mockInstance}"/></span>
        </g:hasErrors>
    </div>
</div>



