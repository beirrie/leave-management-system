package sg.nus.iss.leavesystem.ca.util;

public class CheckOT {
	
	public static Double ValidOrNot(String input) {
		try {
			double inputnumber = Double.parseDouble(input);
			if(inputnumber > 0 && inputnumber % 0.5 == 0) {
				return inputnumber;
			}
			else {
				return null;
			}
		}
		catch (Exception e) {
			return null;
		}
	}
}
