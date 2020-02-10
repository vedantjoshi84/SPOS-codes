//package macropassone;

import java.io.*;
import java.util.*;


public class PassOne {
	ArrayList<MNTEntry>MNT;
	ArrayList<String>MDT;
	ArrayList<String>PNTAB;
	HashMap<String,String>KPDTAB;
	
	public PassOne(){
		MDT    = new ArrayList<String>();
		MNT    = new ArrayList<MNTEntry>();
		PNTAB  = new ArrayList<String>();
		KPDTAB = new HashMap<String,String>();
	}
	
	
	public MacroOutput getOutPut(BufferedReader reader) throws IOException{
		String line;
		int kpdtp=0;
		int mdtp =0;
		StringBuilder iccode = new StringBuilder();
		while((line = reader.readLine())!=null){
			String parts[] = line.split("\\s+");
			
			if(parts[0].equals("MACRO"))
			{
//				System.out.println("found macro");
				PNTAB.clear();
				//handling initial statements
				String prototype[] = reader.readLine().split("\\s+|,\\s+");
				MNTEntry entry = new MNTEntry(prototype[0],mdtp,kpdtp);
				
				//counting no of parameters
				for(int i=1;i<prototype.length;i++){
					//keywordParameters;
					if(prototype[i].contains("=")){
						kpdtp++;
						entry.incrKP();
						String keywordParts[] = prototype[i].replace("&","").split("=");
						KPDTAB.put(keywordParts[0],((keywordParts.length==2) ? keywordParts[1]:"-"));
						PNTAB.add(keywordParts[0]);
					}
					//positionalParameters
					else{
						entry.incrPP();
						PNTAB.add(prototype[i].replace("&",""));
					}
				}
				MNT.add(entry);		
				
				//process macro instructions
				String instruction = reader.readLine();
				StringBuilder tempAns = new StringBuilder();
				while(!instruction.equals("MEND")){
					//System.out.println(instruction);
					String instrParts[] = instruction.split("\\s+|,\\s+");
					
					tempAns.append(mdtp).append("  ");
					tempAns.append(instrParts[0]).append("  ");
					tempAns.append(handleOperation(instrParts[1]));
					if(instrParts.length == 3)
						tempAns.append(",").append(handleOperation(instrParts[2]));
					tempAns.append("\n");
					mdtp++;
					instruction = reader.readLine();
				}
				MDT.add(tempAns.toString());
			}else{
				iccode.append(line).append("\n");
			}
		}
		MacroOutput macroOutput = new MacroOutput();
		
		//for IC
		macroOutput.setIC(iccode.toString());
		
		
		//for MNT
		final StringBuilder mnt = new StringBuilder(); 
		for(MNTEntry values : MNT){
		  mnt.append(values.getName()+"\t" + values.getPp()+"\t" + values.getKp()+"\t" + values.getMdtp()+"\t"
				  		+ values.getKpdtp()).append("\n");
		}
		macroOutput.setMNT(mnt.toString());

		//for KPDTAB
		final StringBuilder kpdtab = new StringBuilder();
		   for (Map.Entry<String, String> pair: KPDTAB.entrySet()) {
	         String tempkey = pair.getKey();
	         String tempvalue = pair.getValue();
	         kpdtab.append(tempkey).append("\t").append(tempvalue).append("\n");
		   }
		macroOutput.setKPDTAB(kpdtab.toString());
		
		//for PNTAB
		  final StringBuilder pntab = new StringBuilder();	
		  for (String values : PNTAB) {
	            pntab.append(values).append("\n");
	        }
		macroOutput.setPNTAB(pntab.toString());
		
		//for MDT
		final StringBuilder mdt = new StringBuilder();
		for(String values : MDT){
				mdt.append(values);
			}
		macroOutput.setMDT(mdt.toString());
		
		
		return macroOutput;
	}
	
	public String handleOperation(String op){
		op = op.replace("&","");
		int index = PNTAB.indexOf(op);
		if(index != -1)
			op = "(P," + (index+1) + ")";
		return op;
	}
}

class MacroOutput{
	String MNT;
	String KPDTAB;
	String PNTAB;
	String MDT;
	String IC;
	
	public String getIC(){
		return IC;
	}
	public void setIC(String iC){
		IC = iC;
	}
	public String getMNT() {
		return MNT;
	}
	public void setMNT(String mNT) {
		MNT = mNT;
	}
	public String getKPDTAB() {
		return KPDTAB;
	}
	public void setKPDTAB(String kPDTAB) {
		KPDTAB = kPDTAB;
	}
	public String getPNTAB() {
		return PNTAB;
	}
	public void setPNTAB(String pNTAB) {
		PNTAB = pNTAB;
	}
	public String getMDT() {
		return MDT;
	}
	public void setMDT(String mDT) {
		MDT = mDT;
	}
}
class MNTEntry {
	String name;
	int pp;
	int kp;
	int mdtp;
	int kpdtp;
	
	public MNTEntry(String name, int mdtp, int kpdtp){
		this.name  = name;
		this.kpdtp = kpdtp;
		this.mdtp  = mdtp;
	}
	public void incrKP(){
		//System.out.println("in incrKP");
		this.kp++;
	}
	public void incrPP(){
		//System.out.println("in incrPP");
		this.pp++;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPp() {
		return pp;
	}
	public void setPp(int pp) {
		this.pp = pp;
	}
	public int getKp() {
		return kp;
	}
	public void setKp(int kp) {
		this.kp = kp;
	}
	public int getMdtp() {
		return mdtp;
	}
	public void setMdtp(int mdtp) {
		this.mdtp = mdtp;
	}
	public int getKpdtp() {
		return kpdtp;
	}
	public void setKpdtp(int kpdtp) {
		this.kpdtp = kpdtp;
	}
}
