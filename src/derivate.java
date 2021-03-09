
public class derivate {
	static expressions e = new expressions();
	@SuppressWarnings("static-access")
	public static String derivate(String s) {
		// Scot parantezele
		while(!s.equals(e.elimBrackets(s)))
			s = e.elimBrackets(s);
		
		// Suma
		if(e.isSum(s))
			return e.addition(s);
		
		// Diferenta
		if(e.isDif(s))
			return e.substraction(s);
		
		// Produs
		if(e.isMultiplication(s))
			return e.multiplyRule(s);
		
		// Impartire
		if(e.isDivision(s))
			return e.divideRule(s);
		
		// --------------------------- //
		
		if(e.isConst(s))
			return "0";
		
		if(e.isX(s))
			return "1";
		
		if(e.isPower(s))
			return e.powerRule(s);
		
		if(e.isSqrt(s))
			return e.sqrtRule(s);
		
		if(e.isLog(s))
			return e.logRule(s);
		
		if(e.isConstPowX(s))
			return e.constPowXRule(s);
		
		// -- functii grigonometrice -- //
		if(e.isTrigon(s))
			return e.trigonDerivate(s);
		
		return "Eroare";
	}
	
}
