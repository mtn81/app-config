package jp.mts.appconfig.tasks.helper;

import static org.fest.assertions.api.Assertions.*;

import java.io.File;

import org.junit.Test;

public class ExternalAppConfigFileTest {

	@Test
	public void test_YAMLファイルを読み込めること() {
		ExternalAppConfigFile sut
			= ExternalAppConfigFile.asYaml(new File("./src/test/resources/test.yaml"));

		assertThat(sut.value("name")).isEqualTo("hoge");
	}

}
