package demo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

@Controller
class WeightMvcController {
	
	@Autowired
	private WeightRepository weightRepository;
	
	@RequestMapping("weights")
	String getWeights(Model model) {
		
		model.addAttribute("weights", this.weightRepository.findAll());
		return "weights";
	}

	@RequestMapping("weight")
	String putWeights(@RequestBody Weight requestedWeight, Model model) {

		this.weightRepository.save(requestedWeight);
		model.addAttribute("weights", this.weightRepository.findAll());
		return "weights";
	}
}

@RepositoryRestResource
interface WeightRepository extends JpaRepository<Weight, Long> {
}

@Entity
class Weight {

	@Id
	@GeneratedValue
	private Long id;

	private Date recordDate;

	private int weight1;

	private int weight2;

	public Weight() {
	}

	public Long getId() {
		return id;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public int getWeight1() {
		return weight1;
	}

	public int getWeight2() {
		return weight2;
	}

}