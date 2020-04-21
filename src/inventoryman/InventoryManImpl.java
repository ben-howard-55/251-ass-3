package inventoryman;

import java.util.ArrayList;
import java.util.List;

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
		
		try {
			if (costStr.charAt(0) != '$' || costStr.charAt(costStr.length() - 3) != '.') {
				throw new ItemException("ERrOR: A Incorrect cost format (needs to be $dollars.cents e.g. $500.50): " + costStr);
			} 
			if (acquisitionDateStr.charAt(4) != '-' || acquisitionDateStr.charAt(7) != '-' || acquisitionDateStr.charAt(acquisitionDateStr.length()-3) != '-') {
				throw new ItemException("ErOR: A Incorrect dating format for acquisition date (needs to be ISO8601 format): " + acquisitionDateStr);
			} 
			
		}
		catch (ItemException ex) {
			return ex.getMessage();
		}
		
		_itemList.addBook(new Book(author, title, publicationYear, publisher, acquisitionDateStr, owner, costStr, formatStr));
		return "Success";
	}

	@Override
	public String addMusic(String artist, String title, String releaseDateStr, String acquisitionDateStr, String owner,
			String costStr, String formatStr) {
		
		try {
			if (costStr.charAt(0) != '$' || costStr.charAt(costStr.length() - 3) != '.') {
				throw new ItemException("ERROR: A Incorrect cost format (needs to be $dollars.cents e.g. $500.50): " + costStr);
			} 
			if (acquisitionDateStr.charAt(4) != '-' || acquisitionDateStr.charAt(7) != '-' || acquisitionDateStr.charAt(acquisitionDateStr.length()-3) != '-') {
				throw new ItemException("ERROR: A Incorrect dating format for acquisition date (needs to be ISO8601 format): " + acquisitionDateStr);
			} 
			if (releaseDateStr.charAt(4) != '-' || releaseDateStr.charAt(7) != '-'|| releaseDateStr.charAt(releaseDateStr.length()-3) != '-') {
				throw new ItemException("ERROR: A Incorrect dating format for release date (needs to be ISO8601 format): " + releaseDateStr);
			} 
			
		}
		catch (ItemException ex) {
			return ex.getMessage();
		}
 
		_itemList.addMusic(new Music(artist, title, releaseDateStr, acquisitionDateStr, owner, costStr, formatStr));
		return "Success";
	}

	@Override
	public String getItemToDisplay(String creator, String title, String formatStr) {
		
		return _itemList.findItem(creator+title+formatStr);
	
	}
	
	@Override
	public List<String> getAll(String order) {
		
		if(order.equals(orderType.Acquisition.toString())) {
			return _itemList.getAll(order);
			
		} else if (order.equals(orderType.Creator.toString())) {
			return _itemList.getAll(order);
			
		} else if (order.equals(orderType.Title.toString())){
			return _itemList.getAll(order);
		} else {
			return null;
		}
		
	}

	@Override
	public List<String> getItemsAcquiredInYear(String year) {
		
		 return _itemList.getItemsAcquiredInYear(year);
	}

	@Override
	public List<String> getCreators() {
		
			return _itemList.getCreators();
		}

	public enum formatType {
		CD,
		LP,
		Hardcover,
		Paperback
	}

	@Override
	public List<String> getFlatReport() {
		List<String> info = new ArrayList<String>();
		
		info.add(_flatName);
		info.addAll(_itemList.getFlatReport());
		
		return info;

	}

}
