package spring.boot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.MediaType;
import  org.springframework.beans.factory.annotation.Autowired;
import spring.dto.Vote;
import spring.service.ServiceVoting;
import java.util.*;


@RestController
@RequestMapping(value = "/vote", produces = MediaType.APPLICATION_JSON_VALUE)
public class VotingController {

@Autowired
ServiceVoting serviceVoting;


    @RequestMapping(value="/start", method = RequestMethod.POST)
    public  Vote createVote(@RequestParam(value="nameOfVote") String nameOfVote,@RequestParam(value="question") String question, @RequestParam(value="variant") String[] variants)  {
		
		//Validation of input data
		if(nameOfVote.equals(null) || nameOfVote.isEmpty()) {
			System.out.println("Enter the name of the vote");
		} else if(question.equals(null) || question.isEmpty()) {
			System.out.println("Enter the question for the vote");
		} else if(variants.length<2) {
			System.out.println("Minimal quantity of the answers is two");
		} else if(variants.length>=2) {
			for(int i=0; i<variants.length; i++) {
		        if(variants[i].equals(null) || variants[i].isEmpty()) {
					System.out.println("You must indicate non-empty variants");
				}
			}
		} 
		 return serviceVoting.createVoting(nameOfVote, question, variants);	
		
	}
	@RequestMapping(value="start/{activity}", method = RequestMethod.GET)
	public void start(@PathVariable("activity") boolean activity) {
		
		//The beginning of voting if input condition is true
		
		if(activity) {
	    serviceVoting.startVoting(activity);
		} else {
			
		//The closing of voting when an input condition is false(we pressed button "finish voting")
		
        System.out.println("The voiting"   +          
		
		serviceVoting.getNameOfVote()   +                                             "has closed");
		System.out.println("You can look at statistic");
	    serviceVoting.closeVoting(activity);			
	}
	}
	
	@RequestMapping(value="start/add", method = RequestMethod.POST)
	public void addVoice(@RequestParam(value="numberOfVariant") String numberOfVariant) {
		//Validation
		if(numberOfVariant.equals(null) || numberOfVariant.isEmpty()) {
			System.out.println("You must choose one variant of answer");
		} else {
		serviceVoting.addVoice(Integer.valueOf(numberOfVariant));
		}
		
	}

}