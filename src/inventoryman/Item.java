package inventoryman;

import java.util.Comparator;

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
	// formats of Items that will not change.
	public enum formatType {
		CD,
		LP,
		Hardcover,
		Paperback;
	} 
    // The only 3 Types of organisation that can occur
    public enum orderType {
		Creator,
		Title,
		Acquisition;
	}
	// nested class that allows the ItemComparator to be used without breaking encapsulation
	public static class ItemComparator implements Comparator<Item> {

		private orderType _type;
	
		public ItemComparator(orderType title) {
			_type = title;
		}
	
		// Compares the objects in different ways depending on its orderType
		public int compare(Item object1, Item object2) {
		switch(_type) {
			case Creator:
				return object1._creator.compareTo(object2._creator);
			case Title:
				return object1._title.compareTo(object2._title);
			case Acquisition:
				return object1._acquisitionDateStr.compareTo(object2._acquisitionDateStr);
			default:
				return object1._creator.compareTo(object2._creator);
			} 
		}
	}

	// abstract methods that the children of Item are expected to use polymorphicly
	protected abstract String getItemToDisplay();
	protected abstract String itemReport();
	
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
	
	public String getItem() {
		return _creator + _title + _formatStr;
	}
	
	public String getYearOfAcquisition() {
		return _acquisitionDateStr.substring(0,4);
	}
}