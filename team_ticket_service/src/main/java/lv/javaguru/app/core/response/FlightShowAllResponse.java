package lv.javaguru.app.core.response;

import lv.javaguru.app.core.domain.CodeError;

import java.util.ArrayList;
import java.util.List;

public class FlightShowAllResponse extends  Response {

	//private  List<T> response;
	//private Class<T> responseClass;
	private String listAsString;

//public void setResponseClass (Class<T> responseClass) {
//	this.responseClass = responseClass;
//}

	public String getListAsString () {
		return listAsString;
	}

	public void setListAsString (String listAsString) {
		this.listAsString = listAsString;
	}
	public FlightShowAllResponse (String listAsString) {
		this.listAsString = listAsString;
	}
//	public FlightShowAllResponse (List<T> response) {
//		this.response = response;
//	}

//public Class<T> getResponseClass () {
//	return responseClass;
//}

//public List<T> getResponse () {
//	return response;
//}

//public boolean hasErrors () {
//	if (responseClass == CodeError.class)
//		return response.isEmpty();
//	return false;
//}

//public void printResponse () {
//	if (!response.isEmpty())
//		response.forEach(System.out::println);
//}
}
