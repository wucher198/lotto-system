package pl.myjava.util.read.htmlpage;

import pl.myjava.util.read.htmlpage.consts.LiteralConsts;

public class StringUtils {
	public static boolean isEmpty(String string) {
		return string.length() <= 0 && string.equals(LiteralConsts.EMPTY_STR);
	}
	
	public static boolean isNotEmpty(String string) {
		return !isEmpty(string);
	}
	
	public static boolean isEmpty(StringBuilder builder) {
		return isEmpty(builder.toString());
	}
	
	public static boolean isNotEmpty(StringBuilder builder) {
		return isNotEmpty(builder.toString());
	}
}
