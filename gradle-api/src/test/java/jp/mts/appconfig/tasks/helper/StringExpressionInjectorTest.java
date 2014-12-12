package jp.mts.appconfig.tasks.helper;

import static org.fest.assertions.api.Assertions.*;
import mockit.Mocked;
import mockit.NonStrictExpectations;

import org.junit.Test;

public class StringExpressionInjectorTest {

	@Test
	public void test(@Mocked ExternalAppConfigFile externalAppConfigFile) {

		new NonStrictExpectations() {{
			externalAppConfigFile.value("hoge"); result = "foo";
			externalAppConfigFile.value("bar"); result = "baz";
		}};

		StringExpressionInjector target = new StringExpressionInjector(externalAppConfigFile);

		String actual = target.inject("${hoge} ${bar} ${hoge}");

		assertThat(actual).isEqualTo("foo baz foo");
	}

}
