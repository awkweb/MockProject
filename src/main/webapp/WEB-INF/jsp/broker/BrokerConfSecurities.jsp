
<br>
<br>
<br>
<br>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<form:form class="form-horizontal" role="form">
  <div class="form-group">
    <form:label path="maxPriceSpread" class=" col-sm-4" for="maxPriceSpread">Maximum Price Spread Percentage :</form:label>
    <div class="col-sm-2">
      <form:input path="maxPriceSpread" type="number" class="form-control" id="maxPriceSpread"></form:input>
    </div>
  </div>
  <div class="form-group">
    <form:label path="maxExecs" class=" col-sm-4 " for="maxExecs">Maximum number of executions per order :</form:label>
    <div class="col-sm-2"> 
      <form:input path="maxExecs" type="text" class="form-control" id="maxExecs"></form:input>
    </div>
  </div>
  <div class="form-group">
    <form:label path="maxInterval" class=" col-sm-4" for="maxInterval">Maximum interval between executions(seconds) :</form:label>
    <div class="col-sm-2"> 
      <form:input path="maxInterval" type="text" class="form-control" id="maxInterval"></form:input>
    </div>
  </div>
   <div class="form-group">
    <form:label path="fullyExecs" class="col-sm-4" for="fullyExecs">Percentage of fully executed orders :</form:label>
    <div class="col-sm-2"> 
      <form:input path="fullyExecs" type="number" class="form-control" id="fullyExecs" ></form:input>
    </div>
  </div>
  <br>
  <div class="form-group"> 
    <div class="col-sm-offset-4 col-sm-10">
      <button type="submit" class="btn btn-default">Submit</button>
       <button type="submit" class="btn btn-default">Cancel</button>
    </div>
  </div>
</form:form>