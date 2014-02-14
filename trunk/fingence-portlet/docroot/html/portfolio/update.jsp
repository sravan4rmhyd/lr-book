<%@page import="com.fingence.slayer.model.impl.PortfolioImpl"%>

<%@ include file="/html/portfolio/init.jsp"%>

<portlet:renderURL var="defaultViewURL"/>

<portlet:actionURL name="savePortfolio" var="savePortfolioURL"/>

<%
	long portfolioId = ParamUtil.getLong(request, "portfolioId");

	Portfolio portfolio = new PortfolioImpl();
	if (portfolioId > 0l) {
		portfolio = PortfolioLocalServiceUtil.fetchPortfolio(portfolioId);
	}
%>

<c:if test="<%= (portfolioId == 0l) %>">
	<liferay-ui:header title="add-portfolio"/>
</c:if>

<aui:form action="<%= savePortfolioURL %>" enctype="multipart/form-data">
	<aui:row>
		<aui:column>
			<aui:input name="portfolioName" required="true" autoFocus="true" value="<%= portfolio.getPortfolioName() %>"/>
		</aui:column>
		
		<aui:column cssClass="display-down">
			<aui:input type="checkbox" name="trial" label="this-is-a-trial" value="<%= portfolio.isTrial() %>"/>
		</aui:column>		
	</aui:row>

	<aui:row>
		<aui:column>
			<aui:select name="investorId" label="investor" required="true" showEmptyOption="true">
				<%
					List<User> users = BridgeServiceUtil.getUsersByTargetType(userId, IConstants.USER_TYPE_INVESTOR);
					for (User _user: users) {
						%><aui:option value="<%= _user.getUserId() %>" label="<%= _user.getFullName() %>" selected="<%= (_user.getUserId() == portfolio.getInvestorId()) %>" /><% 
					}
				%>
			</aui:select>			
		</aui:column>
		
		<aui:column>
			<aui:select name="wealthAdvisorId" label="wealth-advisor" required="true" showEmptyOption="true">
				<%
					List<User> users = BridgeServiceUtil.getUsersByTargetType(userId, IConstants.USER_TYPE_WEALTH_ADVISOR);
					for (User _user: users) {
						%><aui:option value="<%= _user.getUserId() %>" label="<%= _user.getFullName() %>" selected="<%= (_user.getUserId() == portfolio.getWealthAdvisorId()) %>" /><% 
					}
				%>
			</aui:select>			
		</aui:column>		
	</aui:row>
	
	<aui:row>
		<aui:column>
			<aui:select name="relationshipManagerId" label="relationship-manager" required="true" showEmptyOption="true">
				<%
					List<User> users = BridgeServiceUtil.getUsersByTargetType(userId, IConstants.USER_TYPE_REL_MANAGER);
					for (User _user: users) {
						%><aui:option value="<%= _user.getUserId() %>" label="<%= _user.getFullName() %>" selected="<%= (_user.getUserId() == portfolio.getRelationshipManagerId()) %>" /><% 
					}
				%>
			</aui:select>			
		</aui:column>
		
		<aui:column>
			<aui:select name="institutionId" label="institution" required="true" showEmptyOption="true">
				<%
					List<Organization> institutions = BridgeServiceUtil.getInstitutions();
					for (Organization institution: institutions) {
						%><aui:option value="<%= institution.getOrganizationId() %>" label="<%= institution.getName() %>" selected="<%= (institution.getOrganizationId() == portfolio.getInstitutionId()) %>" /><% 
					}
				%>
			</aui:select>
		</aui:column>		
	</aui:row>
	
	<c:choose>
		<c:when test="<%= (portfolioId == 0l) %>">
			<aui:row>
				<aui:column>
					<aui:input type="file" name="excelFile" label="portfolio-assets"/>
				</aui:column>		
			</aui:row>
			
			<aui:button-row>
				<aui:button type="submit" />
				<aui:a href="<%= defaultViewURL %>" label="cancel"/>
			</aui:button-row>		
		</c:when>	
		
		<c:otherwise>
			<aui:button onclick="javascript:updateInfo();" value="save"/>
		</c:otherwise>
	</c:choose>
</aui:form>

<c:if test="<%= (portfolioId > 0l) %>">
	<aui:script>
		function updateInfo() {
        	var frm = document.<portlet:namespace/>fm;
        	        	
			Liferay.Service(
			  	'/fingence-portlet.portfolio/update-portfolio',
			  	{
			    	portfolioId: '<%= portfolioId %>',
			    	userId: '<%= userId %>',
			    	portfolioName: frm.<portlet:namespace/>portfolioName.value,
			    	investorId: frm.<portlet:namespace/>investorId.value,
			    	institutionId: frm.<portlet:namespace/>institutionId.value,
			    	wealthAdvisorId: frm.<portlet:namespace/>wealthAdvisorId.value,
			    	trial: frm.<portlet:namespace/>trial.value,
			    	relationshipManagerId: frm.<portlet:namespace/>relationshipManagerId.value,
			    	social: true
			  	},
			  	function(obj) {
			    	Liferay.Util.getWindow('<portlet:namespace/>editPortfolioPopup').destroy();
	                Liferay.Util.getOpener().<portlet:namespace/>reloadPortlet();
			  	}
			);
        }	 
	</aui:script>
</c:if>