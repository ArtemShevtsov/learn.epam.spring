<div id="header">
    <#if tickets?has_content>
        <h2>Please, see bellow list of booked tickets for user ${user.fullName}:</h2>
    <#else>
        <h2>There is no booked tickets for user ${user.fullName}</h2>
    </#if>
</div>
<hr>

<div>
    <#if tickets?has_content>
        <#list tickets>
            <ul>
                <#items as t>
                    <li>
                       Film: ${t.eventAuditorium.event.name}; Auditory: ${t.eventAuditorium.auditorium.name}; Seat: ${t.seat}
                    </li>
                </#items>
            </ul>
        </#list>
    </#if>
</div>