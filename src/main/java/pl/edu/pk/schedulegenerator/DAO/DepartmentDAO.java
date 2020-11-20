package pl.edu.pk.schedulegenerator.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pk.schedulegenerator.Entity.Department;

import java.util.Collection;
import java.util.Optional;

@Component
public class DepartmentDAO {

    @Autowired
    private DepartmentRepository repository;

    public Collection<Department> getDepartments() {
        return repository.findAll();
    }

    public Optional<Department> getDepartmentById(String id) {
        return repository.findById(id);
    }
}
