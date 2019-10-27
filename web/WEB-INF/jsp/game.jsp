<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Nouvelle partie</title>
    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/vendor/foundation-6.5.1/css/foundation.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
<div class="row small-5 small-centered connection-box">
    <form method="POST">
        <div class="form-icons">
            <h4>Partie</h4>
            <div class="input-group">
                <input class="input-group-field" type="text" placeholder="${gameBean.currentExpression.strCalcul}" name="form-expression"
                       value=""/>
            </div>
            <div class="input-group">
                <input class="input-group-field" type="text" placeholder="Saisissez un résultat"
                       name="form-result"
                       value=""/>
            </div>
        </div>
        <button class="button expanded">Valider</button>
    </form>
</div>
<script src="${pageContext.request.contextPath}/vendor/foundation-6.5.1/js/vendor/jquery.js"></script>
<script src="${pageContext.request.contextPath}/vendor/foundation-6.5.1/js/vendor/foundation.min.js"></script>
<script>
    $(document).foundation();
    document.documentElement.setAttribute('data-useragent', navigator.userAgent);
</script>
</body>
</html>
