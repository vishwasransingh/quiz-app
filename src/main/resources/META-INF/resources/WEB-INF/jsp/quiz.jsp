<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sample Quiz</title>
    <link href="/webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>

<div class="container mt-5">
    <h1 class="mb-4">Sample Quiz</h1>
    <hr>

    <form method="post">
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
</div>

<script src="/webjars/jquery/3.7.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>

</body>
</html>
