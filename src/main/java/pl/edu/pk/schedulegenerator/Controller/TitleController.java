package pl.edu.pk.schedulegenerator.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.schedulegenerator.Entity.Title;
import pl.edu.pk.schedulegenerator.Service.TitleService;

import java.util.Collection;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/title")
public class TitleController {

    @Autowired
    private TitleService service;

    @GetMapping
    public Collection<Title> getTitles() {
        return service.getTitles();
    }

    @GetMapping("/{id}")
    public Optional<Title> getTitleById(@PathVariable String id) {
        return service.getTitleById(id);
    }

}
