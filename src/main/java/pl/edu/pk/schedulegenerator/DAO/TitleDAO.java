package pl.edu.pk.schedulegenerator.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pk.schedulegenerator.Entity.Title;

import java.util.Collection;
import java.util.Optional;

@Component
public class TitleDAO {

    @Autowired
    private TitleRepository repository;

    public Collection<Title> getTitles() {
        return repository.findAll();
    }

    public Optional<Title> getTitleById(String id) {
        return repository.findById(id);
    }
}
