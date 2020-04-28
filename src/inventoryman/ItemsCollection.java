package inventoryman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import inventoryman.Item.orderType;

public class ItemsCollection implements Iterable<Item> {

	private List<Item> _itemList;

	public ItemsCollection() {
		_itemList = new ArrayList<Item>();
	}

	public Iterator<Item> iterator() {
		return _itemList.iterator();
	}

	public void addBook(Book book) {
		_itemList.add(book);
	}

	public void addMusic(Music music) {
		_itemList.add(music);
	}

	public String findItem(String itemToFind) {
		// Looks through all items and finds the item that matches the input 
		for (Item item : _itemList) {
			if (itemToFind.equalsIgnoreCase(item.getItem())) {
				return item.getItemToDisplay();
			}
		}
		return null;
	}

	public List<String> getAll(String type) {
		List<String> info = new ArrayList<String>();

		// Change type string to a orderType
		orderType _type = orderType.valueOf(type);

		// Sort by selected orderType
		switch (_type) {
			case Creator:
				Collections.sort(_itemList, new Item.ItemComparator(orderType.Acquisition));
				Collections.sort(_itemList, new Item.ItemComparator(orderType.Title));
				Collections.sort(_itemList, new Item.ItemComparator(orderType.Creator));
				break;
			case Title:
				Collections.sort(_itemList, new Item.ItemComparator(orderType.Acquisition));
				Collections.sort(_itemList, new Item.ItemComparator(orderType.Creator));
				Collections.sort(_itemList, new Item.ItemComparator(orderType.Title));
				break;
			case Acquisition:
				Collections.sort(_itemList, new Item.ItemComparator(orderType.Title));
				Collections.sort(_itemList, new Item.ItemComparator(orderType.Creator));
				Collections.sort(_itemList, new Item.ItemComparator(orderType.Acquisition));
				break;
		}


		// Add all item information to an organised list
		for (Item i : _itemList) {	
			info.add(i.getItemToDisplay()); // polymorphism 
		}
		return info;
	}

	public List<String> getItemsAcquiredInYear(String year) {
		List<Item> itemsInYear = new ArrayList<Item>();
		List<String> itemsInYearStr = new ArrayList<String>();
		// Finds all items acquired in a year
		for (Item item : _itemList) {
			if (item.getYearOfAcquisition().contentEquals(year)) {
				itemsInYear.add(item);
			}
		}
		// Organises all the items found in the year
		Collections.sort(itemsInYear, new Item.ItemComparator(orderType.Acquisition));
		// Add all item information to an organized list
		for (Item item : itemsInYear) {
			itemsInYearStr.add(item.getItemToDisplay());
		}
		return itemsInYearStr;
	}

	public List<String> getCreators() {
		List<String> temp = new ArrayList<String>();
		List<String> newList = new ArrayList<String>();
		// Find all creators
		for (Item item : _itemList) {
			temp.add(item.getCreator());
			// Remove all duplicate Creators
			if(!newList.contains(item.getCreator())) {
				newList.add(item.getCreator());
			}
		}
		return newList;
	}

	
	
	public List<String> getFlatReport() {
		List<String> info = new ArrayList<String>();
		List<Item> tempMusic = new ArrayList<Item>();
		List<Item> tempBook = new ArrayList<Item>();
		List<String> ownerList = new ArrayList<String>();

		// Organise Items by its title
		Collections.sort(_itemList, new Item.ItemComparator(orderType.Title));

		// Seperate Items by Music and Book
		for (Item item : _itemList) {
			if (item instanceof Music) {
				tempMusic.add(item);
			} else {
				tempBook.add(item);
			}
			// find all unique owner names
			if(!ownerList.contains(item.getOwner())) {
				ownerList.add(item.getOwner());
			}
		}

		// Organise Books and Music by Creator
		Collections.sort(tempMusic, new Item.ItemComparator(orderType.Creator));
		Collections.sort(tempBook, new Item.ItemComparator(orderType.Creator));

		// Orangise owners by name
		Collections.sort(ownerList);

		// add all books and music according to owner
		for (String owner : ownerList) {
			for (Item book : tempBook) {
				if (owner.equals(book.getOwner())) {
					info.add(book.itemReport());
				}
			}
			for (Item music : tempMusic) {
				if (owner.equals(music.getOwner())) {
					info.add(music.itemReport());
				}
			}
		}
		return info;
	}
}	