<html>
    <head>
        <title>Novo Usuário</title>
        <%@include file="../../commom/head-setting.jsp" %>
    </head>
    <body>
        <%@include file="../../commom/navbar.jsp" %>
        <h1>Cadastre-se</h1>
        <form action="user?action=create" method="post">
            <div class="form-field">
                <label>Nome</label>
                <input type="text" name="firstName">
            </div>

            <div class="form-field">
                <label>Sobrenome</label>
                <input type="text" name="lastName">
            </div>
            <div class="form-field">
                <label>Email</label>
                <input type="text" name="email">
            </div>
            <div class="form-field">
                <label>Senha</label>
                <input type="password" name="password">
            </div>
            <div class="form-field">
                <label>CPF</label>
                <input type="text" name="cpf">
            </div>

            <input type="submit" value="Cadastrar">
        </form>
    </body>
</html>