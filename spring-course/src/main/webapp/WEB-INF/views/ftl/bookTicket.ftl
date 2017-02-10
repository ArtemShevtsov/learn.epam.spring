<style>
    label{
        min-width: 300px;
        display: inline-block;
    }
    select{
        min-width: 200px;
    }
    div.line{
        padding-bottom: 10px;
    }
</style>
<div id="header" style="display: inline-block; width: 100%">
    <h4 style="float: right;"><a href="/j_spring_security_logout">Log Out</a></h4>
</div>
<hr>
<form method="post" action="/book-ticket">
    <div class="line">
        <label for="user">Select User you want to book ticket for</label>
        <#list users>
            <select required name="userId" id="user">
                <option value="" selected>Select Value</option>
                <#items as u>
                    <option value="${u.id}">${u.fullName}</option>
                </#items>
            </select>
        </#list>
    </div>

    <div class="line">
        <label for="event">Select Event</label>
        <#list events>
            <select required name="eventId" id="event">
                <option value="" selected>Select Value</option>
                <#items as e>
                    <option value="${e.id}">${e.name}</option>
                </#items>
            </select>
        </#list>
    </div>

    <div class="line">
        <label for="auditory">Select Auditorium</label>
        <#list auditoriums>
            <select required name="auditoriumId" id="auditory">
                <option value="" selected>Select Value</option>
                <#items as a>
                    <option value="${a.id}">${a.name}</option>
                </#items>
            </select>
        </#list>
    </div>

    <div class="line">
        <label for="seat">Insert Seat Number</label>
        <input id="seat" name="seat" type="number" min="1"/>
    </div>

    <div class="line">
        <label for="date">Insert session Date and Time ("DD/MM/YYYY hh:mm")</label>
        <input id="date" name="date" />
    </div>

    <input type="submit">
</form>