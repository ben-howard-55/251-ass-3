package inventoryman;

public class Music extends Item {
	
	private String _releaseDateStr;
	

	public Music(String artist, String title, String releaseDateStr, String acquisitionDateStr, String owner,
			String costStr, String formatStr) {
		
		super(title, acquisitionDateStr, owner, costStr, formatStr, artist);
		
		_releaseDateStr = releaseDateStr;
			
	}
	
	public String getItemToDisplay() {
		return "'" + getTitle() + "' by " +  getCreator() + ", " + _releaseDateStr + ". (" + 
	    getFormatStr() + ", " + getOwner() + ", " + getAcquisitionDateStr() + ", " + getCostStr() + ")";
	}
	
	public String itemAdded() {
		return "Error";
	}
	public String itemReport() {
		return getOwner() + ": '" + getTitle() + "' by " + getCreator() + " (" + getFormatStr() + ")";
				}

		
}
