package DoAn.B19DCCN445.EMarket.exception;

public class StorageException extends Exception{

	public StorageException(String message) {
		super(message);
	}

	public StorageException(String message, Exception e) {
		super(message);
	}

}
