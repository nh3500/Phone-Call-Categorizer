/*
 * Copyright 1999-2004 Carnegie Mellon University.
 * Portions Copyright 2004 Sun Microsystems, Inc.
 * Portions Copyright 2004 Mitsubishi Electric Research Laboratories.
 * All Rights Reserved.  Use is subject to license terms.
 *
 * See the file "license.terms" for information on usage and
 * redistribution of this file, and for a DISCLAIMER OF ALL
 * WARRANTIES.
 *
 */

package eu.gumpinger.erp.keywordspotting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Logger;

import edu.cmu.sphinx.frontend.util.AudioFileDataSource;
import edu.cmu.sphinx.frontend.util.StreamCepstrumSource;
import edu.cmu.sphinx.linguist.language.grammar.NoSkipGrammar;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

public class Eveluater {

	
	private static int userID = 34;
	private static int clientID = 5;
	private static String contactphone = "4366482617747";
	private static String timestamp = "20121010150750";
	private static String filename = userID + "_" + contactphone + "_"
			+ timestamp;

	private String type="mfcc"; //"audio" -> wav file ; "mfcc"-> mfcc file
	private String audioconfigfile = "16k_config_audio_wsj.xml";
	private String mfccconfigfile = "./resources/conf/16k_config_mfcc_wsj";
	private String urlToAudioFile = "file:./resources/HB.wav";
	private String urlToMfccFile = "file:./resources/1.mfcc";
	
	private String finalreport="";
	
	private String[] keyword = {"emphasize","inspiration","infrared","camera","remote","Nintendo","exploration","microsoft","hacker","Pronouncing"};
	
	boolean evaluation=true;
	private int[]	ss0={};
	private int[]	ss1={};
	private int[]	ss2={74,95,112,124,199};
	private int[]	ss3={74,123,129,177,304};
	private int[]	ss4={56,83,100,186};
	private int[]	ss5={55,258};
	private int[]	ss6={25,35};
	private int[]	ss7={};
	private int[]	ss8={};
	private int[]	ss9={};
	/*
	 * 
	private int[]	ss0={};
	private int[]	ss1={};
	private int[]	ss2={};
	private int[]	ss3={};
	private int[]	ss4={};
	private int[]	ss5={};
	private int[]	ss6={};
	private int[]	ss7={};
	private int[]	ss8={};
	private int[]	ss9={};
	 */
	
	
	
	private HashMap<String, float[][]> keywordtimestamp = new HashMap<String, float[][]>();

	
	private final static Logger LOGGER = Logger.getLogger(Eveluater.class
			.getName());

	private Recognizer recognizer;
	private NoSkipGrammar grammar;
	
	public static void main(String argus[]) {		

		Eveluater test = new Eveluater();
		try {
			test.run(filename, userID, clientID);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Throwable ex) {
	        System.err.println("Uncaught exception - " + ex.getMessage());
	        ex.printStackTrace(System.err);
	    }

	}

	public void run(String incomefilename, int incomingID, int inclientID)
			throws IOException {
		
		if(evaluation==true){
			float[][]	kts0 = null;
			float[][]	kts1= null;
			float[][]	kts2= null;
			float[][]	kts3= null;
			float[][]	kts4= null;
			float[][]	kts5= null;
			float[][]	kts6= null;
			float[][]	kts7= null;
			float[][]	kts8= null;
			float[][]	kts9= null;
		
		kts0=new float[2][ss0.length];
		kts1=new float[2][ss1.length];
		kts2=new float[2][ss2.length];
		kts3=new float[2][ss3.length];
		kts4=new float[2][ss4.length];
		kts5=new float[2][ss5.length];
		kts6=new float[2][ss6.length];
		kts7=new float[2][ss7.length];
		kts8=new float[2][ss8.length];
		kts9=new float[2][ss9.length];
		
		for(int i=0;i<ss0.length;i++){
			kts0[0][i]=ss0[i];
			kts0[1][i]=(float)0.0;
		}
		
		for(int i=0;i<ss1.length;i++){
			kts1[0][i]=ss1[i];
			kts1[1][i]=(float)0.0;
		}
		
		for(int i=0;i<ss2.length;i++){
			kts2[0][i]=ss2[i];
			kts2[1][i]=(float)0.0;
		}
		
		for(int i=0;i<ss3.length;i++){
			kts3[0][i]=ss3[i];
			kts3[1][i]=(float)0.0;
		}
		
		for(int i=0;i<ss4.length;i++){
			kts4[0][i]=ss4[i];
			kts4[1][i]=(float)0.0;
		}
		
		for(int i=0;i<ss5.length;i++){
			kts5[0][i]=ss5[i];
			kts5[1][i]=(float)0.0;
		}
		
		for(int i=0;i<ss6.length;i++){
			kts6[0][i]=ss6[i];
			kts6[1][i]=(float)0.0;
		}
		
		for(int i=0;i<ss7.length;i++){
			kts7=new float[2][ss7.length];
			kts7[0][i]=ss7[i];
			kts7[1][i]=(float)0.0;
		}
		
		for(int i=0;i<ss8.length;i++){
			kts8[0][i]=ss8[i];
			kts8[1][i]=(float)0.0;
		}
		
		for(int i=0;i<ss9.length;i++){
			kts9[0][i]=ss9[i];
			kts9[1][i]=(float)0.0;
		}
			
			
		keywordtimestamp.put(keyword[0], kts0 );
		keywordtimestamp.put(keyword[1], kts1 );
		keywordtimestamp.put(keyword[2], kts2 );
		keywordtimestamp.put(keyword[3], kts3 );
		keywordtimestamp.put(keyword[4], kts4 );
		keywordtimestamp.put(keyword[5], kts5 );
		keywordtimestamp.put(keyword[6], kts6 );
		keywordtimestamp.put(keyword[7], kts7 );
		keywordtimestamp.put(keyword[8], kts8 );
		keywordtimestamp.put(keyword[9], kts9 );
		}
		
		userID = incomingID;
		clientID = inclientID;
		retrivekeywords();
	}

	private boolean retrivekeywords() {

		/*
		String[] list = filename.split("_");
		String contactphone = list[1];
		String timestamp = list[2];

		IContactPersonMngmtDAO inserter = new ContactPersonMngmetDAO();
		IActionLogDAO actor = new ActionLogDAO();
*/
		
		for(int k=5;k<=10;k++){
			for(int h=5;h<=10;h++){
		
		/*
		 * Prepare recognizer
		 */
		if(type=="audio"){
			prepareAudioRecognizer();
		}else if(type=="mfcc"){
			finalreport=finalreport+mfccconfigfile+"_"+k+"_"+h+".xml\n";
			System.out.println(mfccconfigfile+"_"+k+"_"+h+".xml");
			prepareMfccRecognizer(mfccconfigfile+"_"+k+"_"+h+".xml");
		}

		/*
		 * Keywords retrieval
		 */
/*
		UserTO user = new UserTO();
		user.setUserId(userID);

		IUserDAO retriver = new UserDAO();
		user = retriver.getKeywords(user);
		// String[] keyword = user.getKeywords().split(","); //retrieve from DB
		*/

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
/*
		AktionLogEntryTO newtask = new AktionLogEntryTO();

		AktionTO ato = new AktionTO();
		ato.setName("mobilephone");
		newtask.setAktionid(actor.getActionByName(ato).getId()); // kind of task

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
*/
		String note = "";

		/*if (findcontact == false)
			note = note + "contact:" + contactphone + "\n";
*/
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

			/*
			LOGGER.info(keyword[i] + " appear " + appear[i] + " times.Density : "
					+ den);
*/
			
			if (appear[i] >= 3)
				note = note + keyword[i] + ",";
		}
		
		//LOGGER.info("Content of note column : "+note);

		//newtask.setComment(note);

		/*
		 * Save to DB
		 */

		//actor.addAktionLogEntry(newtask);
/*
		System.out.println("raw result form decoder:"
				+ result.getTimedBestResult(false, true));
	*/	
		String[]tmp=result.getTimedBestResult(false, true).split("\\ ");
		for(String target : tmp){
			if(!target.contains("<unk>")){
				System.out.println(target);
				//finalreport=finalreport+target+"\n";
			}
		}
		
		if(evaluation==true){
			
			System.out.println();
			System.out.println("-------------evaluation----------------");
			String e = result.getTimedBestResult(false, true);
			int insertion=0;
			int deletion=0;
			int correct=0;
			
			String[] et=e.split("\\ ");
			for(String token2 : et){
				String[] et2=token2.split("\\(");
				if(!et2[0].equalsIgnoreCase("<unk>")){
					String[] et3=et2[1].replaceAll("\\(","").replaceAll("\\)","").split("\\,");
					float ap=Float.parseFloat(et3[0]);
					float[][] ks=keywordtimestamp.get(et2[0]);
					boolean find=false;
					if(ks!=null){
						for(int i=0;i<ks[0].length;i++){
							//System.out.print(et2[0]+" : ");
							//System.out.print((ks[0][i]-1.0)+"<"+ap+"<"+(ks[0][i]+1.0)+"\n");
							ks[1][i]=(float) 0.0;
							
							if( ap <= ks[0][i]+1.0 && ap >= ks[0][i]-1.0  ){
								ks[1][i]=(float) 1.0;
								correct++;
								System.out.println("SPOTTED:	"+et2[0]+"("+et2[1]);
								finalreport=finalreport+"SPOTTED:	"+et2[0]+"("+et2[1]+"\n";
								find=true;
								break;
							}
						}
					}
					if(find==false){
						insertion++;
						System.out.println("Insertion:	"+et2[0]+"("+et2[1]);
						finalreport=finalreport+"Insertion:	"+et2[0]+"("+et2[1]+"\n";
					}
				}
				
			}

			for(String r : keyword){
				float[][] ks=keywordtimestamp.get(r);
				if(ks!=null){
					for(int i=0;i<ks[0].length;i++){
						if( ks[1][i]==0.0 ){
							deletion++;
						}
					}
				}
			}
			
			System.out.println("insertion : "+insertion+"	"+ (float)(insertion/(insertion+correct)));
			System.out.println("deletion : "+deletion);
			System.out.println("correct : "+correct+"	"+ (float)(insertion/(insertion+correct)));
			
			finalreport=finalreport+"insertion : "+insertion+"\n";
			finalreport=finalreport+"deletion : "+deletion+"\n";
			finalreport=finalreport+"correct : "+correct+"\n";
		}
			}
		}
		
		
		File fw = null;
		FileWriter bw = null;
		fw = new File("1_wsj_revise_out.txt");
		try {
			bw =new FileWriter(fw);
			bw.write(finalreport);
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.print(finalreport);
		return true;

	}

	private void prepareAudioRecognizer(){
		LOGGER.info("Prepare Audio recognizer");
		ConfigurationManager cm = new ConfigurationManager(audioconfigfile);
		recognizer = (Recognizer) cm.lookup("recognizer");
		grammar = (NoSkipGrammar) cm.lookup("NoSkipGrammar");

		AudioFileDataSource dataSource = (AudioFileDataSource) cm
				.lookup("audioFileDataSource");

		try {
			dataSource.setAudioFile(new URL(urlToAudioFile), null);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	private void prepareMfccRecognizer(String conf){
		recognizer=null;
		grammar=null;
		LOGGER.info("Prepare MFCC recognizer");
		ConfigurationManager cm = new ConfigurationManager(conf);
		recognizer = (Recognizer) cm.lookup("recognizer");
		grammar = (NoSkipGrammar) cm.lookup("NoSkipGrammar");

		StreamCepstrumSource cepstrumsource = (StreamCepstrumSource) cm.lookup("streamCepstrumSource");

		try {
			cepstrumsource.setInputStream(new URL(urlToMfccFile).openStream(), true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
