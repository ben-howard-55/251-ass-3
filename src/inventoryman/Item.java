package inventoryman;

public abstract class Item {
	private String _title;
	private String _acquisitionDateStr;
	private String _owner;
	private String _costStr;
	private String _formatStr;
	private String _creator;
	
	public Item(String title, String acquisitionDateStr, String owner, String costStr, String formatStr, String creator) {
		_title = title;
		_acquisitionDateStr = acquisitionDateStr;
		_owner = owner;
		_costStr = costStr;
		_formatStr = formatStr;
		_creator = creator;
		
	}
	
	public String getTitle() {
		return _title;
		
	}
	public String getAcquisitionDateStr() {
		return _acquisitionDateStr;
		
	}
	public String getOwner() {
		return _owner;
		
	}
	public String getCostStr() {
		return _costStr;
		
	}
	public String getFormatStr() {
		return _formatStr;
		
	}
	public String getCreator() {
		return _creator;
	}
	public String itemAdded() {
		return "failed";
	}
	
	public String getItem() {
		return _creator + _title + _formatStr;
	}
	
	public String getItemToDisplay() {
		return _title + " " + _creator + " " + _formatStr;
	}
	
	public String getYearOfAcquisition() {
		return _acquisitionDateStr.substring(0,4);
	}
	
	public String itemReport() {
		return _owner;
	}
	

}
