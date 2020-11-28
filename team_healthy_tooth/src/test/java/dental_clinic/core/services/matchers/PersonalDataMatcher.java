package dental_clinic.core.services.matchers;

import dental_clinic.core.domain.PersonalData;
import org.mockito.ArgumentMatcher;

public class PersonalDataMatcher implements ArgumentMatcher<PersonalData> {

    private String personalCode;

    public PersonalDataMatcher(String personalCode){
        this.personalCode = personalCode;
    }

    @Override
    public boolean matches (PersonalData personalData){
        return personalData.getPersonalCode().equals(personalCode);
    }

}
