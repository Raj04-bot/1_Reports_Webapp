
<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

 
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>JSP Testing</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>

<div class="container">
    <h3 class="pb-3 pt-3">Report App</h3>

    <form:form action="search" modelAttribute="search" method="POST">
    <div class="row mb-3">
        <div class="col-md-2">
            <label for="planName">Plan Name</label>
        </div>
            <div class="col-md-4">
            <form:select path="planName" class="form-control">
                <form:option value="">-Select-</form:option>
                <form:options items="${names}" />
            </form:select>
        </div>

             <div class="col-md-2">
            <label for="planStatus">Plan Status</label>
        </div>
        <div class="col-md-4">
            <form:select path="planStatus" class="form-control">
                <form:option value="">-Select-</form:option>
                <form:options items="${status}" />
            </form:select>
        </div>
    </div>

         <div class="row mb-3">
        <div class="col-md-2">
            <label>Gender</label>
        </div>
             <div class="col-md-4">
            <div class="form-check">
                <form:radiobutton path="gender" value="male" class="form-check-input" id="male"/>
                <label class="form-check-label" for="male">Male</label>
            </div>
            <div class="form-check">
                <form:radiobutton path="gender" value="female" class="form-check-input" id="female"/>
                <label class="form-check-label" for="female">Female</label>
            </div>
        </div>
    </div>

         <div class="row mb-3">
        <div class="col-md-2">
            <label for="startDate">Start Date</label>
        </div>
        <div class="col-md-4">
            <form:input path="startDate" type="date" class="form-control" id="startDate"/>
        </div>

        <div class="col-md-2">
            <label for="endDate">End Date</label>
        </div>
        <div class="col-md-4">
            <form:input path="endDate" type="date" class="form-control" id="endDate"/>
        </div>
    </div>

        <div class="row mb-3">
            <div class="col-md-12">
                <input type="submit" value="Search" class="btn btn-primary"/>
            </div>
        </div>
    </form:form>

    <hr/>
    <table class="table table-striped table-hover">
    	<th>
    		<tr>id</tr>
    		<tr>Gender</tr>
    		<tr>Plan Name</tr>
    		<tr>Plan Status</tr>
    		<tr>Start Date</tr>
    		<tr>End Date</tr>
    	</th>
    </table>
    <tbody>
    	<c:forEach items="${plans }" var = "plan">
    	<tr>
    		<td>${index.count}</td>
    		<td>${plan.gender}</td>
    		<td>${plan.planName}</td>
    		<td>${plan.planStatus}</td>
    		<td>${plan.planStartDate }</td>
    		<td>${plan.planEndDate }</td>
    	</tr>
    	</c:forEach>
    </tbody>

    <div class="mt-3">
        Export: 
        <a href="excel" class="btn btn-outline-primary btn-sm">Excel</a>
        <a href="pdf" class="btn btn-outline-primary btn-sm">PDF</a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
