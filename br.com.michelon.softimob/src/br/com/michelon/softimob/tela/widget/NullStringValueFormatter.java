package br.com.michelon.softimob.tela.widget;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

import org.apache.commons.lang.StringUtils;
import org.jsoup.helper.StringUtil;

import de.ralfebert.rcputils.tables.format.StringValueFormatter;

public class NullStringValueFormatter extends StringValueFormatter{

	public NullStringValueFormatter() {
		super(new Format(){

			private static final long serialVersionUID = 1L;

			@Override
			public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
				if(obj == null)
					return new StringBuffer(StringUtils.EMPTY);
				return new StringBuffer(obj.toString());
			}

			@Override
			public Object parseObject(String source, ParsePosition pos) {
				return null;
			}
			
		});
	}

}
