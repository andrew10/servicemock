%{--
The source code contained in the file is the property of Simple Health Inc.
All rights reserved 2013.
 --}%
<html>
<head>
    <meta name="layout" content="main">
    <title>Edit Mock</title>
</head>

<body>

<h3>Edit Mock</h3>

<div class="row-fluid">
    <u:alert/>
</div>

<div class="row-fluid">
    <g:form class="form-horizontal" name="cram-edit-form" action="update" method="POST">
        <tmpl:form/>
        <div class="form-actions">
            <g:link class="btn" action="list">
                Cancel
            </g:link>
            <g:submitButton name="save" class="btn btn-primary" value="Update"/>
        </div>
    </g:form>
</div>
</body>
</html>
