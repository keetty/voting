package spring.service; 

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import spring.dto.*;
import java.util.*;

@Service("voting")

public class ServiceVoting {

@Autowired
Vote vote;

@Autowired
Statistic statistic;


public Vote createVoting(String nameOfVote, String question, String[] variants) {
	vote.setNameOfVote(nameOfVote);
	vote.setQuestion(question);
	Map<Integer, String> map=new HashMap();
	int j=1;
	//Add the parameters in the Map of answer's variants
	for(int i=0;i<variants.length;i++) {
		map.put(j,variants[i]);
		j++;
	}
	vote.setVariantsOfAnswer(map);
	return vote;
}
public String getNameOfVote() {
	return vote.getNameOfVote();
}


public void startVoting(boolean activity) {
	vote.setActivity(activity);
	System.out.println(vote.getNameOfVote());
	System.out.println(vote.getQuestion());
	System.out.println(vote.getVariantsOfAnswer());
	//Creating of the list where the response's indexes will be stored
	List<Integer> list = new ArrayList();
	vote.setListOfVoices(list);
	}
public void addVoice(int numberOfVariant) {
vote.getListOfVoices().add(numberOfVariant);
}

public void closeVoting(boolean activity) {
	int i;
	vote.setActivity(activity);
	for(i=1; i<=vote.getVariantsOfAnswer().size(); i++) {
		System.out.println(vote.getVariantsOfAnswer().get(i)+ statistic.quantityOfVoicesByOneVariant(i));
		
	}
	vote.getListOfVoices().clear();
}
}