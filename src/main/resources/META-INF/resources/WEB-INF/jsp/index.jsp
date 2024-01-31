<%@ include file="common/header.jspf" %>

<h1 class="mt-5">Quiz-Application Home</h1>
<hr>

<form action="/get-quiz" method="get">
    <button type="submit" class="btn btn-success mt-3" name="testName" value="GK_Test_1">Sample Test1</button>
</form>

<form action="/get-quiz" method="get">
    <button type="submit" class="btn btn-warning mt-3" name="testName" value="sampleTest2">Sample Test2</button>
</form>

<form action="/get-quiz" method="get">
    <button type="submit" class="btn btn-warning mt-3" name="testName" value="sampleTest3">Sample Test3</button>
</form>

<form action="/get-quiz" method="get">
    <button type="submit" class="btn btn-warning mt-3" name="testName" value="sampleTest4">Sample Test4</button>
</form>

<%@ include file="common/footer.jspf" %>
