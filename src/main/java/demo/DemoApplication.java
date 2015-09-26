package demo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
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
	String putWeights(@RequestBody @Validated Weight requestedWeight) {

		this.weightRepository.save(requestedWeight);
		return "complete";
	}
}

@RepositoryRestResource
interface WeightRepository extends JpaRepository<Weight, Long> {
}

@Entity
class Weight {

	@Id
	private Date recordDate;

	@NotNull
	@Min(0)
	@Max(999)
	private Integer weight1;

	@NotNull
	@Min(0)
	@Max(99)
	private Integer weight2;

	public Weight() {
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public Integer getWeight1() {
		return weight1;
	}

	public Integer getWeight2() {
		return weight2;
	}

}