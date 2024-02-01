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
        <hr>
        <h5>What you need to study :</h5>
        
        <c:forEach items="${testResult.feedbackQuestions}" var="question" varStatus="questionNumber">
        <div class="mb-4">
            <p class="lead">${questionNumber.index + 1}. ${question.question}</p>

            <div class="form-check">
                <input class="form-check-input" type="radio" name="answers[${questionNumber.index}]" value="A" id="optionA${questionNumber.index}">
                <label class="form-check-label" for="optionA${questionNumber.index}">A. ${question.options[0]}</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="answers[${questionNumber.index}]" value="B" id="optionB${questionNumber.index}">
                <label class="form-check-label" for="optionB${questionNumber.index}">B. ${question.options[1]}</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="answers[${questionNumber.index}]" value="C" id="optionC${questionNumber.index}">
                <label class="form-check-label" for="optionC${questionNumber.index}">C. ${question.options[2]}</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="answers[${questionNumber.index}]" value="D" id="optionD${questionNumber.index}">
                <label class="form-check-label" for="optionD${questionNumber.index}">D. ${question.options[3]}</label>
            </div>
            <p> Selected Option : ${question.userSelectedAnswer}</p>
            <p> Correct Option : ${question.correctAnswer}</p>
            
        </div>
    </c:forEach>
        
    </c:if>
</div>

<%@ include file="common/footer.jspf" %>
