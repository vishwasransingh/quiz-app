<%@ include file="common/header.jspf" %>

<div class="container mt-5">
    <h2 class="mb-4">Quiz Result</h2>
    <hr>

    <c:if test="${not empty testResult}">
        <div class="row">
            <div class="col-md-6">
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
                <hr>
            </div>
        </div>

        <c:choose>
            <c:when test="${not empty testResult.feedbackQuestions}">
                <h5 class="mb-3">What you need to study :</h5>
                <c:forEach items="${testResult.feedbackQuestions}" var="question" varStatus="questionNumber">
                    <div class="mb-4">
                        <p class="lead">${questionNumber.index + 1}. ${question.question}</p>

                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="answers[${questionNumber.index}]" value="A" id="optionA${questionNumber.index}" disabled>
                            <label class="form-check-label" for="optionA${questionNumber.index}">A. ${question.options[0]}</label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="answers[${questionNumber.index}]" value="B" id="optionB${questionNumber.index}" disabled>
                            <label class="form-check-label" for="optionB${questionNumber.index}">B. ${question.options[1]}</label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="answers[${questionNumber.index}]" value="C" id="optionC${questionNumber.index}" disabled>
                            <label class="form-check-label" for="optionC${questionNumber.index}">C. ${question.options[2]}</label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="answers[${questionNumber.index}]" value="D" id="optionD${questionNumber.index}" disabled>
                            <label class="form-check-label" for="optionD${questionNumber.index}">D. ${question.options[3]}</label>
                        </div>
                        <p class="mb-1">Selected Option : ${question.userSelectedAnswer}</p>
                        <p class="mb-1">Correct Option : ${question.correctAnswer}</p>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <div class="alert alert-success">Congratulations! You got all the answers correct!</div>
            </c:otherwise>
        </c:choose>
    </c:if>
</div>

<%@ include file="common/footer.jspf" %>
