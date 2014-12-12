package jp.mts.appconfig.tasks

import jp.mts.appconfig.tasks.helper.AppConfigInjector
import jp.mts.appconfig.tasks.helper.ExternalAppConfigFile

import org.gradle.api.DefaultTask
import org.gradle.api.file.FileCollection
import org.gradle.api.tasks.TaskAction

class YamlFileAppConfigInjectTask extends DefaultTask {

	File srcYaml
	FileCollection injectTargetFiles
	String distPath

	@TaskAction
	def executeTask() {

		if(srcYaml == null) print "srcYaml is required"
		if(injectTargetFiles == null) print "injectTargetFiles is required"
		if(distPath == null) print "distPath is required"

		ExternalAppConfigFile externalAppConfigFile = ExternalAppConfigFile.asYaml(srcYaml);
		def appConfigInjector = new AppConfigInjector(externalAppConfigFile, distPath);

		injectTargetFiles.each { targetFile ->
			appConfigInjector.inject(targetFile)
		}
	}
}
