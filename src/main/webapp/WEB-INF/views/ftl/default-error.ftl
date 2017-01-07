<div id="header">
  <h2>Exception: ${ex.class?string} (${ex.message})</h2>
</div>
<hr>
<#list ex.stackTrace>
    <ul>
        <#items as traceEl>
            <li>${traceEl}</li>
        </#items>
    </ul>
</#list>