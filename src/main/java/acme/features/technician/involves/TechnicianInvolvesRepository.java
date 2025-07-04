
package acme.features.technician.involves;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.maintenanceRecords.Involves;
import acme.entities.maintenanceRecords.MaintenanceRecord;
import acme.entities.task.Task;
import acme.realms.Technician;

@Repository
public interface TechnicianInvolvesRepository extends AbstractRepository {

	@Query("select m from MaintenanceRecord m where m.id = :maintenanceRecordId")
	MaintenanceRecord findMaintenanceRecordById(int maintenanceRecordId);

	@Query("select t from Task t where t.id = :taskId")
	Task findTaskById(int taskId);

	@Query("select t from Task t where t not in (select i.task from Involves i where i.maintenanceRecord = :maintenanceRecord) and (t.draftMode = false or t.technician = :technician)")
	Collection<Task> findValidTasksToLink(MaintenanceRecord maintenanceRecord, Technician technician);

	@Query("select t from Task t where t in (select i.task from Involves i where i.maintenanceRecord = :maintenanceRecord)")
	Collection<Task> findValidTasksToUnlink(MaintenanceRecord maintenanceRecord);

	@Query("select i.task from Involves i where i.maintenanceRecord = :maintenanceRecord")
	Collection<Involves> findInvolvesByMaintenanceRecord(MaintenanceRecord maintenanceRecord);

	@Query("select i from Involves i where i.maintenanceRecord = :maintenanceRecord and i.task = :task")
	Involves findInvolvesByMaintenanceRecordAndTask(MaintenanceRecord maintenanceRecord, Task task);

}
