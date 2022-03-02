<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>To-Do</title>
</head>
<body>
	<div id="add">
		<h1>Add To-Do Item</h1>
		<form action="ToDoController" method="GET">
			<table>
				<tbody>
					<tr>
						<td>Task:</td>
						<td><input type="text" name="task" value="" size="50" /></td>
					</tr>
				</tbody>
			</table>
			<input type="submit" name="addItem" value="Add" />
		</form>
		<form action="ToDoController" method="POST">
			<input type="submit" name="showItem" value="Show">
		</form>
	</div>
</body>
</html>