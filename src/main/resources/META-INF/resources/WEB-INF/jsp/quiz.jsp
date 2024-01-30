<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Quiz</title>
</head>
<body>

	<h1>Sample Quiz</h1>
	<hr>
	
	<form method="post">
		<c:forEach items="${quizList}" var="question" varStatus="questionNumber">
			<br>
			<p>${questionNumber.index + 1}. ${question.question}</p>
			
			<p><input type="radio" name="A" value="A"/> A. ${question.options[0]}</p>
			<p><input type="radio" name="A" value="A"/> B. ${question.options[1]}</p>
			<p><input type="radio" name="A" value="A"/> C. ${question.options[2]}</p>
			<p><input type="radio" name="A" value="A"/> D. ${question.options[3]}</p>

		</c:forEach>
		
		<input type="submit" value="Submit Test">
	</form>
		
	
</body>
</html>