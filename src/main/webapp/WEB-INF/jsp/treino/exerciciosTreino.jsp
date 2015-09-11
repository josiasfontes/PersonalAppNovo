<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>

<meta charset="UTF-8"></meta>
<title>Personal App</title>
<meta
	content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
	name='viewport'></meta>
<!-- Bootstrap 3.3.4 -->
<link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<!-- FontAwesome 4.3.0 -->
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<!-- Ionicons 2.0.0 -->
<link
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css"
	rel="stylesheet" type="text/css" />
<!-- Theme style -->
<link href="/resources/dist/css/AdminLTE.min.css" rel="stylesheet"
	type="text/css" />
<!-- AdminLTE Skins. Choose a skin from the css/skins 
         folder instead of downloading all of them to reduce the load. -->
<link href="/resources/dist/css/skins/_all-skins.min.css"
	rel="stylesheet" type="text/css" />
<!-- iCheck -->
<link href="/resources/plugins/iCheck/flat/blue.css" rel="stylesheet"
	type="text/css" />
<!-- Morris chart -->
<link href="/resources/plugins/morris/morris.css" rel="stylesheet"
	type="text/css" />
<!-- jvectormap -->
<link href="/resources/plugins/jvectormap/jquery-jvectormap-1.2.2.css"
	rel="stylesheet" type="text/css" />
<!-- Date Picker -->
<link href="/resources/plugins/datepicker/datepicker3.css"
	rel="stylesheet" type="text/css" />
<!-- Daterange picker -->
<link href="/resources/plugins/daterangepicker/daterangepicker-bs3.css"
	rel="stylesheet" type="text/css" />
<!-- bootstrap wysihtml5 - text editor -->
<link
	href="/resources/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css"
	rel="stylesheet" type="text/css" />


</head>
<body class="skin-blue sidebar-mini">
	<div class="wrapper">

		<header class="main-header">
			<!-- Logo -->
			<a href="index.xhtml" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
				<span class="logo-mini"><b>P</b>App</span> <!-- logo for regular state and mobile devices -->
				<span class="logo-lg"><b>Personal</b>App</span>
			</a>
			<!-- Header Navbar: style can be found in header.less -->
			<nav class="navbar navbar-static-top" role="navigation">
				<!-- Sidebar toggle button-->
				<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
					role="button"> <span class="sr-only">Toggle navigation</span>
				</a>
				<div class="navbar-custom-menu">
					<ul class="nav navbar-nav">

						<!-- User Account: style can be found in dropdown.less -->
						<li class="dropdown user user-menu"><a href="#"
							class="dropdown-toggle" data-toggle="dropdown"> <img
								src="/resources/dist/img/user2-160x160.jpg" class="user-image"
								alt="User Image" /> <span class="hidden-xs">Nome do
									Usuário</span>
						</a>
							<ul class="dropdown-menu">
								<!-- User image -->
								<li class="user-header"><img
									src="/resources/dist/img/user2-160x160.jpg" class="img-circle"
									alt="User Image" />
									<p>
										Nome do Usuário <small>Administrador</small>
									</p></li>
								<!-- Menu Footer-->
								<li class="user-footer">
									<div class="pull-right">
										<a href="login.xhtml" class="btn btn-default btn-flat">Sair</a>
									</div>
								</li>
							</ul></li>
					</ul>
				</div>
			</nav>
		</header>
		<!-- Left side column. contains the logo and sidebar -->
		<aside class="main-sidebar">
			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">
				<!-- Sidebar user panel -->
				<div class="user-panel">
					<div class="pull-left image">
						<img src="/resources/dist/img/user2-160x160.jpg"
							class="img-circle" alt="User Image" />
					</div>
					<div class="pull-left info">
						<p>Nome do Usuário</p>
					</div>
				</div>

				<!-- sidebar menu: : style can be found in sidebar.less -->
				<ul class="sidebar-menu">
					<li class="header">MENU</li>
					<li class="active treeview"><a href="/academia/home/"> <i
							class="fa fa-fw fa-home"></i> <span>Home</span>
					</a></li>
					<li><a href="/pessoa/listar"> <i
							class="fa fa-fw fa-user-plus"></i> <span>Alunos</span>
					</a></li>
					<li><a href="/exercicio/criar"> <i
							class="fa fa-fw fa-user-plus"></i> <span>Exercicio</span>
					</a></li>
				</ul>
			</section>
			<!-- /.sidebar -->
		</aside>
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					Treino: <b>${treino.nome}</b>
				</h1>
				</br>
				<h1>
					<b>Exercicios</b>
				</h1>
			</section>

			<section class="content">
				<div class="row">
					<div class="col-md-12">
						<div class="box box-primary">
							<div class="box-body">
								<c:if test="${empty treinos}"></c:if>
								<form:form role="form">
									<table class="table">
										<thead>
											<tr>
												<th>Id Treino</th>
												<th>Nome</th>
												<th>Editar</th>
												<th>Remover</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${treino.exercicios}" var="treino">
												<tr>
													<td>${treino.idExercicio}</td>
													<td>${treino.nome}</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</form:form>
							</div>
						</div>
					</div>
				</div>
			</section>

			<br> <br> <br> <br> <br> <br> <br>
			<br> <br> <br> <br> <br> <br> <br>
			<footer class="main-footer">
				<div class="pull-right hidden-xs">
					<b>Version</b> 2.0
				</div>
				<strong>Copyright 2015 <a
					href="http://www.besche.com.br/site">Besche Soluções em
						Tecnologia LTDA</a>.
				</strong> Todos os direitos reservados.
			</footer>


		</div>
		<!-- ./wrapper -->

		<!-- jQuery 2.1.4 -->
		<script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
		<!-- jQuery UI 1.11.2 -->
		<script src="http://code.jquery.com/ui/1.11.2/jquery-ui.min.js"
			type="text/javascript"></script>
		<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
		<script>
			$.widget.bridge('uibutton', $.ui.button);
		</script>
		<!-- Bootstrap 3.3.2 JS -->
		<script src="/resources/bootstrap/js/bootstrap.min.js"
			type="text/javascript"></script>
		<!-- Morris.js charts -->
		<script
			src="http://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
		<script src="/resources/plugins/morris/morris.min.js"
			type="text/javascript"></script>
		<!-- Sparkline -->
		<script src="/resources/plugins/sparkline/jquery.sparkline.min.js"
			type="text/javascript"></script>
		<!-- jvectormap -->
		<script
			src="/resources/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"
			type="text/javascript"></script>
		<script
			src="/resources/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"
			type="text/javascript"></script>
		<!-- jQuery Knob Chart -->
		<script src="/resources/plugins/knob/jquery.knob.js"
			type="text/javascript"></script>
		<!-- daterangepicker -->
		<script
			src="/resources/https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.2/moment.min.js"
			type="text/javascript"></script>
		<script src="/resources/plugins/daterangepicker/daterangepicker.js"
			type="text/javascript"></script>
		<!-- datepicker -->
		<script src="/resources/plugins/datepicker/bootstrap-datepicker.js"
			type="text/javascript"></script>
		<!-- Bootstrap WYSIHTML5 -->
		<script
			src="/resources/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"
			type="text/javascript"></script>
		<!-- Slimscroll -->
		<script src="/resources/plugins/slimScroll/jquery.slimscroll.min.js"
			type="text/javascript"></script>
		<!-- FastClick -->
		<script src='/resources/plugins/fastclick/fastclick.min.js'></script>
		<!-- AdminLTE App -->
		<script src="/resources/dist/js/app.min.js" type="text/javascript"></script>

		<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
		<script src="/resources/dist/js/pages/dashboard.js"
			type="text/javascript"></script>

		<!-- AdminLTE for demo purposes -->
		<script src="/resources/dist/js/demo.js" type="text/javascript"></script>

</body>

</html>