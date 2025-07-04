
package acme.features.technician.task;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.aircraft.Aircraft;
import acme.entities.maintenanceRecords.Involves;
import acme.entities.maintenanceRecords.MaintenanceRecord;
import acme.entities.task.Task;

@Repository
public interface TechnicianTaskRepository extends AbstractRepository {

	@Query("select t from Task t where t.id = :id")
	Task findTaskById(int id);

	@Query("select m from MaintenanceRecord m where m.id = :id")
	MaintenanceRecord findMaintenanceRecordById(int id);

	@Query("select i.task from Involves i where i.maintenanceRecord.id = :masterId")
	Collection<Task> findTasksByMasterId(int masterId);

	@Query("select i.maintenanceRecord from Involves i where i.task.id = :taskId")
	Collection<MaintenanceRecord> findMaintenanceRecordsByTaskId(int taskId);

	@Query("select t from Task t where t.technician.id = :technicianId")
	Collection<Task> findTasksByTechnicianId(int technicianId);

	@Query("select t from Task t where t.draftMode = false")
	Collection<Task> findPublishedTasks();

	@Query("select i from Involves i where i.task.id = :taskId")
	Collection<Involves> findInvolvesByTaskId(int taskId);

	@Query("select a from Aircraft a")
	Collection<Aircraft> findAircrafts();

}
