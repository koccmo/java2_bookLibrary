package dental_clinic;

import dental_clinic.domain.Card;
import dental_clinic.domain.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardDatabaseImpl implements CardDatabase {

    private Long id= 1L;
    private List<Card> cardList = new ArrayList<>();

    List <Card> getCardList(){
        return cardList;
    }

    @Override
    public boolean addPatient(Patient patient) {
        if (cardListContainsPatient(patient)){
            return false;
        }else
            {
            patient.setId(id);
            cardList.add(new Card(patient));
            id++;
        }
        return true;
    }

    @Override
    public boolean deletePatient(long id) {
        for (int i = 0; i < cardList.size(); i++){
            if (cardList.get(i).getPatient().getId() == id){
                cardList.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public void printDatabase() {
        cardList.forEach(System.out::println);
    }

    @Override
    public boolean printPatientCard(long id) {
            for (int i = 0; i < cardList.size(); i++){
                if (cardList.get(i).getPatient().getId() == id){
                    System.out.println(cardList.get(i).toString());
                    return true;
                }
            }
        return false;
    }

    private boolean cardListContainsPatient (Patient patient){
        Optional <Patient> result = cardList.stream()
                .map(card -> card.getPatient())
                .filter(patient1 -> patient1.equals(patient))
                .findAny();
        return result.isPresent();
    }

    private boolean cardListContainsId (long id){
        Optional <Patient> result = cardList.stream()
                .map(card -> card.getPatient())
                .filter(patient1 -> patient1.getId() == id)
                .findAny();
        return result.isPresent();
    }
}
