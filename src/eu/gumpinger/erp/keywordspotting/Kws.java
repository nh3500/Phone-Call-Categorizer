package eu.gumpinger.erp.keywordspotting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Logger;

import edu.cmu.sphinx.frontend.util.StreamCepstrumSource;
import edu.cmu.sphinx.linguist.language.grammar.NoSkipGrammar;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

import eu.gumpinger.erp.dao.IActionLogDAO;
import eu.gumpinger.erp.dao.IContactPersonMngmtDAO;
import eu.gumpinger.erp.dao.impl.ActionLogDAO;
import eu.gumpinger.erp.dao.impl.ContactPersonMngmetDAO;
import eu.gumpinger.erp.dao.IUserDAO;
import eu.gumpinger.erp.dao.impl.UserDAO;
import eu.gumpinger.erp.TO.AktionTO;
import eu.gumpinger.erp.TO.CustomerContactPersonTO;
import eu.gumpinger.erp.TO.UserTO;
import eu.gumpinger.erp.TO.AktionLogEntryTO;
import eu.gumpinger.erp.TO.ContactPersonTO;

public class Kws implements Runnable{

	private static int userID;
	private static int clientID;
	private static String filename;
	
	private String mfccconfigfile = "/16k_config_mfcc_wsj.xml";

	private final static Logger LOGGER = Logger.getLogger(Kws.class
			.getName());

	private File file=null;
	private Recognizer recognizer;
	private NoSkipGrammar grammar;

	@Override
	public void run() {		
		retrivekeywords();
	}
	
	private boolean retrivekeywords() {

		String[] list = filename.split("_");
		String contactphone = list[1];
		String timestamp = list[2];

		IContactPersonMngmtDAO inserter = new ContactPersonMngmetDAO();
		IActionLogDAO actor = new ActionLogDAO();

		/*
		 * Prepare recognizer
		 */
		prepareMfccRecognizer();

		/*
		 * Keywords retrieval
		 */

		UserTO user = new UserTO();
		user.setUserId(userID);

		IUserDAO retriver = new UserDAO();
		user = retriver.getKeywords(user);
		String[] keyword = user.getKeywords().split(","); //retrieve from DB

		for (int i = 0; i < keyword.length; i++)
			grammar.addKeyword(keyword[i]);

		/*
		 * Start to recognize keywords
		 */

		recognizer.allocate();
		Result result = recognizer.recognize();

		/*
		 * Prepare result
		 */

		AktionLogEntryTO newtask = new AktionLogEntryTO();

		AktionTO ato = new AktionTO();
		ato.setName("mobilephone");
		//newtask.setAktionid(actor.getActionByName(ato).getId()); // kind of task
		newtask.setAktionid(167); // kind of task

		// contact
		boolean findcontact = false;
		ContactPersonTO contactperson = new ContactPersonTO();
		contactperson.setId(userID);
		CustomerContactPersonTO search = new CustomerContactPersonTO();
		search.setClientId(clientID);
		Vector<CustomerContactPersonTO> a = inserter
				.getContactPersonsByClient(search);
		for (int i = 0; i < a.size(); i++) {
			String mobile = a.get(i).getContactMobile().replaceAll("\\+", "")
					.replaceAll(" ", "");
			String tel1 = a.get(i).getContactTel().replaceAll("\\+", "")
					.replaceAll(" ", "");
			String tel2 = a.get(i).getContactTel2().replaceAll("\\+", "")
					.replaceAll(" ", "");
			if (mobile.equalsIgnoreCase(contactphone)
					|| tel1.equalsIgnoreCase(contactphone)
					|| tel2.equalsIgnoreCase(contactphone)) {
				newtask.setContactid(a.get(i).getContactId());
				findcontact = true;
				break;
			}

		}

		newtask.setUserId(userID); // userID

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyyMMddHHmmss");
		Date phoneDate = null;
		try {
			phoneDate = simpleDateFormat.parse(timestamp);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		newtask.setTimestamp(new Timestamp(phoneDate.getTime()));// date
		newtask.setStatus(eu.gumpinger.erp.TO.AktionLogEntryTO.STATUS_DONE); // status

		String note = "";

		if (findcontact == false)
			note = note + "contact:" + contactphone + "\n";

		String b = result.getBestResultNoFiller();

		
		
		String[] token = b.split("\\ ");
		float[] appear = new float[keyword.length];
		
		for (int i = 0; i < appear.length; i++)
			appear[i] = 0;
		
		for (int i = 0; i < token.length; i++) {
			if (!token[i].equalsIgnoreCase("<unk>")) {
				for (int j = 0; j < keyword.length; j++) {
					if (token[i].equalsIgnoreCase(keyword[j]))
						appear[j] = appear[j] + 1;
				}
			}
		}
		
		for (int i = 0; i < appear.length; i++) {
			float den = appear[i] / token.length;

			LOGGER.info(keyword[i] + " appear " + appear[i] + " times.Density : "
					+ den);
			
			if (appear[i] >= 3)
				note = note + keyword[i] + ",";
		}
		
		LOGGER.info("Content of note column : "+note);

		newtask.setComment(note);

		/*
		 * Save to DB
		 */

		actor.addAktionLogEntry(newtask);
		file.delete();
		return true;

	}
	
	private void prepareMfccRecognizer(){
		LOGGER.info("Prepare MFCC recognizer");
		ConfigurationManager cm = new ConfigurationManager(this.getClass().getResource(mfccconfigfile));
		recognizer = (Recognizer) cm.lookup("recognizer");
		grammar = (NoSkipGrammar) cm.lookup("NoSkipGrammar");

		StreamCepstrumSource cepstrumsource = (StreamCepstrumSource) cm.lookup("streamCepstrumSource");


			InputStream in;
			
			try {
					file = new File(filename);
					in = new FileInputStream(file);
					cepstrumsource.setInputStream(in, true);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}	
		
	}
	
	 public Kws(String incomefilename,int incomingID,int inclientID) {
		 	userID=incomingID;
			clientID=inclientID;
			filename=incomefilename;
	    }
}
