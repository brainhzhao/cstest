package zhtest.rmiinterface;

import java.rmi.*;
import zhtest.model.*;
import java.util.*;

public interface ConferenceInterface extends java.rmi.Remote{
	UserModel login(UserModel user) throws Exception;
	int addUser(UserModel user) throws Exception;
	boolean isExist(UserModel user) throws Exception;
	Vector<Vector<String>> getConferenceListById(int id) throws Exception;
	boolean addConference(ConferenceModel model) throws Exception;
	boolean deleteConference(int id) throws Exception;
	boolean isCash(ConferenceModel model) throws Exception;
}