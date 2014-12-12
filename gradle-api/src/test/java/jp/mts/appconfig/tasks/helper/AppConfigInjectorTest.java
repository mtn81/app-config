
package jp.mts.appconfig.tasks.helper;

import static org.fest.assertions.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class AppConfigInjectorTest {

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	AppConfigInjector sut;

	@Before
	public void setup() throws IOException{
		ExternalAppConfigFile externalAppConfigFile = ExternalAppConfigFile.asYaml(new File("./src/test/resources/test.yaml"));;
		String distPath = folder.getRoot().getCanonicalPath();
		sut = new AppConfigInjector(externalAppConfigFile, distPath);
	}

	@Test
	public void test_injectで指定パスにファイルが出力されること() throws IOException {

		File injected = sut.inject(new File("./src/test/resources/target.properties"));

		assertThat(injected.exists()).isTrue();
		assertThat(injected.getParent()).isEqualTo(sut.getDistPath());
	}

	@Test
	public void test_injectで設定値が注入されること() throws IOException {

		File injected = sut.inject(new File("./src/test/resources/target.properties"));

		Scanner scanner = new Scanner(injected);
		assertThat(scanner.nextLine()).isEqualTo("hoge");
		assertThat(scanner.nextLine()).isEqualTo("18");
		scanner.close();
	}

}
