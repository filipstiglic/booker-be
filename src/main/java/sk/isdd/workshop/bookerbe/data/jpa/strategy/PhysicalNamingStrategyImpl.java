package sk.isdd.workshop.bookerbe.data.jpa.strategy;

import java.util.Locale;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

/**
 * @Author Filip Stiglic
 */
public class PhysicalNamingStrategyImpl extends PhysicalNamingStrategyStandardImpl {

	@Override
	public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
		return Identifier.toIdentifier((name.getText().toLowerCase().matches("^[c|v]_.+")?"":"t_")+addUnderscores(name.getText()), false);
	}

	@Override
	public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
		return Identifier.toIdentifier(addUnderscores(name.getText()),false);
	}

	private static String addUnderscores(String name) {
		final StringBuilder buf = new StringBuilder(name.replace('.', '_'));
		for (int i = 1; i < buf.length() - 1; i++) {
			if (Character.isLowerCase(buf.charAt(i - 1)) &&
				Character.isUpperCase(buf.charAt(i)) &&
				Character.isLowerCase(buf.charAt(i + 1))) {
				buf.insert(i++, '_');
			}
		}
		return buf.toString().toLowerCase(Locale.ROOT);
	}



}
