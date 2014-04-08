package eu.gumpinger.erp.keywordspotting;

public class Tf {

	
	
	public static void main(String argus[]) {

		//System.out.println(smaple);
		
		smaple=smaple.replaceAll(",","").replaceAll("-"," ").replaceAll("  "," ").replaceAll("\\.","");//.replaceAll("!", "")
		//.replaceAll("(Applause)", "").replaceAll("-"," ").replaceAll("  "," ");
		
		System.out.println(smaple);
		
		String[] a=smaple.split("\\ ");
		int[] b=new int[a.length];
		
		for(String tmp : a){
			for(int i=0;i<a.length;i++){
				if(tmp.equalsIgnoreCase(a[i])){
					b[i]++;
					break;
				}
			}
		}
		
		for(int i=0;i<a.length;i++)
			for(int j=i;j<a.length;j++){
				if(b[i]<b[j]){
					int temp=b[i];
					b[i]=b[j];
					b[j]=temp;
					
					String stemp=a[i];
					a[i]=a[j];
					a[j]=stemp;
				}
			}
		
		for(int i=0;i<a.length;i++){
			if(b[i]<=0)
				break;
			System.out.println(a[i]+"			"+b[i]);
		}

	}
	
	private static String smaple="I collaborate with bacteria. And Im about to show you some stop-motion footage that I made recently where youll see bacteria accumulating minerals from their environment over the period of an hour. So what youre seeing here is the bacteria metabolizing, and as they do so they create an electrical charge. And this attracts metals from their local environment. And these metals accumulate as minerals on the surface of the bacteria. One of the most pervasive problems in the world today for people is inadequate access to clean drinking water. And the desalination process is one where we take out salts. We can use it for drinking and agriculture. Removing the salts from water -- particularly seawater -- through reverse osmosis is a critical technique for countries who do not have access to clean drinking water around the globe. So seawater reverse osmosis is a membrane-filtration technology. We take the water from the sea and we apply pressure. And this pressure forces the seawater through a membrane. This takes energy, producing clean water. But were also left with a concentrated salt solution, or brine. But the process is very expensive and its cost-prohibitive for many countries around the globe. And also, the brine thats produced is oftentimes just pumped back out into the sea. And this is detrimental to the local ecology of the sea area that its pumped back out into. So I work in Singapore at the moment, and this is a place thats really a leading place for desalination technology. And Singapore proposes by 2060 to produce [900] million liters per day of desalinated water. But this will produce an equally massive amount of desalination brine. And this is where my collaboration with bacteria comes into play. So what were doing at the moment is were accumulating metals like calcium, potassium and magnesium from out of desalination brine. And this, in terms of magnesium and the amount of water that I just mentioned, equates to a $4.5 billion mining industry for Singapore -- a place that doesnt have any natural resources. So Id like you to image a mining industry in a way that one hasnt existed before; imagine a mining industry that doesnt mean defiling the Earth; imagine bacteria helping us do this by accumulating and precipitating and sedimenting minerals out of desalination brine. And what you can see here is the beginning of an industry in a test tube, a mining industry that is in harmony with nature. Thank you.";}