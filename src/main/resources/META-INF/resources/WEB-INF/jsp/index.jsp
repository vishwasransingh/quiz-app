<%@ include file="common/header.jspf" %>

<h1 class="mt-5">Quiz-Application Home</h1>
<hr>

<form action="/get-quiz" method="get">
    <label for="subject">Select Subject:</label>
    <select name="subject" id="subject" class="form-select mt-3" required>
        <option value="GK_Test">General Knowledge</option>
        <option value="sampleTest2">Sample Test2</option>
        <option value="sampleTest3">Sample Test3</option>
        <option value="sampleTest4">Sample Test4</option>
    </select>

    <label for="testDifficulty">Select Difficulty:</label>
    <select name="testDifficulty" id="testDifficulty" class="form-select mt-3" required>
        <option value="Easy">Easy</option>
        <option value="Medium">Medium</option>
        <option value="Hard">Hard</option>
    </select>
    
    <label for="">Select Test Size:</label>
    <select name="numberOfQuestions" id="numberOfQuestions" class="form-select mt-3" required>
        <option value="5">5 Questions</option>
        <option value="10">10 Questions</option>
        <option value="20">20 Questions</option>
    </select>

    <button type="submit" class="btn btn-success mt-3">Start Quiz</button>
</form>

<%@ include file="common/footer.jspf" %>