<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Gestion des Contacts - Indentifiez-vous</title>
    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/vendor/foundation-6.5.1/css/foundation.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<div class="callout large primary">
    <div class="row column text-center">
        <h1>Login Mental Game</h1>
    </div>
</div>
<div class="row small-5 small-centered">
    <jsp:useBean id="userBean" class="model.UserBean" scope="request"/>
    <c:if test="${ !empty userBean.authResult }">
        <div class="callout alert">
            <p>${ userBean.authResult }</p>
        </div>
    </c:if>

    <c:if test="${ !userBean.isConnected( pageContext.request ) }">
        <form method="POST" action="login">
            <div class="form-icons">
                <h4>Boîte de login</h4>
                <div class="input-group">
							<span class="input-group-label">
								<i class="fa fa-user"></i>
							</span>
                    <input class="input-group-field" type="text" placeholder="Login" name="form-username"
                           value="${ userBean.user.login }"/>
                </div>
                <div class="input-group">
							<span class="input-group-label">
								<i class="fa fa-key"></i>
							</span>
                    <input class="input-group-field" type="password" placeholder="Mot de passe"
                           name="form-password"
                           value=""/>
                </div>
            </div>
            <button class="button expanded">Valider</button>
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
