<%@ include file="common/header.jspf" %>

<div class="container mt-5">
    <h2>Quiz Result</h2>
    <hr>

    <c:if test="${not empty testResult}">
        <table class="table table-bordered">
            <tbody>
            <tr class="table-info">
                    <td>Total Questions</td>
                    <td>${testResult.totalQuestions}</td>
                </tr>
                <tr class="table-warning">
                    <td>Attempted Questions</td>
                    <td>${testResult.attemptedQuestions}</td>
                </tr>
                <tr class="table-success">
                    <td>Correct Answers</td>
                    <td>${testResult.correctAnswers}</td>
                </tr>
                <tr class="table-info">
                    <td>Percentage Score</td>
                    <td>${testResult.percentageScore}%</td>
                </tr>
            </tbody>
        </table>
    </c:if>
</div>

<%@ include file="common/footer.jspf" %>
