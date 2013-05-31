package br.com.michelon.softimob.tela.binding.updateValueStrategy;

import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.NumberToStringConverter;
import org.eclipse.core.databinding.conversion.StringToNumberConverter;
import org.eclipse.core.internal.databinding.conversion.DateToStringConverter;
import org.eclipse.core.internal.databinding.conversion.StringToDateConverter;
import org.eclipse.core.internal.databinding.validation.StringToDateValidator;
import org.eclipse.core.internal.databinding.validation.StringToIntegerValidator;

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
		
		StringToDateConverter converter = new StringToDateConverter();
		updateValueStrategy.setConverter(converter);
		
		return updateValueStrategy;
	}
	
	public static UpdateValueStrategy uvsDateTimeStampToString(){
		UpdateValueStrategy updateValueStrategy = new UpdateValueStrategy();
		
		DateToStringConverter converter = new DateToStringConverter();
		updateValueStrategy.setConverter(converter);
		
		return updateValueStrategy;
	}
	
}
