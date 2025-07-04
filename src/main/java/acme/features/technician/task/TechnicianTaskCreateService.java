/*
 * TechnicianTaskCreateService.java
 *
 * Copyright (C) 2012-2025 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.technician.task;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.components.models.Dataset;
import acme.client.components.views.SelectChoices;
import acme.client.services.AbstractGuiService;
import acme.client.services.GuiService;
import acme.entities.maintenanceRecords.Involves;
import acme.entities.maintenanceRecords.MaintenanceRecord;
import acme.entities.task.Task;
import acme.entities.task.TaskType;
import acme.realms.Technician;

@GuiService
public class TechnicianTaskCreateService extends AbstractGuiService<Technician, Task> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private TechnicianTaskRepository repository;

	// AbstractGuiService interface -------------------------------------------


	@Override
	public void authorise() {
		boolean status = false;
		Integer maintenanceRecordId = null;
		MaintenanceRecord maintenanceRecord;
		Technician technician;

		if (super.getRequest().hasData("maintenanceRecordId")) {
			maintenanceRecordId = super.getRequest().getData("maintenanceRecordId", Integer.class);

			if (maintenanceRecordId != null) {
				maintenanceRecord = this.repository.findMaintenanceRecordById(maintenanceRecordId);

				if (maintenanceRecord != null) {
					technician = (Technician) super.getRequest().getPrincipal().getActiveRealm();
					status = maintenanceRecord.isDraftMode() && technician.equals(maintenanceRecord.getTechnician());
				}
			}
		} else
			status = true;

		super.getResponse().setAuthorised(status);
	}

	@Override
	public void load() {
		Task task;
		Technician technician;

		technician = (Technician) super.getRequest().getPrincipal().getActiveRealm();

		task = new Task();
		task.setDraftMode(true);
		task.setTechnician(technician);

		super.getBuffer().addData(task);
	}

	@Override
	public void bind(final Task task) {

		super.bindObject(task, "type", "description", "priority", "estimatedDuration");
	}

	@Override
	public void validate(final Task task) {
		;
	}

	@Override
	public void perform(final Task task) {
		Integer maintenanceRecordId;
		MaintenanceRecord maintenanceRecord;
		Involves involves;

		maintenanceRecordId = super.getRequest().hasData("maintenanceRecordId") ?//
			super.getRequest().getData("maintenanceRecordId", Integer.class) : null;

		this.repository.save(task);

		if (maintenanceRecordId != null) {

			involves = new Involves();
			maintenanceRecord = this.repository.findMaintenanceRecordById(maintenanceRecordId);

			involves.setTask(task);
			involves.setMaintenanceRecord(maintenanceRecord);

			this.repository.save(involves);
		}
	}

	@Override
	public void unbind(final Task task) {
		SelectChoices choices;
		Dataset dataset;

		choices = SelectChoices.from(TaskType.class, task.getType());

		dataset = super.unbindObject(task, "type", "description", "priority", "estimatedDuration", "draftMode");
		dataset.put("technician", task.getTechnician().getIdentity().getFullName());
		dataset.put("type", choices.getSelected().getKey());
		dataset.put("types", choices);
		if (super.getRequest().hasData("maintenanceRecordId"))
			dataset.put("maintenanceRecordId", super.getRequest().getData("maintenanceRecordId", Integer.class));

		super.getResponse().addData(dataset);
	}

}
