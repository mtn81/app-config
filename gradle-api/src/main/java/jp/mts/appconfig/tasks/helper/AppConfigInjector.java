package jp.mts.appconfig.tasks.helper;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class AppConfigInjector {

	private ExternalAppConfigFile externalAppConfigFile;
	private String distPath;

	public AppConfigInjector(
			ExternalAppConfigFile externalAppConfigFile,
			String distPath) {
		this.externalAppConfigFile = externalAppConfigFile;
		this.distPath = distPath;
	}

	public File inject(File file)  {

		try {
			FileUtils.forceMkdir(new File(distPath));
			File distFile = new File(new File(distPath), file.getName());
			distFile.createNewFile();

			String content = FileUtils.readFileToString(file);
			FileUtils.writeStringToFile(distFile,
					new StringExpressionInjector(externalAppConfigFile).inject(content));

			return distFile;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public String getDistPath(){
		return distPath;
	}

}
