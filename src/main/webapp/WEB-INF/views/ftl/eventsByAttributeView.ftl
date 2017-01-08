<div id="header">
    <#if attribute??>
        <h2>Event(s) By ${attribute} ${attributeName}:</h2>
    <#else>
        <h2>All Events:</h2>
    </#if>
</div>
<hr>

<div>
    <#if eventsModel?has_content>
        <#list eventsModel>
            <table id="dataTable" class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Base Price</th>
                        <th>Rating</th>
                        <th>Minutes Length</th>
                    </tr>
                <thead>
                <tbody>
                    <#items as e>
                        <tr>
                           <td>${e.id}</td>
                           <td>${e.name}</td>
                           <td>${e.basePrice}</td>
                           <td>${e.rating.name()}</td>
                           <td>${e.minutesLength}</td>
                        </tr>
                    </#items>
                </tbody>
            </table>
        </#list>
    <#else>
        <h4>Sorry, there no Event(s) found!</h4>
    </#if>
</div>