<div id="header">
  <h2>Exception: ${ex.class?string}
    <#if ex.message??>
      (${ex.message})
    </#if>
  </h2>
</div>
<hr>
<#list ex.stackTrace>
    <ul>
        <#items as traceEl>
            <li>${traceEl}</li>
        </#items>
    </ul>
</#list>