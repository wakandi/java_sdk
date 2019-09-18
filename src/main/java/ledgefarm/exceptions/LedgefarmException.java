package ledgefarm.exceptions;

public class LedgefarmException extends Exception
{
    private String code ="Invalid_Operation";
    public LedgefarmException(String message)
    {
        // Call constructor of parent Exception
        super(message);
    }
    public LedgefarmException(String message, String code) {
		super(message);
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
}