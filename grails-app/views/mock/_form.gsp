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
        <g:radioGroup name="method"
                      labels="${RequestMethod.values().collect{it.name()}}"
                      values="${RequestMethod.values().collect{it.name()}}"
                      value="${mockInstance?.method ?: 'GET'}">
            <p>${it.label} ${it.radio}</p>
        </g:radioGroup>
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



