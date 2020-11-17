package pl.edu.pk.schedulegenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import pl.edu.pk.schedulegenerator.DAO.DepartmentRepository;
import pl.edu.pk.schedulegenerator.DAO.TitleRepository;
import pl.edu.pk.schedulegenerator.Entity.Department;
import pl.edu.pk.schedulegenerator.Entity.Title;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@Configuration
public class  ScheduleGeneratorApplication implements CommandLineRunner {

	@Autowired
	private TitleRepository titleRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	public static void main(String[] args) {
		SpringApplication.run(ScheduleGeneratorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		departmentRepository.deleteAll();
		departmentRepository.save(new Department("1", "Wydział Informatyki i Telekomunikacji"));

		titleRepository.deleteAll();
		titleRepository.save(new Title("1", "inż."));
		titleRepository.save(new Title("2", "mgr"));
		titleRepository.save(new Title("3", "mgr inż"));
		titleRepository.save(new Title("4", "dr"));
		titleRepository.save(new Title("5", "dr inż."));
		titleRepository.save(new Title("6", "dr hab."));
		titleRepository.save(new Title("7", "dr hab. inż."));
		titleRepository.save(new Title("8", "dr hab. prof. PK"));
		titleRepository.save(new Title("9", "dr hab. inż. prof. PK"));
		titleRepository.save(new Title("10", "prof. dr hab."));
		titleRepository.save(new Title("11", "prof. dr hab. inż."));
	}
}
