package jp.mts.appconfig.tasks.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringExpressionInjector {

	private ExternalAppConfigFile externalAppConfigFile;

	public StringExpressionInjector(
			ExternalAppConfigFile externalAppConfigFile) {
		this.externalAppConfigFile = externalAppConfigFile;
	}

	public String inject(String src) {

		Pattern pattern = Pattern.compile(expressionPatternOf("([^\\}]*)"));
		Matcher matcher = pattern.matcher(src);

		String dest = src;
		while(matcher.find()){
			dest = dest.replaceAll(expressionPatternOf(matcher.group(1)), externalAppConfigFile.value(matcher.group(1)));
		}

		return dest;
	}

	private String expressionPatternOf(String variable){
		return "\\$\\{\\s*" + variable + "\\s*\\}";
	}


}
