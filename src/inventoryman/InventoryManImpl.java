package inventoryman;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class InventoryManImpl implements InventoryMan{

	private ItemsCollection _itemList;
	private String _flatName;

	public InventoryManImpl(String flatName) {
		_flatName = flatName;
		_itemList = new ItemsCollection();
	}

	@Override
	public String addBook(String author, String title, String publicationYear, String publisher,
			String acquisitionDateStr, String owner, String costStr, String formatStr) {
		// Checked exception to see if select inputs are wrong, if so then book will not be added
		try {
			if (!Pattern.matches("\\$\\d+\\.\\d\\d", costStr)) { // Check the cost formatting
				throw new ItemException("ERROR: A Incorrect cost format (needs to be $dollars.cents e.g. $500.50): " + costStr);
			}
		// Check the date formatting
			if (!Pattern.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d", acquisitionDateStr)) {
				throw new ItemException("ERROR: A Incorrect dating format for acquisition date (needs to be ISO8601 format): " + acquisitionDateStr);
			} 	
		}
		catch (ItemException ex) {
			return ex.getMessage(); // Returns an error message if an input error occurs
		}
		// Otherwise book is added
		_itemList.addBook(new Book(author, title, publicationYear, publisher, acquisitionDateStr, owner, costStr, formatStr));
		return "Success";
	}

	@Override
	public String addMusic(String artist, String title, String releaseDateStr, String acquisitionDateStr, String owner,
			String costStr, String formatStr) {
			// Checked exception to see if select inputs are wrong, if so then music will not be added
		try {
			if (!Pattern.matches("\\$\\d+\\.\\d\\d", costStr)) { // Check the cost formatting
				throw new ItemException("ERROR: A Incorrect cost format (needs to be $dollars.cents e.g. $500.50): " + costStr);
			} //check the date formatting
			if (!Pattern.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d", acquisitionDateStr)) {
				throw new ItemException("ERROR: A Incorrect dating format for acquisition date (needs to be ISO8601 format): " + acquisitionDateStr);
			} //check the date formatting
			if (!Pattern.matches("\\d\\d\\d\\d-\\d\\d-\\d\\d", acquisitionDateStr)) {
				throw new ItemException("ERROR: A Incorrect dating format for release date (needs to be ISO8601 format): " + releaseDateStr);
			} 
		} 
		catch (ItemException ex) {
			return ex.getMessage(); // Returns an error message if an input error occurs
		}
		// Otherwise music is added
		_itemList.addMusic(new Music(artist, title, releaseDateStr, acquisitionDateStr, owner, costStr, formatStr));
		return "Success";
	}

	@Override
	public String getItemToDisplay(String creator, String title, String formatStr) {
		return _itemList.findItem(creator+title+formatStr);
	}
	
	@Override
	public List<String> getAll(String order) {
			return _itemList.getAll(order);
	}

	@Override
	public List<String> getItemsAcquiredInYear(String year) {
		 return _itemList.getItemsAcquiredInYear(year);
	}

	@Override
	public List<String> getCreators() {
			return _itemList.getCreators();
		}

	@Override
	public List<String> getFlatReport() {
		List<String> info = new ArrayList<String>();
		// Add flatName to output
		info.add(_flatName);
		info.addAll(_itemList.getFlatReport()); // Add all the formatted Items to output
		// Returns the Flat Report
		return info;
	}
}