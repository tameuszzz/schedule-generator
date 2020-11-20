package pl.edu.pk.schedulegenerator.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pk.schedulegenerator.DAO.DepartmentDAO;
import pl.edu.pk.schedulegenerator.Entity.Department;

import java.util.Collection;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentDAO dao;

    public Collection<Department> getDepartments() {
        return dao.getDepartments();
    }

    public Optional<Department> getDepartmentById(String id) {
        return dao.getDepartmentById(id);
    }
}
