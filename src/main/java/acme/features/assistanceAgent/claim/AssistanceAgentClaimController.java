
package acme.features.assistanceAgent.claim;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractGuiController;
import acme.client.controllers.GuiController;
import acme.entities.claims.Claim;
import acme.realms.assistanceAgent.AssistanceAgent;

@GuiController
@Controller
public class AssistanceAgentClaimController extends AbstractGuiController<AssistanceAgent, Claim> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AssistanceAgentClaimListUndergoingService	listUndergoingService;

	@Autowired
	private AssistanceAgentClaimListCompletedService	listCompletedService;

	@Autowired
	private AssistanceAgentClaimShowService				showService;

	@Autowired
	private AssistanceAgentClaimCreateService			createService;

	@Autowired
	private AssistanceAgentClaimUpdateService			updateService;

	@Autowired
	private AssistanceAgentClaimDeleteService			deleteService;

	@Autowired
	private AssistanceAgentClaimPublishService			publishService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("create", this.createService);
		super.addBasicCommand("update", this.updateService);
		super.addBasicCommand("delete", this.deleteService);

		super.addCustomCommand("publish", "update", this.publishService);
		super.addCustomCommand("list-completed", "list", this.listCompletedService);
		super.addCustomCommand("list-undergoing", "list", this.listUndergoingService);
	}

}
