package inventoryman;

public class Book extends Item {

	private String _publisher;
	private String _publicationYear;
	
	public Book(String author, String title, String publicationYear, String publisher,
			String acquisitionDateStr, String owner, String costStr, String formatStr) {
		
		super(title, acquisitionDateStr, owner, costStr, formatStr, author);
		
		_publisher = publisher;
		_publicationYear = publicationYear;
	}
	
	public String getItemToDisplay() {
		return getCreator() + ", '" + getTitle() + "'. (" + _publicationYear + ", " +  _publisher + "). [" + getFormatStr() + ", " + 
		getOwner() + ", " + getAcquisitionDateStr() + ", " + getCostStr() + "]";
	}
	
	public String itemAdded() {
		return "Error";
	}
	
	public String itemReport() {
		return getOwner() + ": " + getCreator() + ", '" + getTitle() + "'. (" + getFormatStr() + ")";
				}
}