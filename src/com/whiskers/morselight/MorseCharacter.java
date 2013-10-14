package com.whiskers.morselight;

public enum MorseCharacter {
	A(".-"),
	B("-..."),
	C("-.-."),
	D("-.."),
	E("."),
	F("..-."),
	G("--."),
	H("...."),
	I(".."),
	J(".---"),
	K("-.-"),
	L(".-.."),
	M("--"),
	N("-."),
	O("---"),
	P(".--."),
	Q("--.-"),
	R(".-."),
	S("..."),
	T("-"),
	U("..-"),
	V("...-"),
	W(".--"),
	X("-..-"),
	Y("-.--"),
	Z("--.."),
	
	ONE(".----"),
	TWO("..---"),
	THREE("...--"),
	FOUR("....-"),
	FIVE("....."),
	SIX("-...."),
	SEVEN("--..."),
	EIGHT("---.."),
	NINE("----."),
	ZERO("-----");
	
	private String morseChars;
	
	private MorseCharacter(String morseChars){
		this.morseChars = morseChars;
	}
	
	public String getMorseChars(){
		return morseChars;
	}
}
