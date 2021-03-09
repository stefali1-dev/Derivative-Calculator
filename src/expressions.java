
 public class expressions {
	 static derivate d = new derivate();
	 static boolean isConst(String s) {
		if(s.equals("e"))
			return true;
		boolean ok = true;
		for(int i=0; i<s.length(); i++) {
			if(!Character.isDigit(s.charAt(i))) {
				ok = false;
				break;
			}
		}
		return ok;
	}
	 static boolean isX(String s) {
		return s.equals("x");
	}
	 static boolean isSqrt(String s) {
		String s2 = s.substring(0, s.indexOf('(') + 1);
		return s2.equals("sqrt(") && isSolo(s);
	}
	 static boolean isLog(String s) {
		String s2 = s.substring(0, s.indexOf('(') + 1);
		return s2.equals("ln(") && isSolo(s);
	}
	 static boolean isSin(String s) {
		String s1 = s.substring(0, s.indexOf('(') + 1);
		return s1.equals("sin(") && isSolo(s);
	}
	
	 static boolean isCos(String s) {
		String s1 = s.substring(0, s.indexOf('(') + 1);
		return s1.equals("cos(") && isSolo(s);
	}
	
	 static boolean isTg(String s) {
		String s1 = s.substring(0, s.indexOf('(') + 1);
		return s1.equals("tg(") && isSolo(s);
	}
	
	 static boolean isCtg(String s) {
		String s1 = s.substring(0, s.indexOf('(') + 1);
		return s1.equals("ctg(") && isSolo(s);
	}

	 static boolean isArcSin(String s) {
		String s1 = s.substring(0, s.indexOf('(') + 1);
		return s1.equals("arcsin(") && isSolo(s);
	}
	
	 static boolean isArcCos(String s) {
		String s1 = s.substring(0, s.indexOf('(') + 1);
		return s1.equals("arccos(") && isSolo(s);
	}
	
	 static boolean isArcTg(String s) {
		String s1 = s.substring(0, s.indexOf('(') + 1);
		return s1.equals("arctg(") && isSolo(s);
	}
	
	 static boolean isArcCtg(String s) {
		String s1 = s.substring(0, s.indexOf('(') + 1);
		return s1.equals("arcctg(") && isSolo(s);
	}
	 static boolean isTrigon(String s) {
		 return isSin(s) || isCos(s) || isTg(s) || isCtg(s) || isArcSin(s) ||
				 isArcCos(s) || isArcTg(s) || isArcCtg(s);
	 }
	 static boolean isPower(String s) {
		if(s.indexOf('^') == -1)
			return false;
		else {
			String s1 = s.substring(0, s.indexOf('^'));
			String s2 = s.substring(s.lastIndexOf('^')+1);
			return isConst(s2) && !isConst(s1);
		}
	}
	 static boolean isConstPowX(String s) {
		if(s.indexOf('^') == -1)
			return false;
		else {
			String s1 = s.substring(0, s.indexOf('^'));
			String s2 = s.substring(s.lastIndexOf('^')+1);
			return isConst(s1) && !isConst(s2);
		}
	}
	 static boolean isSolo(String s) {
		int count_open = 0;
		int count_close = 0;
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i) == '(')
				count_open++;
			if(s.charAt(i) == ')')
				count_close++;
			if(count_open != 0)
				if(count_open == count_close && i != s.length() - 1)
					return false;
		}
		return true;
	}
	 static boolean isSingleExpression(String s) {
		return isX(s) || isSqrt(s) || isLog(s) || isTrigon(s) || isPower(s) || isConstPowX(s);
	}
	 static boolean isInParan(String s) {
		return s.indexOf('(') == 0 && s.indexOf(')') == s.length() - 1;
	}
	 static int isOutOfParan(String s, char c) {
		int count = 0;
		for(int i=0; i<s.length(); i++) {
			if(s.charAt(i) == '(') 
				count++;
			if(s.charAt(i) == ')')
				count--;
			if(s.charAt(i) == c && count == 0)
				return i;
		}
		return -1;
	}
	 static String elimBrackets(String s) {
			if(s.indexOf('(') == 0 && s.lastIndexOf(')') == s.length() - 1) {
				int count_open = 0;
				int count_close = 0;
				for(int i=0; i<s.length(); i++) {
					if(s.charAt(i) == '(')
						count_open++;
					if(s.charAt(i) == ')')
						count_close++;
					if(count_open == count_close && i != s.length() - 1)
						return s;
				}
				return s.substring(1, s.length() - 1);
			}
			else
				return s;
		}
	 
	 static boolean hasBrackets(String s) {
		 return (s.indexOf('(') == 0) && (s.lastIndexOf(')') == s.length() - 1);
	}
	 static boolean isOperationSign(char c) {
		 return c == '+' || c == '-' || c == '*' || c == '/';
	 }
	 static String Chain(String s) {
			if(s.indexOf('(') != -1) {
				int first = s.indexOf('(');
				int last = s.lastIndexOf(')');
				return s.substring(first + 1, last);
			}
			else
				return s;
		}
	 static int stringCount(String s, String findStr) {
		 int lastIndex = 0;
		 int count = 0;

		 while(lastIndex != -1){

		     lastIndex = s.indexOf(findStr,lastIndex);

		     if(lastIndex != -1){
		         count ++;
		         lastIndex += findStr.length();
		     }
		 }
		 return count;
	 }
	 static String addSubString(String s, String sub, int poz) {
		 for(int i=0; i<sub.length(); i++) {
			 s = addChar(s, sub.charAt(i), poz);
			 poz++;
		 }
		 return s;
	 }
	 static String addChar(String str, char ch, int position) {
		    int len = str.length();
		    char[] updatedArr = new char[len + 1];
		    str.getChars(0, position, updatedArr, 0);
		    updatedArr[position] = ch;
		    str.getChars(position, len, updatedArr, position + 1);
		    return new String(updatedArr);
		}
	 public static int index_st, index_dr;
	 static String parteaStanga(String s, int index) {
		 index_st = index - 1;
		 int i = index_st, count_open = 0, count_close = 0;
		 
		 for(i = index_st; i>=0; i--) {
			 if(s.charAt(i) == ')')
					count_close++;
			 if(s.charAt(i) == '(')
					count_open++;
			 if((count_open == count_close && isOperationSign(s.charAt(i))) || count_open > count_close) {
				 index_st = i+1;
				 break;
			 }
				 
		 }
		 
		 if(i == -1)
			 index_st = 0;
		 s = s.substring(index_st, index);
		 return s;
	 }
	 static String parteaDreapta(String s, int index) {
		 index_dr = index + 1;
		 int i, count_open = 0, count_close = 0;
		 for(i = index_dr; i<s.length(); i++) {
			 if(s.charAt(i) == ')')
					count_close++;
			 if(s.charAt(i) == '(')
					count_open++;
			 if((count_open == count_close && isOperationSign(s.charAt(i))) || count_close > count_open) {
				 index_dr = i - 1;
				 break;
			 }
		 }
		 if(i == s.length())
			 index_dr = s.length() - 1;
		 s = s.substring(index + 1, index_dr + 1);
		 return s;
	 }
	 
	 static String formatLatexStyle(String s) {
		 
		 // fraction format
		 String c = "/";
		 for (int index = s.indexOf(c);index >= 0; index = s.indexOf(c, index + 1)) {
			 // partea stanga)
			 String s1 = parteaStanga(s, index);
			 // partea dreapta
			 String s2 = parteaDreapta(s, index);
			 int len_frac = index_dr - index_st + 1;
			 
			 
			 // elim brackets
			 while(!s1.equals(elimBrackets(s1)))
					s1 = elimBrackets(s1);
			 while(!s2.equals(elimBrackets(s2)))
					s2 = elimBrackets(s2);
			 
			 String frac = "\\frac {" + s1 + "}{" + s2 + "}";  
			 
			 s = s.substring(0, index_st) + s.substring(index_st + len_frac);
			 
			 s = addSubString(s, frac, index_st);
		 }
		 // multiplication format
		 c = "*";
		 for (int index = s.indexOf(c);index >= 0; index = s.indexOf(c, index + 1)) {
			 s = s.substring(0, index) + s.substring(index + 1);
			 s = addSubString(s, "\\cdot ", index);
		 }
		 
		 // power format ------ TRE SA LUCREZ AICI --------------------------
		 c = "^";
		 for (int index = s.indexOf(c);index >= 0; index = s.indexOf(c, index + 1)) {
			 
			 if(s.charAt(index + 1) == 'x' || s.charAt(index + 1) == 'e') {
				 //nimik;
			 }
			 
			 else if(s.charAt(index + 1) == '(') {
				 s = addChar(s, '{', index+1);
				 index += 2;
			     if(s.charAt(index) == '(') {
			    	  s = s.substring(0, index) + s.substring(index+1);
			    	  while(s.charAt(index) != ')')
			    		  index++;
			    	  s = addChar(s, '}', index+1);
			    	  s = s.substring(0, index) + s.substring(index+1);
			     }
			 }
			 else {
				 s = addChar(s, '{', index+1);
				 index += 2;
				 try {
					 while(Character.isDigit(s.charAt(index))) {
						 index++;
					 }
					 s = addChar(s, '}', index);
				 }
				 catch(Exception e){
					 s = addChar(s, '}', s.length());
				 }
			 }
		     
		 }
		 
		 // sqrt format
		 String c2 = "sqrt(";
		 for (int index = s.indexOf(c2);index >= 0; index = s.indexOf(c2, index + 1)) {
			 // insert backslash
			 s = addChar(s, '\\', index);
			 index++;
			 // first paran
			 index += 4;
			 s = addChar(s, '{', index);
			 index++;
			 s = s.substring(0, index) + s.substring(index+1);
			 
			 //second paran
			while(s.charAt(index) != ')')
				index++;
			s = addChar(s, '}', index);
			index++;
			s = s.substring(0, index) + s.substring(index+1);
			 
			 //
		 }
		 
		 return s;
	 }
	 static String finalStringFormat(String s) {
		 String elem[] = {"\\cdot 1", "\\cdot (1)", "1\\cdot ", "(1)\\cdot ", "\\cdot ((1)), ", "((1))\\cdot "};
		 for(String c: elem) {
			 for (int index = s.indexOf(c);index >= 0; index = s.indexOf(c, index + 1)) {
				 s = s.substring(0, index) + s.substring(index+c.length());
			 }
		 }
		 return s;
	 }
	
	//  --------- Rules ----------//
	 
	 static int plusPosition(String s) {
		return isOutOfParan(s, '+');
	}
	 static int minusPosition(String s) {
		return isOutOfParan(s, '-');
	}
	 static int multiplicPosition(String s) {
		return isOutOfParan(s, '*');
	}
	 static int divisionPosition(String s) {
		return isOutOfParan(s, '/');
	}
	 static boolean isSum(String s) {
		if(plusPosition(s) != -1)
			return true;
		else
			return false;
	}
	 static boolean isDif(String s) {
		if(minusPosition(s) != -1)
			return true;
		else
			return false;
	}
	 static boolean isMultiplication(String s) {
		if(multiplicPosition(s) != -1)
			return true;
		else
			return false;
	}
	 static boolean isDivision(String s) {
		if(divisionPosition(s) != -1)
			return true;
		else
			return false;
	}
	 
	static String addition(String s) {
		 int poz = plusPosition(s);
		 String s1 = s.substring(0, poz);
		 String s2 = s.substring(poz + 1);
		 
		 String s1_derivate = d.derivate(s1);
		 String s2_derivate = d.derivate(s2);
		 
		 if(hasBrackets(s1))
			 s1_derivate = "(" + s1_derivate + ")";
		 
		 if(hasBrackets(s2))
			 s2_derivate = "(" + s2_derivate + ")";
		 
		 if(isConst(s1) && !isConst(s2))
			 return s2_derivate;
					 
		 if(isConst(s2) && !isConst(s1))
			return s1_derivate;
					 
		 if(isConst(s2) && isConst(s1))
			return "0";
		 
		 return s1_derivate + "+" + s2_derivate;
	 }
	 static String substraction(String s) {
		 int poz = minusPosition(s);
		 String s1 = s.substring(0, poz);
		 String s2 = s.substring(poz + 1);
		 
		 String s1_derivate = d.derivate(s1);
		 String s2_derivate = d.derivate(s2);
		 
		 if(hasBrackets(s1))
			 s1_derivate = "(" + s1_derivate + ")";
		 
		 if(hasBrackets(s2))
			 s2_derivate = "(" + s2_derivate + ")";
		 
		 if(isConst(s1) && !isConst(s2))
			 return "-" + s2_derivate;
					 
		 if(isConst(s2) && !isConst(s1))
			return s1_derivate;
					 
		 if(isConst(s2) && isConst(s1))
			return "0";
		 
		 return s1_derivate + "-" + s2_derivate;
	 }
	 static String multiplyRule(String s) {
		 int poz = multiplicPosition(s);
		 String s1 = s.substring(0, poz);
		 String s2 = s.substring(poz + 1);
		 
		 if(isMultiplication(s1) || isDivision(s1))
			 s1 = "(" + s1 + ")";
		 if(isMultiplication(s2) || isDivision(s2))
			 s2 = "(" + s2 + ")";
		 
		 String s1_derivate = d.derivate(s1);
		 String s2_derivate = d.derivate(s2);
		 
		 s1 = formatLatexStyle(s1);
		 s2 = formatLatexStyle(s2);
		 
		 
		 if(hasBrackets(s1))
			 s1_derivate = "(" + s1_derivate + ")";
		 
		 if(hasBrackets(s2))
			 s2_derivate = "(" + s2_derivate + ")";
		 
		 if(s1.equals("0") || s2.equals("0"))
			 return "0";
		 // ----------------------------------//
		 if(isConst(s1) && !isConst(s2))
			 return s1 + "\\cdot " + s2_derivate;
		 
		 if(isConst(s2) && !isConst(s1))
			 return s1_derivate + "\\cdot " + s2;
		 
		 if(isConst(s2) && isConst(s1))
			 return "0";
		 
		return s1_derivate + "\\cdot " + s2 + "+" + s1 + "\\cdot " + s2_derivate;
			 
	 }
	 static String divideRule(String s) {
		 int poz = divisionPosition(s);
		 String s1 = s.substring(0, poz);
		 String s2 = s.substring(poz + 1);
		 
		 if(isMultiplication(s1) || isDivision(s1))
			 s1 = "(" + s1 + ")";
		 if(isMultiplication(s2) || isDivision(s2))
			 s2 = "(" + s2 + ")";
		 
		 String s1_derivate = d.derivate(s1);
		 String s2_derivate = d.derivate(s2);
		 
		 s1 = formatLatexStyle(s1);
		 s2 = formatLatexStyle(s2);
		 
		 if(s1.equals("0"))
			 return "0";
		 
		 if(hasBrackets(s1))
			 s1_derivate = "(" + s1_derivate + ")";
		 
		 if(hasBrackets(s2))
			 s2_derivate = "(" + s2_derivate + ")";
		// ----------------------------------//
		if(isConst(s1) && !isConst(s2))
			return "-\\frac {" + s1 + "\\cdot " + s2_derivate + "} {" + s2 + "^2}";
				 
		if(isConst(s2) && !isConst(s1))
			return "\\frac {" + s1_derivate + "\\cdot " + s2 + "} {" + s2 + "^2}";
				 
		if(isConst(s2) && isConst(s1))
			return "0";
		
		 return "\\frac {" + s1_derivate + "\\cdot " + s2 + "-" + s1 + "\\cdot " + s2_derivate + "} {" + s2 + "^2}";
	 }
	 static String powerRule(String s) {
		 String s1 = s.substring(0, s.lastIndexOf('^'));
		 String s1_derivate = d.derivate(s1);
		 if(hasBrackets(s1))
			 s1_derivate = "(" + s1_derivate + ")";
		 
		 String Const_1 = s.substring(s.lastIndexOf('^') + 1, s.length());
		 
		 // -------
		 String Const_2 = Const_1;
		 char last_char = Const_2.charAt(Const_2.length() - 1);
		 Const_2 = Const_2.substring(0,Const_2.length() - 1);
		 String st = String.valueOf((char)(last_char-1));
		 Const_2 = Const_2.concat(st);
		 // -------
		 
		 
		 s = Const_1 + "\\cdot " + formatLatexStyle(s1) + "^{" + Const_2 + "}";
		 if(s1.equals("x"))
			 return s;
		 else
			 return s + "\\cdot " + s1_derivate;
	 }
	 static String sqrtRule(String s) {
		 String s1 = Chain(s);
		 
		 String s1_derivate = d.derivate(s1);
		 
		 s1_derivate = "(" + s1_derivate + ")";
		 
		 
		 s = "\\frac {1}{2\\cdot \\sqrt{" + formatLatexStyle(s1) + "}" + "}";
		 if(s1.equals("x"))
			 return s;
		 else
			 return s + "\\cdot " + s1_derivate;
	 }
	 static String logRule(String s) {
		 String s1 = Chain(s);
		 
		 String s1_derivate = d.derivate(s1);
		 
		 s1_derivate = "(" + s1_derivate + ")";
		 if(s1.equals("x"))
			 return "\\frac {1}{x}";
		 else
			 return "\\frac {1}{" + formatLatexStyle(s1) + "}\\cdot " + s1_derivate;
	 }
	 static String constPowXRule(String s) {
		 String s1 = s.substring(0, s.indexOf('^'));
		 String s2 = s.substring(s.indexOf('^') + 1);
		 
		 if(!isSum(s2) && !isDif(s2))
			 s2 = elimBrackets(s2);
		 
		 String s2_derivate = "(" + d.derivate(s2) + ")";
		 
		 if(s.equals("e^x"))
			 return s;
		 
		 else if(s1.equals("e"))
			 return "e^{" + formatLatexStyle(s2) + "}" + "\\cdot " + s2_derivate;
		 else
			 return s1 + "^{" + formatLatexStyle(s2) + "}" + "\\cdot " + "ln(" + s1 + ")" + s2_derivate;
	 }
	 static String trigonDerivate(String s) {
		 String s1 = Chain(s);
		 String s1_derivate = "(" + d.derivate(s1) + ")";
		 s1 = formatLatexStyle(s1);
		 
		 if(isSin(s)) {
			 if(s1.equals("x"))
				 return "cos(x)";
			 else
				 return "cos(" + s1 + ")\\cdot " + s1_derivate;
		 }
		 if(isCos(s)) {
			 if(s1.equals("x"))
				 return "-sin(x)";
			 else
				 return "-sin(" + s1 + ")\\cdot " + s1_derivate;
		 }
		 if(isTg(s)) {
			 if(s1.equals("x"))
				 return "\\frac {1}{cos^{2}(x)}";
			 else
				 return "\\frac {1}{cos^{2}(" + s1 + ")}\\cdot " + s1_derivate;
		 }
		 if(isCtg(s)) {
			 if(s1.equals("x"))
				 return "-\\frac {1}{sin^{2}(x)}";
			 else
				 return "-\\frac {1}{sin^{2}(" + s1 + ")}\\cdot " + s1_derivate;
		 }
		 if(isArcSin(s)) {
			 if(s1.equals("x"))
				 return "\\frac {1}{\\sqrt{1-x^{2}}";
			 else
				 return "\\frac {1}{\\sqrt{1-(" + s1 + ")^{2}}}\\cdot " + s1_derivate;
		 }
		 if(isArcCos(s)) {
			 if(s1.equals("x"))
				 return "-\\frac {1}{\\sqrt{1-x^{2}}";
			 else
				 return "-\\frac {1}{\\sqrt{1-(" + s1 + ")^{2}}}\\cdot " + s1_derivate;
		 }
		 if(isArcTg(s)) {
			 if(s1.equals("x"))
				 return "\\frac {1}{1+x^{2}}";
			 else
				 return "\\frac {1}{1+(" + s1 + ")^{2}}\\cdot " + s1_derivate;
		 }
		 if(isArcCtg(s)) {
			 if(s1.equals("x"))
				 return "-\\frac {1}{1+x^{2}}";
			 else
				 return "-\\frac {1}{1+(" + s1 + ")^{2}}\\cdot " + s1_derivate;
		 }
		return s1_derivate;
		 
	 }
}
