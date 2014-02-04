<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails</title>
</head>

<body>

<div class="row-fluid">
    <h3>
        Mocks list
    </h3>
</div>

<div class="row-fluid">
    <u:alert/>
</div>

<div class="pull-right">
    <div class="input-append">
        <g:link action="create" class="btn">Add New</g:link>
    </div>
</div>

<div id="list-profile" class="row-fluid">
    <table class="table table-striped table-hover table-bordered table-fixed">
        <thead>
        <tr>
            <th>URL</th>
            <th>Response</th>
            <th width="12%">Method</th>
            <th width="8%">Actions</th>
        </tr>
        </thead>

        <tbody>
        <g:each in="${mockInstanceList}" var="mock">
            <tr>
                <td>${request.hostUrl}<g:fieldValue field="url" bean="${mock}"/></td>
                <td><u:slasher str="${mock?.response}" limit="400"/></td>
                <td>${mock.method}</td>
                <td>
                    <g:link action="edit" id="${mock.id}">
                        <i class="icon-edit" data-toggle="tooltip" title="Edit"></i>
                    </g:link>

                    <a href="#confirmationModal" data-toggle="modal"
                       onclick="$('#mockId').attr('value', '${mock.id}')">
                        <i class="icon-remove" data-toggle="tooltip" title="Delete account"></i>
                    </a>
                </td>
            </tr>
        </g:each>
        </tbody>

        <g:if test="${mockInstanceTotal == 0}">
            <tfoot>
            <tr>
                <td colspan="4">
                    There are no responses to display
                </td>
            </tr>
            </tfoot>
        </g:if>

    </table>

    <div class="pagination">
        <g:paginate class="pagination-centered" total="${mockInstanceTotal}"/>
    </div>

    <u:confirmationWindow id="confirmationModal" title="Delete Mock"
                          message="Are you sure to delete this mock?"
                          controller="mock" action="delete">
        <g:hiddenField id="mockId" name="id" value=""/>
    </u:confirmationWindow>
</div>

</body>
</html>
