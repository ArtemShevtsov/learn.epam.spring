<style>
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

#login-box {
	width: 300px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}
</style>

<div id="login-box">
  <h2>Please, Log In</h2>

  <#if error??>
    <div class="error">${error}</div>
  </#if>
  <#if msg??>
    <div class="msg">${msg}</div>
   </#if>

  <form name='loginForm' action="j_spring_security_check" method='POST'>
    <table>
      <tr>
        <td>User:</td>
        <td><input type='text' name='user' value=''></td>
      </tr>
      <tr>
        <td>Password:</td>
        <td><input type='password' name='pass' /></td>
      </tr>
      <tr>
        <td><input type='checkbox' name='remember-me' /> Remember Me</td>
      </tr>
      <tr>
        <td colspan='2'>
          <input name="submit" type="submit" value="Log In" />
         </td>
      </tr>
    </table>
  </form>
</div>