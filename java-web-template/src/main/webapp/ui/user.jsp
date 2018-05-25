<!DOCTYPE html>
<%@ page contentType="text/html"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionBean.language}" />
<fmt:setBundle basename="com.example.myapp.crud.crud" />

<t:template>
	<jsp:attribute name="head_area">
		<link rel="stylesheet"
			href="https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css" />
		<link rel="stylesheet"
			href="https://cdn.datatables.net/buttons/1.1.2/css/buttons.dataTables.min.css" />
		<link rel="stylesheet"
			href="https://cdn.datatables.net/select/1.1.2/css/select.dataTables.min.css" />
		<link rel="stylesheet"
			href="https://cdn.datatables.net/responsive/2.0.2/css/responsive.dataTables.min.css" />
	</jsp:attribute>

	<jsp:attribute name="body_area">
          <div class="">
            <div class="page-title">
              <div class="title_left">
                <h3>
						<fmt:message key="title.User" />
					</h3>
              </div>

              <div class="title_right">
                <div
						class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
                  <div class="input-group">
                    <input type="text" class="form-control"
								placeholder="Search for...">
                    <span class="input-group-btn">
                      <button class="btn btn-default" type="button">Go!</button>
                    </span>
                  </div>
                </div>
              </div>
            </div>

            <div class="clearfix"></div>

            <div class="row">
              <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>
								<fmt:message key="title.User" />
							</h2>
                    <ul class="nav navbar-right panel_toolbox">
                      <li><a class="collapse-link"><i
										class="fa fa-chevron-up"></i></a>
                      </li>
                    </ul>
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
					<table id="userTable" class="table table-striped table-bordered dt-responsive nowrap">
						<thead>
							<tr class="text-center">
								<th><fmt:message key="userTable.name" /></th>
								<th><fmt:message key="userTable.surname" /></th>
								<th><fmt:message key="userTable.username" /></th>
							</tr>
						</thead>
						
					</table>
                  </div>
                </div>
              </div>
            </div>
          </div>
    
    </jsp:attribute>

	<jsp:attribute name="footer_area">
	
	<script>
		var entity = 'User';
	</script>
    
	<script
			src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
	<script
			src="https://cdn.datatables.net/buttons/1.1.2/js/dataTables.buttons.min.js"></script>
	<script
			src="https://cdn.datatables.net/select/1.1.2/js/dataTables.select.min.js"></script>
	<script
			src="https://cdn.datatables.net/responsive/2.0.2/js/dataTables.responsive.min.js"></script>
	<script
			src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-growl/1.0.0/jquery.bootstrap-growl.min.js"></script>
	
	<script src="js/altEditor/dataTables.altEditor.free.js"></script>
	<script src="js/crud/user.js"></script>
	</jsp:attribute>
</t:template>