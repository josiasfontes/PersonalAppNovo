<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layout"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
    <meta charset="UTF-8"></meta>
    <title>Personal App | Entrar</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'></meta>
    <!-- Bootstrap 3.3.4 -->
    <link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- Font Awesome Icons -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Theme style -->
    <link href="/resources/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
    <!-- iCheck -->
    <link href="/resources/plugins/iCheck/square/blue.css" rel="stylesheet" type="text/css" />

  </head>
  <body class="login-page">
    <div class="login-box">
      <div class="login-logo">
        <b>Personal</b>App</a>
      </div><!-- /.login-logo -->
      <div class="login-box-body">
        <p class="login-box-msg">Entre para iniciar sua sessão</p>
        <!-- <form action="index.xhtml" method="post"> -->

	<aside id="main" class="col-md-12">
		<form class="form-horizontal" name='f' action='/login' method='POST'>
			<div class="form-group">
			<div class="form-group has-feedback">
				<label class="col-sm-2 control-label">Email</label>
				<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
     	  </div>
				<div class="col-sm-10">
					<input type="text" name="username" class="form-control" />
				</div>
			</div>

			<div class="form-group">
			<div class="form-group has-feedback">
				<label class="col-sm-2 control-label">Senha</label>
				<span class="glyphicon glyphicon-lock form-control-feedback"></span>
          </div>
				<div class="col-sm-10">
					<input type="password" name="password" class="form-control" />
				</div>
			</div>
			<div class="row">
            <div class="col-xs-8">    
              <div class="checkbox icheck">
              <br>
                <label>
                  <h4><a class="nav nav-tabs nav-justified" href="/academia/criar">Cadastrar-se</a></p></h4>
                </label>
              </div>                        
            </div><!-- /.col -->
            <br>
			<div class="form-group">
				<div class="col-xs-4">
					<input type="submit" value="Entrar" class="btn btn-success"></input>
				</div>
			</div>
		</form>
		
		<%-- <form class="form-horizontal" action="/connect/facebook" method="POST">
			<input type="hidden" name="scope" value="read_stream"></input>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-success">Connect to
						Facebook</button>
				</div>
			</div>
		</form> --%>
		
			</aside>
		 <div class="social-auth-links text-center">
		 <div class="form-group">
			
				<h4><a class="nav nav-tabs nav-justified" href=""></a></p></h4>
			
		</div>
        </div><!-- /.social-auth-links -->
      </div><!-- /.login-box-body -->
    </div><!-- /.login-box -->

    <!-- jQuery 2.1.4 -->
    <script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.2 JS -->
    <script src="/resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- iCheck -->
    <script src="/resources/plugins/iCheck/icheck.min.js" type="text/javascript"></script>
    <script>
      $(function () {
        $('input').iCheck({
          checkboxClass: 'icheckbox_square-blue',
          radioClass: 'iradio_square-blue',
          increaseArea: '20%' // optional
        });
      });
    </script>
  </body>