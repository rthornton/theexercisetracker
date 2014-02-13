<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet"  type="text/css" />

    <!-- Custom styles for this template -->
    <link href="/resources/bootstrap-theme.css" rel="stylesheet">
</head>
<body>
<div class="container">

    <div class="starter-template">
        <h1>Bootstrap starter template</h1>

        <p class="lead">Use this document as a way to quickly start any new project.<br> All you get is this text and a
            mostly barebones HTML document.</p>

        <h1>${message}</h1>
    </div>

    <div class="row">
        <div class="col-md-12" id="fileuploadContent">
            <h2> File Upload </h2>
            <form id="fileuploadForm" action="fileupload" method="POST" enctype="multipart/form-data" class="cleanform">
                <div class="header">
                    <c:if test="${not empty message}">
                        <div id="message" class="success">${message}</div>
                    </c:if>
                </div>

                <input type="file" id="file" name="file" />

                <div style="padding-top:20px;"></div>

                <div>
                    <button type="submit" class="btn btn-primary">Upload</button>
                </div>
                <p></p>
            </form>
        </div>
    </div>
</div>
<!-- /.container -->

<script src="https://code.jquery.com/jquery.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
</body>
</html>