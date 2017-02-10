<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-sm-offset-1 col-sm-10">

    <legend>
        Users By ${attribute} ${attributeName}:
    </legend>

    <div>
        <table id="dataTable" class="table table-striped table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Birthday</th>
                    <th>Email</th>
                </tr>
            <thead>
            <tbody>
                <c:forEach var="u" items="${usersModel}">
                    <tr>
                        <td>${u.id}</td>
                        <td>${u.firstName}</td>
                        <td>${u.lastName}</td>
                        <td>${u.birthDay}</td>
                        <td>${u.email}</td>
                    <tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>