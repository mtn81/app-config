package jp.mts.appconfig.tasks.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class ExternalAppConfigFile {

	private Map<String, String> configuration;

	private ExternalAppConfigFile() {}

	public static ExternalAppConfigFile asYaml(File file) {
		ExternalAppConfigFile instance = new ExternalAppConfigFile();

		try(InputStream in = new FileInputStream(file)) {
			instance.configuration = (Map<String, String>)new Yaml().load(in);
			return instance;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String value(String key) {
		return configuration.get(key);
	}


}
