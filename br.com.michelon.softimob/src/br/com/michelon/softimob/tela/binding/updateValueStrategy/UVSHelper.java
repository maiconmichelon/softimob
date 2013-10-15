package br.com.michelon.softimob.tela.binding.updateValueStrategy;

import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.conversion.NumberToStringConverter;
import org.eclipse.core.databinding.conversion.StringToNumberConverter;
import org.eclipse.core.internal.databinding.conversion.StringToDateConverter;
import org.eclipse.core.internal.databinding.validation.StringToDateValidator;
import org.eclipse.core.internal.databinding.validation.StringToIntegerValidator;
import org.eclipse.nebula.widgets.formattedtext.MaskFormatter;

import br.com.michelon.softimob.aplicacao.helper.NumberHelper;
import br.com.michelon.softimob.tela.binding.convert.DateTimeToStringConverter;
import br.com.michelon.softimob.tela.binding.convert.DateToStringConverter;
import br.com.michelon.softimob.tela.binding.convert.ExtractNumbersConverter;
import br.com.michelon.softimob.tela.binding.convert.StringToDateTimeConverter;
import br.com.michelon.softimob.tela.widget.PhoneTextField;

@SuppressWarnings("restriction")
public class UVSHelper {

	public static UpdateValueStrategy uvsStringToInteger(){
		UpdateValueStrategy updateValueStrategy = new UpdateValueStrategy();
		
		StringToNumberConverter converter = StringToNumberConverter.toInteger(false);
		updateValueStrategy.setConverter(converter);
		
		StringToIntegerValidator validator = new StringToIntegerValidator(converter);
//		updateValueStrategy.setAfterConvertValidator(validator);
//		updateValueStrategy.setBeforeSetValidator(validator);
		updateValueStrategy.setAfterGetValidator(validator);
		
		return updateValueStrategy;
	}

	public static UpdateValueStrategy uvsIntegerToString() {
		UpdateValueStrategy updateValueStrategy = new UpdateValueStrategy();
		
		NumberToStringConverter converter = NumberToStringConverter.fromInteger(false);
		updateValueStrategy.setConverter(converter);
		
		return updateValueStrategy;
	}
	
	public static UpdateValueStrategy uvsStringToDate(){
		UpdateValueStrategy updateValueStrategy = new UpdateValueStrategy();
		
		StringToDateConverter converter = new StringToDateConverter();
		updateValueStrategy.setConverter(converter);
		
		StringToDateValidator validator = new StringToDateValidator(converter);
		updateValueStrategy.setAfterGetValidator(validator);
		
		return updateValueStrategy;
	}
	
	public static UpdateValueStrategy uvsDateToString(){
		UpdateValueStrategy updateValueStrategy = new UpdateValueStrategy();
		
		DateToStringConverter converter = new DateToStringConverter();
		updateValueStrategy.setConverter(converter);
		
		return updateValueStrategy;
	}
	
	public static UpdateValueStrategy uvsStringToBigDecimal(){
		UpdateValueStrategy updateValueStrategy = new UpdateValueStrategy();
		
		StringToNumberConverter converter = StringToNumberConverter.toBigDecimal();
		updateValueStrategy.setConverter(converter);
		
		return updateValueStrategy;
	}
	
	public static UpdateValueStrategy uvsBigDecimalToString(){
		UpdateValueStrategy updateValueStrategy = new UpdateValueStrategy();
		
		NumberToStringConverter converter = NumberToStringConverter.fromBigDecimal();
		updateValueStrategy.setConverter(converter);
		
		return updateValueStrategy;
	}
	
	public static UpdateValueStrategy uvsStringToDateTimeStamp(){
		UpdateValueStrategy updateValueStrategy = new UpdateValueStrategy();
		
		StringToDateTimeConverter converter = new StringToDateTimeConverter();
		updateValueStrategy.setConverter(converter);
		
		return updateValueStrategy;
	}
	
	public static UpdateValueStrategy uvsDateTimeStampToString(){
		UpdateValueStrategy updateValueStrategy = new UpdateValueStrategy();
		
		DateTimeToStringConverter converter = new DateTimeToStringConverter();
		updateValueStrategy.setConverter(converter);
		
		return updateValueStrategy;
	}

	public static UpdateValueStrategy uvsExtractNumbers() {
		UpdateValueStrategy updateValueStrategy = new UpdateValueStrategy();
		
		updateValueStrategy.setConverter(new ExtractNumbersConverter());

		return updateValueStrategy;
	}
	
	public static UpdateValueStrategy uvsLongIsNull(){
		return new UpdateValueStrategy(){
			@Override
			protected IConverter createConverter(Object fromType, Object toType) {
				return new IConverter() {
					
					@Override
					public Object getToType() {
						return Boolean.class;
					}
					
					@Override
					public Object getFromType() {
						return Long.class;
					}
					
					@Override
					public Object convert(Object fromObject) {
						return fromObject != null ;
					}
				};
			}
		};
	}

	public static UpdateValueStrategy uvsStringToFormatTextField() {
		return new UpdateValueStrategy(){
			@Override
			protected IConverter createConverter(Object fromType, Object toType) {
				return new IConverter() {
					
					@Override
					public Object getToType() {
						return String.class;
					}
					
					@Override
					public Object getFromType() {
						return String.class;
					}
					
					@Override
					public Object convert(Object fromObject) {
						return fromObject.toString().trim();
					}
				};
			}
		};
	}
	
	public static UpdateValueStrategy uvsStringToPhoneTextField() {
		return new UpdateValueStrategy(){
			@Override
			protected IConverter createConverter(Object fromType, Object toType) {
				return new IConverter() {
					
					@Override
					public Object getToType() {
						return String.class;
					}
					
					@Override
					public Object getFromType() {
						return String.class;
					}
					
					@Override
					public Object convert(Object fromObject) {
						return NumberHelper.extractNumbers(fromObject.toString().trim());
					}
				};
			}
		};
	}
	
	public static UpdateValueStrategy uvsPhoneToStringTextField() {
		return new UpdateValueStrategy(){
			@Override
			protected IConverter createConverter(Object fromType, Object toType) {
				return new IConverter() {
					
					private MaskFormatter f = new MaskFormatter(PhoneTextField.FORMATTER_TELEFONE);
					
					@Override
					public Object getToType() {
						return String.class;
					}
					
					@Override
					public Object getFromType() {
						return String.class;
					}
					
					@Override
					public Object convert(Object fromObject) {
						f.setValue(fromObject);
						return f.getDisplayString();
					}
				};
			}
		};
	}

	public static UpdateValueStrategy uvsLongIsNotNull() {
		return new UpdateValueStrategy(){
			@Override
			protected IConverter createConverter(Object fromType, Object toType) {
				return new IConverter() {
					
					@Override
					public Object getToType() {
						return Boolean.class;
					}
					
					@Override
					public Object getFromType() {
						return Long.class;
					}
					
					@Override
					public Object convert(Object fromObject) {
						return fromObject == null ;
					}
				};
			}
		};
	}
	
}
