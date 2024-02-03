<%@ include file="common/header.jspf" %>

<div class="container mt-5">
    <div class="card border-primary mx-auto" style="max-width: 400px;">
        <div class="card-header bg-primary text-white">
            Login
        </div>
        <div class="card-body">
            <form action="/login" method="post" modelAttribute="user">
                <div class="mb-3">
                    <label for="userName" class="form-label">Username:</label>
                    <input type="text" id="userName" name="userName" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Password:</label>
                    <input type="password" id="password" name="password" class="form-control" required>
                </div>
                <button type="submit" class="btn btn-primary">Login</button>
            </form>
            <div class="mt-3" style="color:red">${errorMessage}</div>
        </div>
    </div>
</div>

<%@ include file="common/footer.jspf" %>
