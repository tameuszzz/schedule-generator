package pl.edu.pk.schedulegenerator.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pk.schedulegenerator.DAO.TitleDAO;
import pl.edu.pk.schedulegenerator.Entity.Title;

import java.util.Collection;
import java.util.Optional;

@Service
public class TitleService {

    @Autowired
    private TitleDAO dao;

    public Collection<Title> getTitles() {
        return dao.getTitles();
    }

    public Optional<Title> getTitleById(String id) {
        return dao.getTitleById(id);
    }
}
