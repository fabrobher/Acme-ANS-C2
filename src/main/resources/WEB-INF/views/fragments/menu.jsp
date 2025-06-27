<%--
- menu.jsp
-
- Copyright (C) 2012-2025 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:menu-bar>
	<acme:menu-left>
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-agus" action="https://donpollocarmona.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-mig" action="https://pointerpointer.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-juanma" action="https://theuselessweb.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-fabrobher" action="https://restaurantebilios.es/"/>
			<acme:menu-suboption code="master.menu.anonymous.favourite-link-ra" action="https://www.youtube.com/channel/UCBCD1nIuiH-d10_l6Q-8rRg"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator" access="hasRealm('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.list-user-accounts" action="/administrator/user-account/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.populate-db-initial" action="/administrator/system/populate-initial"/>
			<acme:menu-suboption code="master.menu.administrator.populate-db-sample" action="/administrator/system/populate-sample"/>			
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.administrator.shut-system-down" action="/administrator/system/shut-down"/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.any">
			<acme:menu-suboption code="master.menu.any.list-flights" action="/any/flight/list"/>
			<acme:menu-separator/>
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.manager" access="hasRealm('Manager')">
			<acme:menu-suboption code="master.menu.manager.list-legs" action="/manager/leg/list"/>
			<acme:menu-suboption code="master.menu.manager.list-flights" action="/manager/flight/list"/>
			<acme:menu-separator/>
			<acme:menu-suboption code="master.menu.manager.dashboard" action="/manager/manager-dashboard/show" />
		</acme:menu-option>
		

		<acme:menu-option code="master.menu.member" access="hasRealm('Member')">
			<acme:menu-suboption code="member.assignment.menu.list-planned" action="/member/flight-assignment/list-planned"/>
			<acme:menu-suboption code="member.assignment.menu.list-completed" action="/member/flight-assignment/list-completed"/>
			<acme:menu-suboption code="master.menu.member.list-logs" action="/member/activity-log/list"/>
		</acme:menu-option>

		<acme:menu-option code="master.menu.technician" access="hasRealm('Technician')">
			<acme:menu-suboption code="master.menu.technician.list-maintenance-records" action="/technician/maintenance-record/list"/>
			<acme:menu-suboption code="master.menu.technician.list-tasks" action="/technician/task/list"/>
			
		</acme:menu-option>
		
		<acme:menu-option code="master.menu.assistance-agent" access="hasRealm('AssistanceAgent')">
			<acme:menu-suboption code="master.menu.assistance-agent.list-completed-claims" action="/assistance-agent/claim/list-completed"/>
			<acme:menu-suboption code="master.menu.assistance-agent.list-undergoing-claims" action="/assistance-agent/claim/list-undergoing"/>
		</acme:menu-option>
	</acme:menu-left>

	<acme:menu-right>		
		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.become-manager" action="/authenticated/manager/create" access="!hasRealm('Manager')"/>
			<acme:menu-suboption code="master.menu.user-account.manager-profile" action="/authenticated/manager/update" access="hasRealm('Manager')"/>
		</acme:menu-option>
	</acme:menu-right>
</acme:menu-bar>

