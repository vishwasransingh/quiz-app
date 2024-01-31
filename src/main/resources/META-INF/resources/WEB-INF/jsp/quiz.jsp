<%@ include file="common/header.jspf" %>

<h1 class="mb-4">${testName}</h1>
<hr>

<form action="/submit-test" method="post" enctype="application/x-www-form-urlencoded" modelAttribute="quizForm">
     <input type="hidden" name="testName" value="${testName}" />
    <c:forEach items="${quizList}" var="question" varStatus="questionNumber">
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
        </div>
    </c:forEach>

    <button type="submit" class="btn btn-primary">Submit Test</button>
</form>

<%@ include file="common/footer.jspf" %>
