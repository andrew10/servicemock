<html>
<head>
    <meta name="layout" content="main">
    <title>Add new mock</title>
</head>

<body>

<h3>Create Mock</h3>

<div class="row-fluid">
    <u:alert/>
</div>

<div class="row-fluid">
    <g:form class="form-horizontal" name="cram-create-form" controller="mock" action="save">
        <tmpl:form/>
        <div class="form-actions">
            <g:link class="btn" action="list">
                Cancel
            </g:link>
            <g:submitButton name="save" class="btn btn-primary" value="Save"/>
        </div>
    </g:form>
</div>
</body>
</html>
