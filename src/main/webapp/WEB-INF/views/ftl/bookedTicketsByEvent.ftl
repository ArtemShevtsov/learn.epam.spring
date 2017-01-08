<div id="header">
    <#if tickets?has_content>
        <h2>Please, see bellow list of booked tickets for event ${event.name}:</h2>
    <#else>
        <h2>There is no booked tickets for event ${event.name}</h2>
    </#if>
</div>
<hr>

<div>
    <#if tickets?has_content>
        <#list tickets>
            <ul>
                <#items as t>
                    <li>
                       User: ${t.user.fullName}; Auditory: ${t.eventAuditorium.auditorium.name}; Seat: ${t.seat}
                    </li>
                </#items>
            </ul>
        </#list>
    </#if>
</div>