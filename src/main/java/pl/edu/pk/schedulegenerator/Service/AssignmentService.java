package pl.edu.pk.schedulegenerator.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pk.schedulegenerator.DAO.AssignmentDAO;
import pl.edu.pk.schedulegenerator.Entity.assignment.Assignment;
import pl.edu.pk.schedulegenerator.Entity.assignment.AssignmentUpdate;

import java.util.Collection;
import java.util.Optional;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentDAO dao;

    public Collection<Assignment> getAssignments() {
        return dao.getAssignments();
    }

    public Assignment createAssignment(Assignment assignment) {
        return dao.createAssignment(assignment);
    }

    public Optional<Assignment> getAssignmentById(String id) {
        return dao.getAssignmentById(id);

    }

    public Optional<Assignment> deleteAssignmentById(String id) {
        return dao.deleteAssignmentById(id);
    }

    public Optional<Assignment> updateAssignmentById(String id, AssignmentUpdate assignmentUpdate) {
        return dao.updateAssignmentById(id, assignmentUpdate);
    }
}
