package test.docker.container.TestDocker.service;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.stereotype.Service;
import test.docker.container.TestDocker.model.Details;
import test.docker.container.TestDocker.model.PersonalDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class DetailsService {


    public  List<PersonalDetails> fetchDetails(String name) {
        List<PersonalDetails> personalDetailsList = new ArrayList<>();
        if(name!=null && name.length()>0) {
            PersonalDetails personalDetails = new PersonalDetails("hyderabad", "Software Engineer", "Male");
            PersonalDetails personalDetails1 = new PersonalDetails("Mineapolis", "Software Engineer", "Male");
            personalDetailsList.add(personalDetails);
            personalDetailsList.add(personalDetails1);
            Details details = new Details("ramu", personalDetailsList);
            List<Details> detailsList = new ArrayList<>();
            detailsList.add(details);
            // Use streams to find the Details object by name
            Optional<List<PersonalDetails>> personalDetailsListOptional = detailsList.stream()
                    .filter(detail -> detail.getName().equals(name))
                    .map(Details::getPersonalDetailsList)
                    .findFirst();

            if (personalDetailsListOptional.isPresent()) {
                return personalDetailsListOptional.get();
            }else {
                return new ArrayList<>();
            }
        }
        return personalDetailsList;
    }
}
