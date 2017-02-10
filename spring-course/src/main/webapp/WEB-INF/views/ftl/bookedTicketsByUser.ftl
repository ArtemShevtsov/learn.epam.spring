<div id="header" style="display: inline-block; width: 100%">
    <#if tickets?has_content>
        <h2 style="float: left;">Please, see bellow list of booked tickets for user ${user.fullName}:</h2>
    <#else>
        <h2 style="float: left;">There is no booked tickets for user ${user.fullName}</h2>
    </#if>
    <h4 style="float: right;"><a href="/j_spring_security_logout">Log Out</a></h4>
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