package pl.edu.pk.schedulegenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pk.schedulegenerator.model.Title;
import pl.edu.pk.schedulegenerator.repository.TitleRepository;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/title")
public class TitleController {

    @Autowired
    private TitleRepository titleRepository;

    @PostMapping("/addTitle")
    public String saveTitle(@RequestBody Title title) {
        titleRepository.save(title);
        return "Added title: " + title.getName();
    }

    @GetMapping("/findAllTitles")
    public List<Title> getTitles() {
        return titleRepository.findAll();
    }

    @GetMapping("/findTitleById/{id}")
    public Optional<Title> getTitle(@PathVariable String id) {
        return titleRepository.findById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTitle(@PathVariable String id) {
        titleRepository.deleteById(id);
        return "Deleted title with id: " + id;
    }
}
