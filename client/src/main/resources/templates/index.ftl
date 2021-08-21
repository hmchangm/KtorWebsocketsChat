<#-- @ftlvariable name="entries" type="kotlin.collections.List<com.jetbrains.handson.website.BlogEntry>" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Kotlin Client</title>
</head>
<body style="text-align: center; font-family: sans-serif">
<h1>ChatBot Client</h1>
<hr>
<#list entries as item>
    <div>
        <p>${item.body}</p>
    </div>
</#list>
<hr>
<div>
    <h3>Entry your message!</h3>
    <form action="/submit" method="post">
        <textarea name="body"></textarea>
        <br>
        <input type="submit">
    </form>
</div>
</body>
</html>