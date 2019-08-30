package com.ledgefarm.core;
import java.io.*;

public class LedgefarmException extends Exception
{
    private String code ="Invalid_Operation";
    public LedgefarmException(String s)
    {
        // Call constructor of parent Exception
        super(s);
    }
    public LedgefarmException(String message, String code) {
		super(message);
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
}