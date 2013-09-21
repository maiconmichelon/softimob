package br.com.michelon.softimob.tela.widget;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParseException;
import java.text.ParsePosition;

import org.apache.commons.lang.StringUtils;

import br.com.michelon.softimob.aplicacao.helper.FormatterHelper;
import de.ralfebert.rcputils.tables.format.StringValueFormatter;

public class TimeStringValueFormatter extends StringValueFormatter{

	public TimeStringValueFormatter() {
		super(new Format() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public Object parseObject(String source, ParsePosition pos) {
				try {
					return FormatterHelper.getSimpleDateFormatHorasMinutos().parseObject(source);
				} catch (ParseException e) {
					return null;
				}
			}
			
			@Override
			public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
				return new StringBuffer(obj == null ? StringUtils.EMPTY : FormatterHelper.getSimpleDateFormatHorasMinutos().format(obj));
			}
		});
	}

}
