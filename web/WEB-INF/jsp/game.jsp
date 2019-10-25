<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Liste des scores</title>
    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/vendor/foundation-6.5.1/css/foundation.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<div class="row small-5 small-centered connection-box">
    <jsp:useBean id="userBean" class="model.UserBean" scope="request"/>
    <c:if test="${ !empty userBean.authResult }">
        <div class="callout alert">
            <p>${ userBean.authResult }</p>
        </div>
    </c:if>

    <c:if test="${ !userBean.isConnected( pageContext.request ) }">
        <form method="POST" action="game">
            <div class="form-icons">
                <h4>Jeu de Calcul mental</h4>
<%--        BOUCLE FOR        e--%>
            </div>
            <button class="button expanded">Connexion</button>
        </form>
    </c:if>
</div>
<script src="${pageContext.request.contextPath}/vendor/foundation-6.5.1/js/vendor/jquery.js"></script>
<script src="${pageContext.request.contextPath}/vendor/foundation-6.5.1/js/vendor/foundation.min.js"></script>
<script>
    $(document).foundation();
    document.documentElement.setAttribute('data-useragent', navigator.userAgent);
</script>
</body>
</html>
